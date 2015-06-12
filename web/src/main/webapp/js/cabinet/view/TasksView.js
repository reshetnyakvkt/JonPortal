/**
 * Created by al1 on 06.06.15.
 */
define(['jquery', "datatables", "DT-bootstrap", "bootstrap", "codemirror/lib/codemirror", "controller/Communication"], function (JQuery, Table, tableboot, bootstrap, CodeMirror, Communication) {
    var selectedGroup;
    var selectedSprint;
    var selectedTask;
    var codeEditor;
    var table;
    var groups;

    var constructor = function () {
        var textArea = document.getElementById("code");
        codeEditor = CodeMirror.fromTextArea(textArea, {
            lineNumbers: true,
            //styleActiveLine: true,
            lineWrapping: true,
            theme: "vibrant-ink",
            mode: "text/x-java",
            matchBrackets: true
        });
    };
    constructor();

    function renderTasks(tasks) {
        table.fnClearTable();
        table.fnAddData(tasks);
        table.fnDraw();

        $('#table_id tbody tr:first-child').addClass('selected');
        renderTask(tasks[0]);
    }

    function renderTasksFromSprint(sprint) {
        renderTasks(sprint.tasks);
    }

    function renderGroups(groups, group) {
        Communication.getCourseRate(selectedGroup.id, function (data) {
            $('#courseRate').html(data);
        });
        $('#group').html(group.name + '<span class="caret">');
        if (groups.length > 0) {
            $('#groups').empty();
        }
        for (var i = 0; i < groups.length; i++) {
            var group = groups[i];
            $('#groups').append($('<li><a name="' + group.id + '" href="#">' + group.name + '</a></li>'));

            if (i != groups.length - 1) {
                $('#groups').append($('<li class="divider"></li>'));
            }
        }
    }

    function renderSprints(group, selectSprint) {
        if (group == undefined) {
            return;
        }
        Communication.getSprintRate(group.id, selectSprint.id, function (data) {
            $('#sprintRate').html(data);
        });

        var sprints = group.sprints;
        if (sprints.length > 0) {
            $('#sprints').empty();
        }
        $('#sprint').html(selectSprint.name + '<span class="caret">');
        for (var i = 0; i < sprints.length; i++) {
            var sprint = sprints[i];
            $('#sprints').append($('<li><a name="' + sprint.id + '" href="#">' + sprint.name + '</a></li>'));
            if (i != sprints.length - 1 && sprints.length < 6) {
                $('#sprints').append($('<li class="divider"></li>'));
            }
        }

        $('#sprints li a').on('click', function () {
            $('#sprint').html(this.innerHTML + '<span class="caret">');
            var sprint = getSprintById(selectedGroup, this.name);
            if (sprint != undefined) {
                selectedSprint = sprint;
                Communication.getSprintRate(selectedGroup.id, sprint.id, function (data) {
                    $('#sprintRate').html(data);
                });

                renderTasksFromSprint(sprint);
            }
        });

        //var dataTable = $('#table_id').dataTable();
        renderTasksFromSprint(selectSprint)
    }

    function renderCode(task) {
        //$('.CodeMirror').remove();
        //textArea.textContent = task.code;
        codeEditor.setValue(task.code);
    }

    function renderTask(task) {
        if (task == undefined) {
            return;
        }
        $('#taskText').html(task.text.replace(/\n/g, '<br/>'));
        $('#result').html(task.result.replace(/\n/g, '<br/>'));
        renderCode(task);
    }

    function render(parameters) {
        groups = JSON.parse(parameters);
        selectedGroup = groups[0];

        renderGroups(groups, selectedGroup);
        selectedSprint = selectedGroup.sprints[0];

        table = buildTable(selectedSprint.tasks);
        renderSprints(selectedGroup, selectedSprint);
        /*
         selectedTask = selectedSprint.tasks[0];
         renderTask(selectedTask);
         */

        addHandlers(table);
    }

    return {
        render: render
    };

    function buildTable(tasks) {
        if (tasks == undefined) {
            return;
        }
        table = $('#table_id').dataTable({
            "paging": false,
            "ordering": false,
            "info": false,
            "searching": false,
            //"ajax": "tasks",
            /*            "ajax": function (data, callback, settings) {
             callback(
             parameters
             );
             },*/

            "columns": [
                {"data": "name"},
                {"data": "result"},
                {"data": "action"}
            ],
            /*
             "aoColumns": [
             { "sWidth": "70%" },
             { "sWidth": "25%" },
             { "sWidth": "5%", "sClass": "center", "bSortable": false }
             ],
             */

            "columnDefs": [{
                "targets": -1,
                "data": null,
                "defaultContent": "<button class='btn btn-default play'><span class='glyphicon glyphicon-play'></span></button>"
            },
                {
                    width: '80%',
                    targets: 0
                },
                {
                    width: '10%',
                    targets: 1,
                    render: function (data, type, full, meta) {
                        return data.substring(0, data.indexOf('\n'));
                    }
                }
            ],
            "aaData": tasks
            /*            "aaData": [
             ['Internet Explorer 4.0', 4, '[Проверить]'],
             ['Internet Explorer 4.0', 4, '[]'],
             ['Internet Explorer 4.0', 4, '[]'],
             ['Internet Explorer 4.0', 4, '[]'],
             ['Internet Explorer 4.0', 4, '[]'],
             ['Internet Explorer 4.0', 4, '[]'],
             ['Internet Explorer 4.0', 4, '[]'],
             ['Internet Explorer 5.0', 5, '[]']
             ],*/
            /*            "aoColumns": [
             { "sTitle": "Название задания" },
             { "sTitle": "Оценка" },
             { "sTitle": "" }
             ]*/
        });
        return table;
    }

    function getGroupById(groupId) {
        for (var i = 0; i < groups.length; i++) {
            if (groups[i].id == groupId) {
                return groups[i];
            }
        }
    }

    function getSprintById(selectedGroup, sprintId) {
        var sprints = selectedGroup.sprints;
        for (var i = 0; i < sprints.length; i++) {
            if (sprints[i].id == sprintId) {
                return sprints[i];
            }
        }
    }

    function addHandlers(table) {
        $('#table_id tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
            renderTask(table.fnGetData(this));
        });

        /*        $('#table_id tbody').on('click', 'button', function () {
         var data = table.row($(this).parents('tr')).data();
         alert(data[0] + "'s salary is: " + data[5]);
         });*/

        $('#groups li a').on('click', function () {
            $('#group').html(this.innerHTML + '<span class="caret">');
            var group = getGroupById(this.name);
            if (group != undefined) {
                selectedGroup = group;
                renderSprints(group, group.sprints[0]);
            }
        });

        $('.play').on('click', function () {
            var tr = this.parentNode.parentNode;
            var task = table.fnGetData(tr);
            var button = $(this);
            //button.disabled = true;
            button.attr("disabled", "disabled");

            var playIcon = $(button.children(0));
            playIcon.toggleClass("glyphicon-play");
            playIcon.toggleClass("glyphicon-refresh glyphicon-refresh-animate");
            task.code = codeEditor.getValue();
            Communication.checkTask(task.id, task.type, codeEditor.getValue(), function (data) {
                if (data) {
                    task.result = data;
                    table.fnUpdate(data, tr, 1);
                    table.fnDraw();
                    $('#result').html(data.replace(/\n/g, '<br/>'));
                    task.result = data;
                }

                button.removeAttr("disabled");
                playIcon.toggleClass("glyphicon-refresh glyphicon-refresh-animate");
                playIcon.toggleClass("glyphicon-play");

            });
        });


        $('#result').on('click', function () {
            $('#modalRes').html($(this).html());
            $('#modal').modal('toggle');
        });

    }
});