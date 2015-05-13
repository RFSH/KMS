var kmsApp = angular.module("kms", ['ngRoute', 'ng-java']);
kmsApp.config(function($routeProvider) {
    $routeProvider.when('/', {templateUrl: 'templates/login.html'});
    $routeProvider.when('/employee/create', {templateUrl: 'templates/create-employee.html'});
});