var kmsApp = angular.module('kms');

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
});