var kmsApp = angular.module('kms');

kmsApp.controller('LetterListCtrl', function ($scope, $routeParams, $ngJava) {
    $scope.data = {title: ""};
    $ngJava.ready(function () {
        $scope.search();
    });

    $scope.search = function () {
        var projects = $scope.searchLetters($scope.data.title);
        $scope.projects = [];
        for (var i = 0; i < projects.size(); i++) {
            $scope.projects.push(letterToObject(projects.get(i)));
        }
    };
});

kmsApp.controller('ProjectListCtrl', function ($scope, $routeParams, $ngJava) {
    $scope.data = {title: ""};
    $ngJava.ready(function () {
        console.log('haaaaaa!!');

        $scope.search();
    });

    $scope.search = function () {
        console.log('searching!!!!! :))');
        var projects = $scope.searchProjects($scope.data.title);
        console.log(projects.size());
        $scope.projects = [];
        for (var i = 0; i < projects.size(); i++) {
            console.log(projectToObject(projects.get(i)))
            $scope.projects.push(projectToObject(projects.get(i)));
        }
    };
});