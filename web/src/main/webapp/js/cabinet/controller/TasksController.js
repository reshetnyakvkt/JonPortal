/**
 * Created by al1 on 06.06.15.
 */
define(['view/TasksView'], function(TasksView){

    function start(){
        var tasks = localStorage.tasks;
        TasksView.render(tasks);
    }

    return {
        start:start
    };
});