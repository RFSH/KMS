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

kmsApp.controller('AddOrEditQuestionKnowledgeCtrl', function ($scope, $routeParams, $ngJava) {
    $scope.data = {};
    $scope.tags = [];
    $ngJava.ready(function() {
        $scope.knowledgeId = $routeParams.knowledgeId;
        $scope.update = ($scope.knowledgeId !== undefined);

        if ($scope.update) {
            var knowledge = $scope.getQuestionKnowledge($scope.knowledgeId);
            var obj = questionKnowledgeToObject(knowledge);
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
        $scope.addOrUpdateQuestionKnowledge($scope.update, $scope.data);
    };
});

kmsApp.controller('QuestionListCtrl', function ($scope, $ngJava) {
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
        var knowledges = $scope.searchQuestionKnowledge($scope.data.query, $scope.data.fromDate, $scope.data.toDate);
        $scope.knowledges = [];
        for (var i = 0; i < knowledges.size(); i++) {
            $scope.knowledges.push(questionKnowledgeToObject(knowledges.get(i)));
        }
    };
});


kmsApp.controller('QuestionKnowledgeCtrl', function ($scope, $routeParams, $modal, $ngJava) {
    $scope.knowledge = {};
    $scope.answer = {};
    $scope.answers = [];
    $scope.usecase = "";

    var javaKnowledge = null;

    $ngJava.ready(function() {
        $scope.knowledgeId = $routeParams.questionId;
        var knowledge = $scope.getQuestionKnowledge($scope.knowledgeId);
        javaKnowledge = knowledge;
        var knowledgeData = questionKnowledgeToObject(knowledge);
        if (knowledgeData.attachment === null || knowledgeData.attachment === undefined || knowledgeData.attachment === "null") {
            knowledgeData.attachment = "";
        }

        var answers = $scope.getAnswers($scope.knowledgeId);
        $scope.answers = [];
        for (var i = 0; i < answers.size(); i++) {
            $scope.answers.push(answerKnowledgeToObject(answers.get(i)));
        }

        $scope.knowledge = knowledgeData;
    });

    $scope.voteUp = function(knowledge) {
        knowledge.voteSum = $scope.addVote(knowledge.id, 1);
    };

    $scope.voteDown = function(knowledge) {
        knowledge.voteSum = $scope.addVote(knowledge.id, -1);
    };

    $scope.approve = function() {
        $scope.approveOrDisapprove($scope.knowledgeId, 1);
        $scope.knowledge.isApproved = true;
    };

    $scope.submitAnswer = function() {
        var content = $scope.answer.content;
        if (content === undefined || content === null || content === "") {
            return;
        }

        var answer = $scope.addAnswer($scope.knowledgeId, $scope.answer.content);
        $scope.answers.push(answerKnowledgeToObject(answer));
    };

    $scope.openReportDialog = function (knowledge) {
        $modal.open({
            templateUrl: 'reportDialog.html',
            resolve: {
                pScope: function () {
                    return $scope;
                },
                knowledge: function() {
                    return knowledge;
                }
            },
            controller: function ($scope, pScope, knowledge) {
                $scope.submitAbuseReport = function() {
                    pScope.addAbuseReport(knowledge.id, $scope.reportContent);
                    $scope.$close();
                    show_message("گزارش تخلف با موفقیت ثبت شد", "success");
                };
            }
        });
    };
});