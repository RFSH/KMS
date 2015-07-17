var kmsApp = angular.module("kms", [
    'ngRoute',
    'ng-sortable',
    'textAngular',
    'ngTagsInput',
    'ui.bootstrap',
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
    $routeProvider.when('/employee/edit/:employeeId', {templateUrl: 'templates/user/create-employee.html'}); //RF done
    $routeProvider.when('/employee/list', {templateUrl: 'templates/user/list-employee.html'}); //RF done
    $routeProvider.when('/employee/:employeeId', {templateUrl: 'templates/user/view-employee.html'}); //RF done



});

kmsApp.controller('MainController', function($scope) {
    $scope.redirect = function (route) {
        window.location.hash = route;
    };
});;var kmsApp = angular.module('kms');

kmsApp.controller('AdminSettingsCtrl', function ($scope) {
    $scope.activeTab = 'permissions';
    $scope.permissionGroups = [
        'سطح دسترسی قوی',
        'سطح دسترسی متوسط',
        'سطح دسترسی ضعیف'


    ];

    $scope.roles = [
        'کارمند',
        'ناظر',
        'تایپیست',
        'منشی'
    ];

    $scope.tags = [
        'گزارش',
        'سند',
        'آموزشی',
        'خارج از سازمان',
        'مدیریتی'
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

kmsApp.controller('AddEmployeeCtrl', function ($scope, $modal, $routeParams, $ngJava) {
    $scope.data = {};
    $scope.error = "";

    $ngJava.ready(function() {
        $scope.employeeId = $routeParams.employeeId;
        $scope.update = ($scope.employeeId !== undefined);

        var roles = $scope.getRoles();
        $scope.roles = [];
        for (var i = 0; i < roles.size(); i++) {
            $scope.roles.push(roleToObject(roles.get(i)));
        }

        var permissions = $scope.getPermissionLevels();
        $scope.permissions = [];
        for (var j = 0; j < permissions.size(); j++) {
            $scope.permissions.push(permissionToObject(permissions.get(j)));
        }

        if ($scope.update) {
            var employee = $scope.getEmployee($scope.employeeId);
            var obj = employeeToObject(employee);
            obj.role = obj.roleId;
            obj.permissionLevel = obj.permissionId;
            obj.passwordConfirm = obj.password;
            $scope.data = obj;
        } else {
            $scope.data.role = $scope.roles[0].id;
            $scope.data.permissionLevel = $scope.permissions[0].id;
        }

    });

    $scope.submit = function() {
        $scope.error = $scope.addOrUpdateEmployee($scope.update, $scope.data);
    };

});

kmsApp.controller('EmployeeListCtrl', function ($scope, $modal, $ngJava) {
    $scope.data = {query:"", role:null};
    $scope.employees = [];

    $ngJava.ready(function() {
        var roles = $scope.getRoles();
        $scope.roles = [{
            id: "0",
            name: "همه"
        }];
        for (var i = 0; i < roles.size(); i++) {
            $scope.roles.push({
                id: roles.get(i).getId(),
                name: roles.get(i).getName()
            });
        }
        $scope.data.role = $scope.roles[0].id;

        $scope.search();
    });

    $scope.search = function() {
        var employees = $scope.searchEmployees($scope.data.query, $scope.data.role);
        $scope.employees = [];
        for (var i = 0; i < employees.size(); i++) {
            $scope.employees.push(employeeToObject(employees.get(i)));
        }
    };

});

kmsApp.controller('EmployeeCtrl', function ($scope, $routeParams, $modal, $ngJava) {
    var employeeId = $routeParams.employeeId;

    $ngJava.ready(function() {
        var employee = $scope.getEmployee(employeeId);
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

kmsApp.controller('KnowledgeCtrl', function ($scope, $modal) {
    $scope.openReportDialog = function () {
        $modal.open({
            templateUrl: 'reportDialog.html'
        });
    };
});;var kmsApp = angular.module('kms');

kmsApp.controller('LoginController', function ($scope) {
    $scope.data = {
        username: "hadi",
        password: ""
    };

    $scope.error = "";

    $scope.submit = function () {
        $scope.error = $scope.login($scope.data.username, $scope.data.password);
    };
});;function employeeToObject(employee) {
    return {
        id: employee.getId(),
        username: employee.getUsername(),
        name: employee.getFullName(),
        firstName: employee.getFirstName(),
        lastName: employee.getLastName(),
        nationalId: employee.getNationalId(),
        password: employee.getPassword(),
        email: employee.getEmail(),
        roleName: employee.getRole().getName(),
        roleId: employee.getRole().getId(),
        permissionName: employee.getPermissionLevel().getName(),
        permissionId: employee.getPermissionLevel().getId()
    };
}

function roleToObject(role) {
    return {
        id: role.getId(),
        name: role.getName()
    };
}

function permissionToObject(permission) {
    return {
        id: permission.getId(),
        name: permission.getName()
    };
}