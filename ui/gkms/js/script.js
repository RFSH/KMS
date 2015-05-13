var kmsApp = angular.module("kms", [
    'ngRoute',
    'ng-sortable',
    'textAngular',
    'ngTagsInput',
    'ng-java'
]);

kmsApp.config(function($routeProvider) {
    //admin
    $routeProvider.when('/admin/index', {templateUrl: 'templates/admin/index-admin.html'}); //RF done
    $routeProvider.when('/admin/settings', {templateUrl: 'templates/admin/settings.html'}); //HaD
    $routeProvider.when('/admin/abuse-list', {templateUrl: 'templates/admin/abuse-list.html'}); //RF done

    //kdbm
    $routeProvider.when('/kdbm/projects', {templateUrl: 'templates/kdbm/kdbm-projects.html'}); //RF done
    $routeProvider.when('/kdbm/mails', {templateUrl: 'templates/kdbm/kdbm-mails.html'}); //RF done

    //knowledge
    $routeProvider.when('/knowledge/create', {templateUrl: 'templates/knowledge/create-knowledge.html'}); //HaD
    $routeProvider.when('/knowledge/view', {templateUrl: 'templates/knowledge/view-knowledge.html'}); //RF done
    $routeProvider.when('/knowledge/list',  {templateUrl: 'templates/knowledge/list-knowledge.html'}); //RF done

    //question
    $routeProvider.when('/question/create', {templateUrl: 'templates/question/create-question.html'}); //HaD
    $routeProvider.when('/question/view', {templateUrl: 'templates/question/view-question.html'}); //RF done
    $routeProvider.when('/question/list', {templateUrl: 'templates/question/list-question.html'}); //RF done

    //report
    $routeProvider.when('/report/activities', {templateUrl: 'templates/report/activities-report.html'}); //RF done
    $routeProvider.when('/report/activities/employee', {templateUrl: 'templates/report/activities-employee-report.html'}); //RF done
    $routeProvider.when('/report/knowledge', {templateUrl: 'templates/report/knowledge-report.html'}); //RF done
    $routeProvider.when('/report/tag', {templateUrl: 'templates/report/tag-report.html'}); //HaD

    //user
    $routeProvider.when('/', {templateUrl: 'templates/user/login.html'}); //RF done
    $routeProvider.when('/employee/create', {templateUrl: 'templates/user/create-employee.html'}); //RF done
    $routeProvider.when('/employee/list', {templateUrl: 'templates/user/list-employee.html'}); //RF done
    $routeProvider.when('/employee/view', {templateUrl: 'templates/user/view-employee.html'}); //RF done



});

kmsApp.controller('MainController', function($scope) {
    $scope.redirect = function (route) {
        window.location.hash = route;
    };
});;var kmsApp = angular.module('kms');

kmsApp.controller('AdminSettingsCtrl', function ($scope) {
    $scope.activeTab = 'permissions';
    $scope.permissionGroups = [
        'سطح دسترسی ۱',
        'سطج دسرتسی ۲'
    ];

    $scope.roles = [
        'کارمند',
        'ناظر',
        'تایپیست',
        'منشی'
    ];

    $scope.tags = [

    ];

    $scope.sortableConfig = {};

    $scope.selectTab = function (tab) {
        $scope.activeTab = tab;
    };

    $scope.addPermissionGroup = function () {
        $scope.permissionGroups.push('');
    };

    $scope.addRole = function () {
        $scope.roles.push('');
    };

    $scope.addTag = function () {
        $scope.tags.push('');
    };
});;var kmsApp = angular.module('kms');

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