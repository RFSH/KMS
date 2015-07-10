var kmsApp = angular.module('kms');

kmsApp.controller('AddEmployeeCtrl', function ($scope, $modal, $ngJava) {
    $scope.data = {};
    $scope.error = "";

    $ngJava.ready(function() {
        //var x = $scope.getRoles();
        setTimeout(function() {}, 2000);
        //console.log(x.get(0));
    });

    $scope.submit = function() {
        $scope.error = $scope.addOrUpdateEmployee(false, $scope.data);
        console.log($scope.getRoles(2));

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