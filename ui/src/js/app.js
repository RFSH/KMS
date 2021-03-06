var kmsApp = angular.module("kms", [
    'ngRoute',
    'ng-sortable',
    'textAngular',
    'ngTagsInput',
    'ui.bootstrap',
    'ng-java',
    'chart.js'
]);

kmsApp.config(function($routeProvider) {
    //restricted
    $routeProvider.when('/restricted', {templateUrl:'templates/restricted.html'});

    //admin
    $routeProvider.when('/admin/index', {templateUrl: 'templates/admin/index-admin.html'}); //RF done
    $routeProvider.when('/admin/settings', {templateUrl: 'templates/admin/settings.html'}); //HaD
    $routeProvider.when('/admin/abuse-list', {templateUrl: 'templates/admin/abuse-list.html'}); //RF done

    //kdbm
    $routeProvider.when('/kdbm/projects', {templateUrl: 'templates/kdbm/kdbm-projects.html'}); //RF done
    $routeProvider.when('/kdbm/mails', {templateUrl: 'templates/kdbm/kdbm-mails.html'}); //RF done

    //knowledge
    $routeProvider.when('/knowledge/create', {templateUrl: 'templates/knowledge/create-knowledge.html'}); //HaD
    $routeProvider.when('/knowledge/edit/:knowledgeId', {templateUrl: 'templates/knowledge/create-knowledge.html'}); //HaD
    $routeProvider.when('/knowledge/list',  {templateUrl: 'templates/knowledge/list-knowledge.html'}); //RF done
    $routeProvider.when('/knowledge/:knowledgeId', {templateUrl: 'templates/knowledge/view-knowledge.html'}); //RF done

    //question
    $routeProvider.when('/question/create', {templateUrl: 'templates/question/create-question.html'}); //HaD
    $routeProvider.when('/question/list', {templateUrl: 'templates/question/list-question.html'}); //RF done
    $routeProvider.when('/question/:questionId', {templateUrl: 'templates/question/view-question.html'}); //RF done

    //report
    $routeProvider.when('/report/activities', {templateUrl: 'templates/report/activities-report.html'}); //RF done
    $routeProvider.when('/report/activities/employee', {templateUrl: 'templates/report/activities-employee-report.html'}); //RF done
    $routeProvider.when('/report/knowledge/:knowledgeId', {templateUrl: 'templates/report/knowledge-report.html'}); //RF done
    $routeProvider.when('/report/chart', {templateUrl: 'templates/report/chart-report.html'}); //HaD

    //user
    $routeProvider.when('/', {templateUrl: 'templates/user/login.html'}); //RF done
    $routeProvider.when('/employee/create', {templateUrl: 'templates/user/create-employee.html'}); //RF done
    $routeProvider.when('/employee/edit/:employeeId', {templateUrl: 'templates/user/create-employee.html'}); //RF done
    $routeProvider.when('/employee/list', {templateUrl: 'templates/user/list-employee.html'}); //RF done
    $routeProvider.when('/employee/:employeeId', {templateUrl: 'templates/user/view-employee.html'}); //RF done



});

kmsApp.controller('MainController', function($scope) {
    $scope.redirect = function (route) {
        window.location.hash = route;
    };
});