var kmsApp = angular.module('kms');

kmsApp.controller('AdminSettingsCtrl', function ($scope, $routeParams, $ngJava) {
    $scope.activeTab = 'permissions';
    $scope.permissionGroups = [];
    $scope.roles = [];
    $scope.tags = [];

    $ngJava.ready(function () {
        var permissions = $scope.getPermissions();
        for (var i = 0; i < permissions.size(); i++) {
            $scope.permissionGroups.push(permissionToObject(permissions.get(i)));
        }
        var roles = $scope.getRoles();
        for (var j = 0; j < roles.size(); j++) {
            $scope.roles.push(roleToObject(roles.get(j)));
        }

        var tags = $scope.getDefaultTags();
        for (var k = 0; k < tags.size(); k++) {
            $scope.tags.push(tagToObject(tags.get(k)));
        }
    });


    $scope.sortableConfig = {};

    $scope.selectTab = function (tab) {
        $scope.activeTab = tab;
    };

    $scope.addPermissionGroup = function () {
        $scope.permissionGroups.push({id: undefined, name: ''});
    };

    $scope.removePermissionGroup = function (index, id) {
        $scope.removePermissionLevel(id);
        $scope.permissionGroups.splice(index, 1);
    };

    $scope.savePermissions = function () {
        var result = $scope.setPermissions($scope.permissionGroups, $scope.permissionGroups.length);
        if (result) {
            show_message('با موفقیت ذخیره شدند.', 'success');
        } else {
            show_message('اطلاعات را درست وارد کنید.', 'error');
        }
    };

    $scope.addRole = function () {
        $scope.roles.push({id: undefined, name:''});
    };

    $scope.removeRole = function (index, id) {
        $scope.removeRole(id);
        $scope.roles.splice(index, 1);
    };

    $scope.saveRoles = function () {
        var result = $scope.setRoles($scope.roles, $scope.roles.length);
        if (result) {
            show_message('با موفقیت ذخیره شدند.', 'success');
        } else {
            show_message('اطلاعات را درست وارد کنید.', 'error');
        }
    };

    $scope.addTag = function () {
        $scope.tags.push({id: undefined, name:''});
    };

    $scope.removeTag = function (index, id) {
        $scope.removeDefaultTag(id);
        $scope.tags.splice(index, 1);
    };

    $scope.saveTags = function () {
        var result = $scope.setDefaultTags($scope.tags, $scope.tags.length);
        if (result) {
            show_message('با موفقیت ذخیره شدند.', 'success');
        } else {
            show_message('اطلاعات را درست وارد کنید.', 'error');
        }
    };


});