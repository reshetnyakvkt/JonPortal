/**
 * Created by Олег on 08.02.2016.
 */
cabinetApp.factory('groups', ['$http', function($http) {

    return {
        tasksByUserGroup: function(taskTemplateId, groupId, sprintId) {
            return $http.get('/cabinet/tasksByUserGroup?taskTemplate=' + taskTemplateId +
                '&groupId=' + groupId + '&sprintId=' + sprintId)
                .then(function(response) {
                    return response.data;
                }, function(err) {
                    return err;
                })
        },

        groupInfo: function(groupId) {
            return $http.get('/cabinet/groupInfo?groupId=' + groupId)
                .then(function(response) {
                    return response.data;
                }, function(err) {
                    return err;
                })
        }




    };

}]);