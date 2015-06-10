/**
 * Created by al1 on 10.06.15.
 */
define([], function (TasksView) {
    function getCourseRate(groupId, callback) {
        $.ajax({
            url: "courseRate",
            dataType: 'json',
            data: { groupId: groupId },
            success: callback
        });
    }

    function getSprintRate(groupId, sprintId, callback) {
        $.ajax({
            url: "sprintRate",
            dataType: 'json',
            data: { groupId: groupId, sprintId: sprintId },
            success: callback
        });
    }

    return {
        getCourseRate:getCourseRate,
        getSprintRate:getSprintRate
    };
});