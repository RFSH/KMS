var kmsApp = angular.module('kms');

kmsApp.controller('ChartReportCtrl', function ($scope, $routeParams, $ngJava) {
    $scope.activeTab = "tag";
    $scope.tagData = [];
    $scope.tagLabels = [];
    $scope.timeSeries = [
        "دانش",
        "سوال"
    ];

    $scope.timeData = [
        [1,2,3],
        [3,4,5]
    ];
    $scope.timeLabels = [
        '۱۲ شهریور',
        '۱۳ شهریور',
        '۱۵ شهریور'
    ];

    $scope.colors = [
        '#F7464A', '#46BFBD', '#FDB45C', 'darkgreen', 'darkblue', 'darkred', '108edc'
    ];

    $scope.pieChartOptions = {
        animateRotate: true,
        animateScale: false
    };

    $scope.lineChartOptions = {
        bezierCurve: false,
        datasetFill: false
    };

    $ngJava.ready(function () {
        $scope.getTags();

    });

    $scope.getTags = function(){
        var tags = $scope.getTagsReport();
        var keys = tags.keyList();
        for(var i=0;i<keys.size();i++){
            $scope.tagLabels.push(keys.get(i));
            $scope.tagData.push(tags.getTags().get(keys.get(i)));
        }
    };



    $scope.selectTab = function(tab){
        $scope.activeTab = tab;
    };

});