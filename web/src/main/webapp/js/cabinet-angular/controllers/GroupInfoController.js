/**
 * Created by Олег on 08.02.2016.
 */
cabinetApp.controller('groupInfoController', ['$scope', '$location', 'groups', 'sprints', function($scope, $location, groups, sprints) {
    $scope.groups = [];
    $scope.selectedGroup = {};
    $scope.studentsInfo = [];
    $scope.groupRating = 0;
    $scope.currentPage = 1;
    $scope.itemsPerPage = 2;
    var availableGroups = sprints.getGroups();
    var selectedGroup = sprints.getCurrentGroup();

    var updateGroupInfo = function() {
        groups.groupInfo($scope.selectedGroup.id)
            .then(function(data) {
                $scope.studentsInfo = data;
                $scope.groupRating = getGroupRating();
            }, function(err) {
                $scope.error = err;
            });
    };

    if (selectedGroup === undefined || availableGroups === undefined) {
        $location.path('/');
    } else {
        $scope.groups = availableGroups;
        $scope.selectedGroup = selectedGroup;
        updateGroupInfo();
    }


    var getGroupRating = function() {
        var total = 0;
        for (var i = 0; i < $scope.studentsInfo.length; i++) {
            total += parseInt($scope.studentsInfo[i][1]);
        }

        return total / $scope.studentsInfo.length;
    };



    $scope.selectGroup = function(group) {
        if (group) {
            $scope.selectedGroup = group;
            sprints.setCurrentGroup(group);
            updateGroupInfo();
        } else {
            $scope.selectedGroup = sprints.getCurrentGroup();
            //          $scope.userCourseRate = 'N/A';
        }
    };

    $scope.showMark = function(item) {
        return item === undefined ? 'N/A' : item;
    }




}]);