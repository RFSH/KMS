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