/**
 * Created by Юыху on 28.01.2016.
 */
cabinetApp.factory('sprints', ['$http', function($http) {

    return {
        tasks: function() {
            return $http.get('/cabinet/tasks')
                .then(function(response) {
                    console.log(response.data);
                    return response.data;
                }, function(err) {
                    return err;
                })
        },

        courseRate: function(groupId) {
            return $http.get('/cabinet/courseRate?groupId=' + groupId)
                .then(function(response) {
                    console.log(response.data);
                    return response.data;
                }, function(err) {
                    return err;
                })
        },

        sprintRate: function(groupId, sprintId) {
            return $http.get('/cabinet/courseRate?groupId=' + groupId + '&sprintId=' + sprintId)
                .then(function(response) {
                    console.log(response.data);
                    return response.data;
                }, function(err) {
                    return err;
                })
        }


    };

}]);