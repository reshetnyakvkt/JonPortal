/**
 * Created by ���� on 28.01.2016.
 */
cabinetApp.factory('sprints', ['$http', function($http) {

    var groupList;
    var currentGroup;
    var currentSprint;
    var currentTask;


    return {

        getGroups: function() {
            return groupList;
        },

        setGroups: function(groups) {
           groupList  = groups;
        },

        getCurrentGroup: function() {
            return currentGroup;
        },

        setCurrentGroup: function(group) {
            currentGroup = group;
        },

        getCurrentSprint: function() {
            return currentSprint;
        },
        setCurrentSprint: function(sprint) {
            currentSprint = sprint;
        },

        getCurrentTask: function() {
            return currentTask;
        },
        setCurrentTask: function(task) {
            currentTask = task;
        },


        tasks: function() {
            return $http.get('/cabinet/tasks')
                .then(function(response) {
                    groupList = response.data;
                    return response.data;
                }, function(err) {
                    return err;
                })
        },

        courseRate: function(groupId) {
            return $http.get('/cabinet/courseRate?groupId=' + groupId, {transformResponse: undefined})
                .then(function(response) {
                    return response.data;
                }, function(err) {
                    return err;
                })
        },

        sprintRate: function(groupId, sprintId) {
            return $http.get('/cabinet/sprintRate?groupId=' + groupId + '&sprintId=' + sprintId, {transformResponse: undefined})
                .then(function(response) {
                    return response.data;
                }, function(err) {
                    return err;
                })
        },
        checkTask: function(taskId, type, code) {
            var data = {
                taskId: taskId,
                type: type,
                code: code
            };
            return $http({
                method: 'POST',
                url:'/cabinet/checkTask',
                params: data,
                transformResponse: undefined

            }).then(function(response) {
                return response.data;
            }, function(err) {
                return "-\nВремя ожидания ответа от сервера истекло, перегрузите страницу позже";
            });

        }


    };

}]);