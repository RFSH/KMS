var kmsApp = angular.module('kms');

kmsApp.controller('AddEmployeeCtrl', function ($scope, $modal, $ngJava) {
    $scope.data = {};
    $scope.error = "";

    $ngJava.ready(function() {
        var roles = $scope.getRoles();
        $scope.roles = [];
        for (var i = 0; i < roles.size(); i++) {
            $scope.roles.push({
                id: roles.get(i).getId(),
                name: roles.get(i).getName()
            });
        }
        $scope.data.role = $scope.roles[0].id;

        var permissions = $scope.getPermissionLevels();
        $scope.permissions = [];
        for (var j = 0; j < permissions.size(); j++) {
            $scope.permissions.push({
                id: permissions.get(j).getId(),
                name: permissions.get(j).getName()
            });
        }
        $scope.data.permissionLevel = $scope.permissions[0].id;
    });

    $scope.submit = function() {
        $scope.error = $scope.addOrUpdateEmployee(false, $scope.data);
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
            console.log(i);
            $scope.employees.push({
                id: employees.get(i).getId(),
                username: employees.get(i).getUsername(),
                name: employees.get(i).getFullName(),
                email: employees.get(i).getEmail(),
                role: employees.get(i).getRole().getName(),
                permission: employees.get(i).getPermissionLevel().getName()
            });
        }
    };

});

kmsApp.controller('EmployeeCtrl', function ($scope, $modal) {
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