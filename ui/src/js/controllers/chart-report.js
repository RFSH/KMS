var kmsApp = angular.module('kms');

kmsApp.controller('ChartReportCtrl', function ($scope, $routeParams, $ngJava) {
    $scope.activeTab = "tag";
    $scope.tagData = [];
    $scope.tagLabels = [];
    $scope.timeSeries = [
        "دانش",
        "سوال"
    ];

    $scope.timeData = [];
    $scope.timeLabels = [];

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
        $scope.getKnowledges();

    });

    $scope.getTags = function () {
        var tags = $scope.getTagsReport();
        var keys = tags.keyList();
        for (var i = 0; i < keys.size(); i++) {
            $scope.tagLabels.push(keys.get(i));
            $scope.tagData.push(tags.getTags().get(keys.get(i)));
        }
    };

    $scope.getKnowledges = function () {
        var timeReport = $scope.getTimeReport();
        var days = timeReport.getAllDays();
        var items = [];

        var wikiTimes = [];
        var questionTimes = [];
        var labels = [];
        for (var i = 0; i < days.size(); i++) {
            var day = days.get(i);
            wikiTimes.push(timeReport.getWikiCount(day));
            questionTimes.push(timeReport.getQuestionCount(day));

            var mdate = moment();
            mdate.dayOfYear(day);
            labels.push(mdate.format('jD jMMMM jYYYY'));
            //items.push({
            //   day: day,
            //    wiki: timeReport.getWikiCount(day),
            //    question: timeReport.getQuestionCount(day)
            //});
        }

        $scope.timeData = [
            wikiTimes,
            questionTimes
        ];
        $scope.timeLabels = labels;
    };


    $scope.selectTab = function (tab) {
        $scope.activeTab = tab;
    };

});