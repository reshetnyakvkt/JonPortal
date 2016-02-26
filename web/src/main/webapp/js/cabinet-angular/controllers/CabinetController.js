/**
 * Created by ���� on 28.01.2016.
 */
cabinetApp.controller('cabinetController', ['$scope', '$location', 'users', 'sprints', function($scope, $location, users, sprints) {
    var codeEditor;
    var userRoles = [];
    $scope.username = 'Student';
    $scope.userCourseRate = 80;
    $scope.userSprintRate = 90;
    $scope.groups = [];
    $scope.selectedGroup = {
        name: 'Group'
    };
    $scope.sprints = [];
    $scope.selectedSprint = {
        name: 'Sprint'
    };
    $scope.userTasks = [];
    $scope.selectedTask = {};
    $scope.elementCollapse = {
        table: false,
        result: false,
        text: false,
        code: false
    };

    users.userName().then(function(data) {
        $scope.username = data;
    }, function(err) {
        $scope.error = err;
    });

    users.userRoles().then(function(data) {
        userRoles = data;
    }, function(err) {
        $scope.error = err;
    });

    sprints.tasks().then(function(data) {
        $scope.groups = data;
        $scope.selectGroup(data[0]);
    }, function(err) {
        $scope.error = err;
    });

    $scope.selectGroup = function(group) {
        if (group) {
            $scope.selectedGroup = group;
            sprints.setCurrentGroup(group);
            updateGroupInfo();
        } else {
            $scope.selectedGroup = {
                name: 'Group'
            };
            $scope.userCourseRate = 'N/A';
        }
    };

    $scope.selectSprint = function(sprint) {
        if (sprint) {
            $scope.selectedSprint = sprint;
            sprints.setCurrentSprint(sprint);
            updateSprintInfo();
        } else {
            $scope.selectedSprint = {
                name: 'Sprint'
            };
            $scope.userSprintRate = 'N/A';
            $scope.userTasks = [];
            $scope.selectedTask = {};
        }
    };

    $scope.selectTask = function(task) {
        $scope.selectedTask = task;
        sprints.setCurrentTask(task);
        renderCode(task);
    };

    var updateGroupInfo = function() {
        var id = $scope.selectedGroup.id;
        sprints.courseRate(id).then(function(data) {
            $scope.userCourseRate = data;
        });
        $scope.sprints = $scope.selectedGroup.sprints;
        $scope.selectSprint($scope.sprints[0]);
    };

    var updateSprintInfo = function() {
        var groupId = $scope.selectedGroup.id;
        var sprintId = $scope.selectedSprint.id;
        sprints.sprintRate(groupId, sprintId).then(function(data) {
            $scope.userSprintRate = data;
        });
        $scope.userTasks = $scope.selectedSprint.tasks;
        $scope.selectTask($scope.userTasks[0]);
    };

    //copy-pasted from previous version

    var constructor = function () {
        var textArea = document.getElementById("code");
        codeEditor = CodeMirror.fromTextArea(textArea, {
            viewportMargin: Infinity,
            lineNumbers: true,
            lineWrapping: true,
            theme: "vibrant-ink",
            mode: "text/x-java",
            matchBrackets: true
        });
    };
    constructor();

    $scope.getCabinetCtrlScope = function() {
        return $scope;
    };

    function renderCode(task) {
        codeEditor.setValue(task.code);
    }

    $scope.saveCode = function() {
        $scope.selectedTask.code = codeEditor.getValue();
        console.log($scope.selectedTask.code);
    };

    $scope.checkUserTask = function(task) {
        $scope.selectTask(task);
        $scope.selectedTask.taskUnchecked = true;
        sprints.checkTask(task.id, task.type, task.code)
            .then(function(data) {
                $scope.selectedTask.taskUnchecked = false;
                console.log(data);
                $scope.selectedTask.result = data;
            }, function(err) {
                $scope.selectedTask.taskUnchecked = false;
                $scope.selectedTask.result = err;
            });
    };

    $scope.isAdmin = function() {
        for(var i = 0; i < userRoles.length; i++) {
            var auth = userRoles[i].authority;
            if (auth === 'ROLE_ADMIN') {
                return true;
            }
        }
        return false;
    }



}]);