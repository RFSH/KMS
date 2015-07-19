var kmsApp = angular.module('kms');

kmsApp.config(function ($provide) {
    // this demonstrates how to register a new tool and add it to the default toolbar
    $provide.decorator('taOptions', ['taRegisterTool', '$delegate', function (taRegisterTool, taOptions) { // $delegate is the taOptions we are decorating
        taOptions.toolbar = [
            ['h1', 'h2', 'h3', 'h4', 'h5', 'pre', 'quote', 'bold', 'italics', 'underline', 'strikeThrough', 'ul', 'ol', 'redo', 'undo', 'insertImage','insertLink']
        ];
        return taOptions;
    }]);
});

kmsApp.controller('AddOrEditWikiKnowledgeCtrl', function ($scope, $routeParams, $ngJava) {
    $scope.data = {};
    $scope.tags = [];
    $ngJava.ready(function() {
        $scope.knowledgeId = $routeParams.knowledgeId;
        $scope.update = ($scope.knowledgeId !== undefined);

        if ($scope.update) {
            var knowledge = $scope.getWikiKnowledge($scope.knowledgeId);
            var obj = wikiKnowledgeToObject(knowledge);
            $scope.data = obj;

            for (var i = 0; i < knowledge.getTags().size(); i++) {
                $scope.tags.push(knowledge.getTags().get(i).getName());
            }
        }
    });

    $scope.submit = function() {
        $scope.data.tags = [];
        for (var i = 0; i < $scope.tags.length; i++) {
            $scope.data.tags.push($scope.tags[i].text);
        }
        $scope.addOrUpdateWikiKnowledge($scope.update, $scope.data);
    };
});

kmsApp.controller('WikiListCtrl', function ($scope, $ngJava) {
    $scope.data = {
        query: "",
        fromDate: "",
        toDate: ""
    };
    $scope.knowledges = [];

    $ngJava.ready(function() {
        $scope.search();
    });

    $scope.search = function() {
        var knowledges = $scope.searchWikiKnowledge($scope.data.query, $scope.data.fromDate, $scope.data.toDate);
        $scope.knowledges = [];
        for (var i = 0; i < knowledges.size(); i++) {
            $scope.knowledges.push(wikiKnowledgeToObject(knowledges.get(i)));
        }
    };
});


kmsApp.controller('WikiKnowledgeCtrl', function ($scope, $routeParams, $modal, $ngJava) {
    $scope.knowledge = {};
    $scope.usecase = "";

    var javaKnowledge = null;

    $ngJava.ready(function() {
        $scope.knowledgeId = $routeParams.knowledgeId;
        var knowledge = $scope.getWikiKnowledge($scope.knowledgeId);
        javaKnowledge = knowledge;
        var knowledgeData = wikiKnowledgeToObject(knowledge);
        if (knowledgeData.attachment === null || knowledgeData.attachment === undefined || knowledgeData.attachment === "null") {
            knowledgeData.attachment = "";
        }

        $scope.knowledge = knowledgeData;

        $scope.showApprove = $scope.isUserManager() && !knowledgeData.isApproved;
        $scope.perms = itemPermissionsToObject($scope.getUserPermissions($scope.knowledgeId));
    });

    $scope.voteUp = function() {
        $scope.addVote($scope.knowledgeId, 1);
        $scope.knowledge.voteSum = javaKnowledge.getVoteSum();
    };

    $scope.voteDown = function() {
        $scope.addVote($scope.knowledgeId, -1);
        $scope.knowledge.voteSum = javaKnowledge.getVoteSum();
    };

    $scope.deprecate = function() {
        $scope.deprecateWikiKnowledge($scope.knowledgeId);
        $scope.knowledge.isDeprecated = true;
    };

    $scope.openApproveDialog = function() {
        $modal.open({
            templateUrl: 'approveDialog.html',
            resolve: {
                pScope: function () {
                    return $scope;
                },
                knowledge: function() {
                    return $scope.knowledge;
                }
            },
            controller: function ($scope, pScope, knowledge) {
                $scope.permissions = [];
                $scope.perms = {
                    view: -1,
                    change: -1
                };
                $scope.error = "";
                var permissions = pScope.getPermissionLevels();
                for (var i = 0; i < permissions.size(); i++) {
                    $scope.permissions.push(permissionToObject(permissions.get(i)));
                }

                $scope.submitApproval = function() {
                    if ($scope.perms.view === -1 || $scope.perms.change === -1) {
                        $scope.error = "لطفا سطوح دسترسی را انتخاب کنید";
                        return;
                    }

                    pScope.approve(knowledge.id, 1);
                    knowledge.isApproved = true;
                    pScope.showApproved = false;
                    $scope.$close();
                    show_message("دانش تایید شد", "success");
                };
            }
        });
    };

    $scope.disapprove = function() {
        $scope.approve($scope.knowledgeId, -1);
        window.location.hash = "/knowledge/list";
    };

    $scope.submitUseCase = function() {
        if ($scope.usecase !== "") {
            $scope.addUseCase($scope.knowledgeId, $scope.usecase);
            $scope.knowledge.usecases.push($scope.usecase);
            $scope.usecase = "";
        }
    };



    $scope.openReportDialog = function () {
        $modal.open({
            templateUrl: 'reportDialog.html',
            resolve: {
                pScope: function () {
                    return $scope;
                }
            },
            controller: function ($scope, pScope) {
                $scope.submitAbuseReport = function() {
                    pScope.addAbuseReport(pScope.knowledgeId, $scope.reportContent);
                    $scope.$close();
                    show_message("گزارش تخلف با موفقیت ثبت شد", "success");
                };
            }
        });
    };
});