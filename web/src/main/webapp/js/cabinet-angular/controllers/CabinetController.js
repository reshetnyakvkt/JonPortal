/**
 * Created by Юыху on 28.01.2016.
 */
cabinetApp.controller('cabinetController', ['$scope', '$location', 'users', 'sprints', function($scope, $location, users, sprints) {

    $scope.username = 'Student';
    $scope.selectedGroup = {
        name: 'Group'
    };



    users.userName().then(function(data) {
        $scope.username = data;
    }, function(err) {
        $scope.error = err;
    });

    sprints.tasks().then(function(data) {
        $scope.groups = data;
        $scope.selectedGroup = data[0];
    }, function(err) {
        $scope.error = err;
    });


    $scope.selectGroup = function(group) {
        $scope.selectedGroup = group;
    };

    $scope.renderGroups = function(groups, group) {


    };






}]);