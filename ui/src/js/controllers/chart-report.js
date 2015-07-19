var kmsApp = angular.module('kms');

kmsApp.controller('ChartReportCtrl', function ($scope, $routeParams, $ngJava) {
    $scope.activeTab = "tag";
    $scope.tagData = [
        1 ,2 ,3 ,4
    ];
    $scope.tagLabels = [
        "یک",
        "دو",
        "سه",
        "چهار"
    ];
    $scope.timeSeries = [
        "دانش",
        "سوال",
        "جواب"
    ];

    $scope.timeData = [
        [1,2,3],
        [3,4,5],
        [2,1,4]
    ];
    $scope.timeLabels = [
        '۱۲ شهریور',
        '۱۳ شهریور',
        '۱۵ شهریور'
    ];

    $scope.lineChartOptions = {
        bezierCurve: false,
        datasetFill: false
    };

    $ngJava.ready(function () {
        console.log('here!!!');
        //var data = $scope.getTagsReport();
    });

    $scope.selectTab = function(tab){
        $scope.activeTab = tab;
    };

});