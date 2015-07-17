var kmsApp = angular.module('kms');

kmsApp.controller('AddEditWikiCtrl', function ($scope, $routeParams, $modal, $ngJava) {
    $scope.data = {};

    $ngJava.ready(function() {

    });

    $scope.submit = function() {

    };
});

kmsApp.controller('QuestionCtrl', function ($scope, $routeParams, $modal, $ngJava) {
    var questionId = $routeParams.questionId;

    $ngJava.ready(function() {
        var employee = $scope.getQuestion(questionId);
        $scope.employee = employeeToObject(employee);

        $scope.stats = $scope.getEmployeeStats(employeeId);
    });

    $scope.editEmployee = function () {
        window.location.hash = '/employee/edit/' + employeeId;
    };

    $scope.sendMail = function() {
        window.open('mailto:{{ employee.email }}', '_blank');
    };

    $scope.openDeleteConfirm = function () {
        $modal.open({
            templateUrl: 'confirmDialog.html',
            controller: function ($scope) {
                $scope.title = "حذف کارمند";
                $scope.message = "آیا می خواهید کارمند را حذف کنید؟";
                //$scope.accept
                //$scope.reject
            }
        });
    };
});