var kmsApp = angular.module('kms');

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
});