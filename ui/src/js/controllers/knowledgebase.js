var kmsApp = angular.module('kms');

kmsApp.controller('LetterListCtrl', function ($scope, $routeParams, $ngJava) {
    $scope.data = {title: ""};
    $scope.modalLetterPaths = [];
    $scope.modalLetter = "";

    $ngJava.ready(function () {
        $scope.search();
    });

    $scope.search = function () {
        var projects = $scope.searchLetters($scope.data.title);
        $scope.letters = [];
        for (var i = 0; i < projects.size(); i++) {
            $scope.letters.push(letterToObject(projects.get(i)));
        }
    };

    $scope.openLetterPaths = function (letter) {
        $scope.modalLetterPaths = letter.nodes;
        $scope.modalLetter = letter.title;
    };
});

kmsApp.controller('ProjectListCtrl', function ($scope, $routeParams, $ngJava) {
    $scope.data = {title: ""};
    $scope.modalActivities = [];
    $scope.modalProject = "";

    $ngJava.ready(function () {
        $scope.search();
    });

    $scope.search = function () {
        var projects = $scope.searchProjects($scope.data.title);
        $scope.projects = [];
        for (var i = 0; i < projects.size(); i++) {
            $scope.projects.push(projectToObject(projects.get(i)));
        }
    };

    $scope.openProjectActivities = function (project) {
        $scope.modalActivities = project.nodes;
        $scope.modalProject = project.title;
    };
});