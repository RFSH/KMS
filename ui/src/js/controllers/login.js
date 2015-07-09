var kmsApp = angular.module('kms');

kmsApp.controller('LoginController', function ($scope) {
    $scope.data = {
        username: "hadi",
        password: ""
    };

    $scope.error = "";

    $scope.submit = function () {
        $scope.error = $scope.login($scope.data.username, $scope.data.password);
    };
});