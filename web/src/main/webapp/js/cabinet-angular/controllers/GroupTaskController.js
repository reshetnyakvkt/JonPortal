/**
 * Created by Олег on 08.02.2016.
 */
cabinetApp.controller('groupTaskController', ['$scope', '$location', 'sprints', 'groups', function($scope, $location, sprints, groups) {
    $scope.tasksDTO = [];
    $scope.currentPage = 1;
    $scope.itemsPerPage = 1;

    var currentTask = sprints.getCurrentTask();
    var currentGroup = sprints.getCurrentGroup();
    var currentSprint = sprints.getCurrentSprint();
    if (currentTask === undefined || currentGroup === undefined || currentSprint === undefined) {
        $location.path('/');
    } else {
        groups.tasksByUserGroup(currentTask.taskTemplateId,
            currentGroup.id, currentSprint.id)
            .then(function(data) {
                $scope.tasksDTO = data;
            }, function(err) {
                $scope.error = err;
            });
    }



}]);