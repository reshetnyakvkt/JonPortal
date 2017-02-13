/**
 * Created by ���� on 28.01.2016.
 */
cabinetApp.factory('users', ['$http', function($http) {

    return {
        userName: function() {
            return $http.get('/cabinet/userName', {transformResponse: undefined})
                .then(function(response) {
                    return response.data;
                },function(err) {
                    return err;
                })
        },

        userRoles: function() {
            return $http.get('/cabinet/userRoles')
                .then(function(response) {
                    return response.data;
                },function(err) {
                    return err;
                })
        }






    };

}]);