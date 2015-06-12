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

    function checkTask(taskId, type, code, callback) {
        $.ajax({
            url: "checkTask",
            timeout : 60000,
            //url: "getUpdate",
            //dataType: 'json',
            data: { taskId: taskId, type: type, code: code },
            error : function (a,b,c) {
                callback("-\nВремя ожидания ответа от сервера истекло");
            },
            success: callback
        });
    }

    return {
        getCourseRate:getCourseRate,
        getSprintRate:getSprintRate,
        checkTask:checkTask
    };
});