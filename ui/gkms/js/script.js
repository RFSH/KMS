var kmsApp = angular.module("kms", [
    'ngRoute',
    'ng-sortable',
    'textAngular',
    'ngTagsInput',
    'ui.bootstrap',
    'ng-java',
    'chart.js'
]);

kmsApp.config(function($routeProvider) {
    //restricted
    $routeProvider.when('/restricted', {templateUrl:'templates/restricted.html'});

    //admin
    $routeProvider.when('/admin/index', {templateUrl: 'templates/admin/index-admin.html'}); //RF done
    $routeProvider.when('/admin/settings', {templateUrl: 'templates/admin/settings.html'}); //HaD
    $routeProvider.when('/admin/abuse-list', {templateUrl: 'templates/admin/abuse-list.html'}); //RF done

    //kdbm
    $routeProvider.when('/kdbm/projects', {templateUrl: 'templates/kdbm/kdbm-projects.html'}); //RF done
    $routeProvider.when('/kdbm/mails', {templateUrl: 'templates/kdbm/kdbm-mails.html'}); //RF done

    //knowledge
    $routeProvider.when('/knowledge/create', {templateUrl: 'templates/knowledge/create-knowledge.html'}); //HaD
    $routeProvider.when('/knowledge/edit/:knowledgeId', {templateUrl: 'templates/knowledge/create-knowledge.html'}); //HaD
    $routeProvider.when('/knowledge/list',  {templateUrl: 'templates/knowledge/list-knowledge.html'}); //RF done
    $routeProvider.when('/knowledge/:knowledgeId', {templateUrl: 'templates/knowledge/view-knowledge.html'}); //RF done

    //question
    $routeProvider.when('/question/create', {templateUrl: 'templates/question/create-question.html'}); //HaD
    $routeProvider.when('/question/list', {templateUrl: 'templates/question/list-question.html'}); //RF done
    $routeProvider.when('/question/:questionId', {templateUrl: 'templates/question/view-question.html'}); //RF done

    //report
    $routeProvider.when('/report/activities', {templateUrl: 'templates/report/activities-report.html'}); //RF done
    $routeProvider.when('/report/activities/employee', {templateUrl: 'templates/report/activities-employee-report.html'}); //RF done
    $routeProvider.when('/report/knowledge/:knowledgeId', {templateUrl: 'templates/report/knowledge-report.html'}); //RF done
    $routeProvider.when('/report/chart', {templateUrl: 'templates/report/chart-report.html'}); //HaD

    //user
    $routeProvider.when('/', {templateUrl: 'templates/user/login.html'}); //RF done
    $routeProvider.when('/employee/create', {templateUrl: 'templates/user/create-employee.html'}); //RF done
    $routeProvider.when('/employee/edit/:employeeId', {templateUrl: 'templates/user/create-employee.html'}); //RF done
    $routeProvider.when('/employee/list', {templateUrl: 'templates/user/list-employee.html'}); //RF done
    $routeProvider.when('/employee/:employeeId', {templateUrl: 'templates/user/view-employee.html'}); //RF done



});

kmsApp.controller('MainController', function($scope) {
    $scope.redirect = function (route) {
        window.location.hash = route;
    };
});;var kmsApp = angular.module('kms');

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
});;var kmsApp = angular.module('kms');

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

});;var kmsApp = angular.module('kms');

kmsApp.controller('AddEmployeeCtrl', function ($scope, $modal, $routeParams, $ngJava) {
    $scope.data = {};
    $scope.error = "";

    $ngJava.ready(function() {
        $scope.employeeId = $routeParams.employeeId;
        $scope.update = ($scope.employeeId !== undefined);

        var roles = $scope.getRoles();
        $scope.roles = [];
        for (var i = 0; i < roles.size(); i++) {
            $scope.roles.push(roleToObject(roles.get(i)));
        }

        var permissions = $scope.getPermissionLevels();
        $scope.permissions = [];
        for (var j = 0; j < permissions.size(); j++) {
            $scope.permissions.push(permissionToObject(permissions.get(j)));
        }

        if ($scope.update) {
            var employee = $scope.getEmployee($scope.employeeId);
            var obj = employeeToObject(employee);
            obj.role = obj.roleId;
            obj.permissionLevel = obj.permissionId;
            obj.passwordConfirm = obj.password;
            $scope.data = obj;
        } else {
            $scope.data.role = $scope.roles[0].id;
            $scope.data.permissionLevel = $scope.permissions[0].id;
        }

    });

    $scope.submit = function() {
        $scope.error = $scope.addOrUpdateEmployee($scope.update, $scope.data);
    };

});

kmsApp.controller('EmployeeListCtrl', function ($scope, $modal, $ngJava) {
    $scope.data = {query:"", role:null};
    $scope.employees = [];

    $ngJava.ready(function() {
        var roles = $scope.getRoles();
        $scope.roles = [{
            id: "0",
            name: "همه"
        }];
        for (var i = 0; i < roles.size(); i++) {
            $scope.roles.push({
                id: roles.get(i).getId(),
                name: roles.get(i).getName()
            });
        }
        $scope.data.role = $scope.roles[0].id;

        $scope.search();
    });

    $scope.search = function() {
        var employees = $scope.searchEmployees($scope.data.query, $scope.data.role);
        $scope.employees = [];
        for (var i = 0; i < employees.size(); i++) {
            $scope.employees.push(employeeToObject(employees.get(i)));
        }
    };

});

kmsApp.controller('EmployeeCtrl', function ($scope, $routeParams, $modal, $ngJava) {
    var employeeId = $routeParams.employeeId;

    $ngJava.ready(function() {
        var employee = $scope.getEmployee(employeeId);
        $scope.employee = employeeToObject(employee);

        $scope.stats = $scope.getEmployeeStats(employeeId);
    });

    $scope.editEmployee = function () {
        window.location.hash = '/employee/edit/' + employeeId;
    };

    $scope.sendMail = function() {
        window.open('mailto:{{ employee.email }}', '_blank');
    };

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
});;var kmsApp = angular.module('kms');

kmsApp.config(function ($provide) {
    // this demonstrates how to register a new tool and add it to the default toolbar
    $provide.decorator('taOptions', ['taRegisterTool', '$delegate', function (taRegisterTool, taOptions) { // $delegate is the taOptions we are decorating
        taOptions.toolbar = [
            ['h1', 'h2', 'h3', 'h4', 'h5', 'pre', 'quote', 'bold', 'italics', 'underline', 'strikeThrough', 'ul', 'ol', 'redo', 'undo', 'insertImage','insertLink']
        ];
        return taOptions;
    }]);
});

kmsApp.controller('AddOrEditWikiKnowledgeCtrl', function ($scope, $routeParams, $ngJava) {
    $scope.data = {};
    $scope.tags = [];
    $ngJava.ready(function() {
        $scope.knowledgeId = $routeParams.knowledgeId;
        $scope.update = ($scope.knowledgeId !== undefined);

        if ($scope.update) {
            var knowledge = $scope.getWikiKnowledge($scope.knowledgeId);
            var obj = wikiKnowledgeToObject(knowledge);
            $scope.data = obj;

            for (var i = 0; i < knowledge.getTags().size(); i++) {
                $scope.tags.push(knowledge.getTags().get(i).getName());
            }
        }
    });

    $scope.submit = function() {
        $scope.data.tags = [];
        for (var i = 0; i < $scope.tags.length; i++) {
            $scope.data.tags.push($scope.tags[i].text);
        }
        $scope.addOrUpdateWikiKnowledge($scope.update, $scope.data);
    };
});

kmsApp.controller('WikiListCtrl', function ($scope, $ngJava) {
    $scope.data = {
        query: "",
        fromDate: "",
        toDate: ""
    };
    $scope.knowledges = [];

    $ngJava.ready(function() {
        $scope.search();
    });

    $scope.search = function() {
        var knowledges = $scope.searchWikiKnowledge($scope.data.query, $scope.data.fromDate, $scope.data.toDate);
        $scope.knowledges = [];
        for (var i = 0; i < knowledges.size(); i++) {
            $scope.knowledges.push(wikiKnowledgeToObject(knowledges.get(i)));
        }
    };
});


kmsApp.controller('WikiKnowledgeCtrl', function ($scope, $routeParams, $modal, $ngJava) {
    $scope.knowledge = {};
    $scope.usecase = "";

    var javaKnowledge = null;

    $ngJava.ready(function() {
        $scope.knowledgeId = $routeParams.knowledgeId;
        var knowledge = $scope.getWikiKnowledge($scope.knowledgeId);
        javaKnowledge = knowledge;
        var knowledgeData = wikiKnowledgeToObject(knowledge);
        if (knowledgeData.attachment === null || knowledgeData.attachment === undefined || knowledgeData.attachment === "null") {
            knowledgeData.attachment = "";
        }

        $scope.knowledge = knowledgeData;

        $scope.showApprove = $scope.isUserManager() && !knowledgeData.isApproved;
        $scope.perms = itemPermissionsToObject($scope.getPermissions($scope.knowledgeId));
    });

    $scope.voteUp = function() {
        $scope.addVote($scope.knowledgeId, 1);
        $scope.knowledge.voteSum = javaKnowledge.getVoteSum();
    };

    $scope.voteDown = function() {
        $scope.addVote($scope.knowledgeId, -1);
        $scope.knowledge.voteSum = javaKnowledge.getVoteSum();
    };

    $scope.deprecate = function() {
        $scope.deprecateWikiKnowledge($scope.knowledgeId);
        $scope.knowledge.isDeprecated = true;
    };

    $scope.approve = function() {
        $scope.approveOrDisapprove($scope.knowledgeId, 1);
        $scope.knowledge.isApproved = true;
    };

    $scope.disapprove = function() {
        $scope.approveOrDisapprove($scope.knowledgeId, -1);
        window.location.hash = "/knowledge/list";
    };

    $scope.submitUseCase = function() {
        if ($scope.usecase !== "") {
            $scope.addUseCase($scope.knowledgeId, $scope.usecase);
            $scope.knowledge.usecases.push($scope.usecase);
            $scope.usecase = "";
        }
    };



    $scope.openReportDialog = function () {
        $modal.open({
            templateUrl: 'reportDialog.html',
            resolve: {
                pScope: function () {
                    return $scope;
                }
            },
            controller: function ($scope, pScope) {
                $scope.submitAbuseReport = function() {
                    pScope.addAbuseReport(pScope.knowledgeId, $scope.reportContent);
                    $scope.$close();
                    show_message("گزارش تخلف با موفقیت ثبت شد", "success");
                };
            }
        });
    };
});;var kmsApp = angular.module('kms');

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
});;var kmsApp = angular.module('kms');

kmsApp.controller('LoginController', function ($scope, $ngJava) {
    $ngJava.ready(function() {
        $scope.init();
    });

    $scope.data = {
        username: "",
        password: ""
    };

    $scope.error = "";

    $scope.submit = function () {
        $scope.error = $scope.login($scope.data.username, $scope.data.password);
    };
});;var kmsApp = angular.module('kms');

kmsApp.config(function ($provide) {
    // this demonstrates how to register a new tool and add it to the default toolbar
    $provide.decorator('taOptions', ['taRegisterTool', '$delegate', function (taRegisterTool, taOptions) { // $delegate is the taOptions we are decorating
        taOptions.toolbar = [
            ['h1', 'h2', 'h3', 'h4', 'h5', 'pre', 'quote', 'bold', 'italics', 'underline', 'strikeThrough', 'ul', 'ol', 'redo', 'undo', 'insertImage','insertLink']
        ];
        return taOptions;
    }]);
});

kmsApp.controller('AddOrEditQuestionKnowledgeCtrl', function ($scope, $routeParams, $ngJava) {
    $scope.data = {};
    $scope.tags = [];
    $ngJava.ready(function() {
        $scope.knowledgeId = $routeParams.knowledgeId;
        $scope.update = ($scope.knowledgeId !== undefined);

        if ($scope.update) {
            var knowledge = $scope.getQuestionKnowledge($scope.knowledgeId);
            var obj = questionKnowledgeToObject(knowledge);
            $scope.data = obj;

            for (var i = 0; i < knowledge.getTags().size(); i++) {
                $scope.tags.push(knowledge.getTags().get(i).getName());
            }
        }
    });

    $scope.submit = function() {
        $scope.data.tags = [];
        for (var i = 0; i < $scope.tags.length; i++) {
            $scope.data.tags.push($scope.tags[i].text);
        }
        $scope.addOrUpdateQuestionKnowledge($scope.update, $scope.data);
    };
});

kmsApp.controller('QuestionListCtrl', function ($scope, $ngJava) {
    $scope.data = {
        query: "",
        fromDate: "",
        toDate: ""
    };
    $scope.knowledges = [];

    $ngJava.ready(function() {
        $scope.search();
    });

    $scope.search = function() {
        var knowledges = $scope.searchQuestionKnowledge($scope.data.query, $scope.data.fromDate, $scope.data.toDate);
        $scope.knowledges = [];
        for (var i = 0; i < knowledges.size(); i++) {
            $scope.knowledges.push(questionKnowledgeToObject(knowledges.get(i)));
        }
    };
});


kmsApp.controller('QuestionKnowledgeCtrl', function ($scope, $routeParams, $modal, $ngJava) {
    $scope.knowledge = {};
    $scope.answer = {};
    $scope.answers = [];
    $scope.usecase = "";

    var javaKnowledge = null;

    $ngJava.ready(function() {
        $scope.knowledgeId = $routeParams.questionId;
        var knowledge = $scope.getQuestionKnowledge($scope.knowledgeId);
        javaKnowledge = knowledge;
        var knowledgeData = questionKnowledgeToObject(knowledge);
        if (knowledgeData.attachment === null || knowledgeData.attachment === undefined || knowledgeData.attachment === "null") {
            knowledgeData.attachment = "";
        }

        var answers = $scope.getAnswers($scope.knowledgeId);
        $scope.answers = [];
        for (var i = 0; i < answers.size(); i++) {
            $scope.answers.push(answerKnowledgeToObject(answers.get(i)));
        }

        $scope.knowledge = knowledgeData;
    });

    $scope.voteUp = function(knowledge) {
        knowledge.voteSum = $scope.addVote(knowledge.id, 1);
    };

    $scope.voteDown = function(knowledge) {
        knowledge.voteSum = $scope.addVote(knowledge.id, -1);
    };

    $scope.approve = function() {
        $scope.approveOrDisapprove($scope.knowledgeId, 1);
        $scope.knowledge.isApproved = true;
    };

    $scope.submitAnswer = function() {
        var content = $scope.answer.content;
        if (content === undefined || content === null || content === "") {
            return;
        }

        var answer = $scope.addAnswer($scope.knowledgeId, $scope.answer.content);
        $scope.answers.push(answerKnowledgeToObject(answer));
    };

    $scope.openReportDialog = function (knowledge) {
        $modal.open({
            templateUrl: 'reportDialog.html',
            resolve: {
                pScope: function () {
                    return $scope;
                },
                knowledge: function() {
                    return knowledge;
                }
            },
            controller: function ($scope, pScope, knowledge) {
                $scope.submitAbuseReport = function() {
                    pScope.addAbuseReport(knowledge.id, $scope.reportContent);
                    $scope.$close();
                    show_message("گزارش تخلف با موفقیت ثبت شد", "success");
                };
            }
        });
    };
});;function employeeToObject(employee) {
    return {
        id: employee.getId(),
        username: employee.getUsername(),
        name: employee.getFullName(),
        firstName: employee.getFirstName(),
        lastName: employee.getLastName(),
        nationalId: employee.getNationalId(),
        password: employee.getPassword(),
        email: employee.getEmail(),
        roleName: employee.getRole().getName(),
        roleId: employee.getRole().getId(),
        permissionName: employee.getPermissionLevel().getName(),
        permissionId: employee.getPermissionLevel().getId()
    };
}

function roleToObject(role) {
    return {
        id: role.getId(),
        name: role.getName()
    };
}

function permissionToObject(permission) {
    return {
        id: permission.getId(),
        name: permission.getName()
    };
}

function knowledgeToObject(knowledge) {
    var tags = [];

    for (var i = 0; i < knowledge.getTags().size(); i++) {
        tags.push(knowledge.getTags().get(i).getName());
    }

    return {
        id: knowledge.getId(),
        employee: knowledge.getOwner(),
        employeeId: knowledge.getOwner().getId(),
        employeeName: knowledge.getOwner().getFullName(),
        tags: tags,
        voteSum: knowledge.getVoteSum()
    };
}

function wikiKnowledgeToObject(knowledge) {
    var ret = knowledgeToObject(knowledge);
    ret.usecases = [];

    for (var i = 0; i < knowledge.getUseCaseList().size(); i++) {
        ret.usecases.push(knowledge.getUseCaseList().get(i));
    }

    ret.title = knowledge.getTitle();
    ret.content = knowledge.getContent();
    ret.attachment = knowledge.getAttachment();
    ret.isApproved = knowledge.isApproved();
    ret.isDeprecated = knowledge.isDeprecated();

    return ret;
}

function questionKnowledgeToObject(knowledge) {
    var ret = knowledgeToObject(knowledge);

    ret.title = knowledge.getTitle();
    ret.content = knowledge.getContent();

    return ret;
}

function answerKnowledgeToObject(knowledge) {
    var ret = knowledgeToObject(knowledge);
    ret.content = knowledge.getContent();
    return ret;
}

function letterToObject(letter){
    var nodes = [];
    for (var i = 0; i < letter.getLetterPathNodes().size(); i++) {
        nodes.push(letter.getLetterPathNodes().get(i).getName());
    }
    return {
        id: letter.getId(),
        content: letter.getContent(),
        title: letter.getTitle(),
        nodes: nodes
    };
}

function projectToObject(project){
    var activites = [];
    for (var i = 0; i < project.getProjectActivities().size(); i++) {
        var activity = project.getProjectActivities().get(i);
        activites.push({'title':activity.getTitle(), 'description':activity.getDescription()});
    }
    return {
        id: project.getId(),
        description: project.getDescription(),
        title: project.getTitle(),
        nodes: activites
    };
}

function tagToObject(tag) {
    return {
        id: tag.getId(),
        name: tag.getName()
    };
}

function abuseToObject(abuse) {
    return {
        id: abuse.getId(),
        knowledge: abuse.getKnowledge(),
        knowledgeType: abuse.getKnowledge().getKnowledgeType().ordinal(),
        knowledgeId: abuse.getKnowledge().getId(),
        employeeName: abuse.getReporter().getFullName(),
        employeeId: abuse.getReporter().getId(),
        content: abuse.getContent()
    };
}

function itemPermissionsToObject(perms) {
    return {
        change: perms.canChange(),
        add: perms.canAdd(),
        delete: perms.canDelete(),
        deprecate: perms.canDeprecate(),
        clone: perms.canClone(),
        report: perms.canReport(),
        vote: perms.canVote()
    };
};/*
 msg = message to be shown
 type= type of message(error, success, warning, notice)
 is_stickey = True or False (default=False)
 duration = duration of message (default=
 */
function show_message(msg, type, is_sticky, duration) {
    if (!duration)duration = 3000;
    var t = $().toastmessage({
        text: msg,
        sticky: is_sticky,
        type: type,
        position: 'top-left',
        stayTime: duration
    });
    $().toastmessage('showToast', t);
}

