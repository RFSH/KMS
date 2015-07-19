var kmsApp = angular.module('kms');

kmsApp.controller('LoginController', function ($scope, $ngJava) {
    $ngJava.ready(function() {
        $scope.init();
    });

    $scope.data = {
        username: "",
        password: ""
    };

    $scope.error = "";

    $scope.submit = function () {
        $scope.error = $scope.login($scope.data.username, $scope.data.password);
    };
});