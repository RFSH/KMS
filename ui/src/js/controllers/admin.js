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

kmsApp.controller('AbuseReportListCtrl', function ($scope, $ngJava) {
    $scope.reports = [];
    $scope.search = {
        content: "",
        type: "-1"
    };

    $ngJava.ready(function() {
        var results = $scope.searchAbuseReport($scope.search.content, $scope.search.type);
        for (var i = 0; i < results.size(); i++) {
            var obj = abuseToObject(results.get(i));
            if (obj.knowledgeType === 0) {
                obj.knowledgeContent = obj.knowledge.getTitle();
                obj.knowledgeUrl = "knowledge/" + obj.id;
            } else if (obj.knowledgeType === 1) {
                obj.knowledgeContent = obj.knowledge.getTitle();
                obj.knowledgeUrl = "question/" + obj.id;
            } else if (obj.knowledgeType === 2) {
                obj.knowledgeContent = obj.knowledge.getContent();
                obj.knowledgeUrl = "question/" + obj.knowledge.getQuestion().getId();
            }
            $scope.reports.push(obj);
            console.log(obj.knowledgeUrl);
        }
    });

    $scope.acceptReport = function (report, index) {
        $scope.responseToReport(report.id, true);
        $scope.reports.splice(index, 1);
    };

    $scope.declineReport = function (report, index) {
        $scope.responseToReport(report.id, false);
        $scope.reports.splice(index, 1);
    };
});