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
            var knowledge = $scope.getEmployee($scope.employeeId);
            var obj = wikiKnowledgeToObject(employee);
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


kmsApp.controller('KnowledgeCtrl', function ($scope, $routeParams, $modal, $ngJava) {
    $scope.knowledge = {};

    $ngJava.ready(function() {
        $scope.knowledgeId = $routeParams.knowledgeId;
        var knowledge = $scope.getWikiKnowledge($scope.knowledgeId);
        var knowledgeData = wikiKnowledgeToObject(knowledge);
        if (knowledgeData.attachment === null || knowledgeData.attachment === undefined) {
            knowledgeData.attachment = "وجود ندارد";
        }

        $scope.knowledge = knowledgeData;
    });

    $scope.openReportDialog = function () {
        $modal.open({
            templateUrl: 'reportDialog.html'
        });
    };
});