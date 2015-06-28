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

kmsApp.controller('KnowledgeCreateCtrl', function ($scope) {
    $scope.knowledge = {
        content: ""
    };
});

kmsApp.controller('KnowledgeCtrl', function ($scope, $modal) {
    $scope.openReportDialog = function () {
        $modal.open({
            templateUrl: 'reportDialog.html'
        });
    };
});