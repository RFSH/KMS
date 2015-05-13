var kmsApp = angular.module("kms", ['ngRoute', 'ng-java']);
kmsApp.config(function($routeProvider) {
    //admin
    $routeProvider.when('/admin/index', {templateUrl: 'templates/report/index-admin.html'}); //RF
    $routeProvider.when('/admin/settings', {templateUrl: 'templates/admin/settings.html'}); //HaD
    $routeProvider.when('/admin/abuse-list', {templateUrl: 'templates/admin/abuse-list.html'}); //RF

    //kdbm
    $routeProvider.when('/kdbm/projects', {templateUrl: 'templates/admin/kdbm-projects.html'}); //RF
    $routeProvider.when('/kdbm/projects', {templateUrl: 'templates/admin/kdbm-mails.html'}); //RF

    //knowledge
    $routeProvider.when('/knowledge/create', {templateUrl: 'templates/knowledge/create-knowledge.html'}); //HaD
    $routeProvider.when('/knowledge/view', {templateUrl: 'templates/knowledge/view-knowledge.html'}); //RF
    $routeProvider.when('/knowledge/list',  {templateUrl: 'templates/user/list-employee.html'}); //RF

    //question
    $routeProvider.when('/question/create', {templateUrl: 'templates/question/create-question.html'}); //HaD
    $routeProvider.when('/question/view', {templateUrl: 'templates/question/view-question.html'}); //RF
    $routeProvider.when('/question/list', {templateUrl: 'templates/question/list-question.html'}); //RF

    //report
    $routeProvider.when('/report/activities', {templateUrl: 'templates/report/activities-report.html'}); //RF
    $routeProvider.when('/report/activities/employee', {templateUrl: 'templates/report/activities-employee-report.html'}); //RF
    $routeProvider.when('/report/list', {templateUrl: 'templates/report/knowledge-report.html'}); //che karaE shode RF
    $routeProvider.when('/report/activities/employee', {templateUrl: 'templates/report/tag-report.html'}); //HaD

    //user
    $routeProvider.when('/', {templateUrl: 'templates/user/login.html'}); //RF
    $routeProvider.when('/employee/create', {templateUrl: 'templates/user/create-employee.html'}); //RF
    $routeProvider.when('/employee/list', {templateUrl: 'templates/user/list-employee.html'}); //RF
    $routeProvider.when('/employee/view', {templateUrl: 'templates/user/view-employee.html'}); //RF



});

kmsApp.controller('MainController', function($scope) {
    $scope.redirect = function (route) {
        window.location.hash = route;
        alert(window.location);
    };
});