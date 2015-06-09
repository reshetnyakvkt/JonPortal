/**
 * Created by al1 on 06.06.15.
 */
define(['jquery', "datatables", "DT-bootstrap", "bootstrap", "codemirror/lib/codemirror"], function(JQuery, table, tableboot, bootstrap,CodeMirror) {
    var selectedGroup;
    var selectedSprint;
    var selectedTask;
    var codeEditor;

    function renderSprints(group) {
        if (group == undefined) {
            return;
        }
        var sprints = group.sprints;
        for (var i = 0; i < sprints.length; i++) {
            var sprint = sprints[i];
            $('#sprints').append($('<li><a name="' + sprint.id + '" href="#">' + sprint.name + '</a></li>'));
            if (i != sprints.length - 1) {
                $('#sprints').append($('<li class="divider"></li>'));
            }
        }
    }

    function renderCode(task) {
        var textArea = document.getElementById("code");

        textArea.textContent = task.code;
        codeEditor = CodeMirror.fromTextArea(textArea, {
            lineNumbers: true,
            //styleActiveLine: true,
            lineWrapping: true,
            theme: "vibrant-ink",
            mode: "text/x-java",
            matchBrackets: true
        });
    }

    function renderTask(task) {
        if (task == undefined) {
            return;
        }
        $('#taskText').html(task.text.replace(/\n/g, '<br/>'));
        $('#result').html(task.rate);
        renderCode(task);
    }

    function render(parameters){
        var groups = JSON.parse(parameters);
        renderGroups(groups);
        selectedGroup = groups[0];
        selectedSprint = selectedGroup.sprints[0];
        renderSprints(selectedGroup);
        selectedTask = selectedSprint.tasks[0];
        renderTask(selectedTask);
        var table = buildTable(selectedSprint.tasks);
        addHandlers(table);
    }

    return {
        render:render
    };

    function buildTable(tasks) {
        if (tasks == undefined) {
            return;
        }
        var table = $('#table_id').DataTable({
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
                {"data": "mark"},
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
                "defaultContent": "<button class='btn btn-default glyphicon glyphicon-play'></button>"
            },
                {width: '80%', targets: 0}
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

    function addHandlers(table) {
        $('#table_id tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
            renderTask(table.row(this).data());
        });

        $('#table_id tbody').on('click', 'button', function () {
            var data = table.row($(this).parents('tr')).data();
            alert(data[0] + "'s salary is: " + data[5]);
        });

        $('#result').on('click', function() {

            $('#modal').modal('toggle');

        });

    }

    function renderGroups(groups) {
        for (var i = 0; i < groups.length; i++) {
            var group = groups[i];
            $('#groups').append($('<li><a name="' + group.id + '" href="#">' + group.name + '</a></li>'));
            if (i != groups.length - 1) {
                $('#groups').append($('<li class="divider"></li>'));
            }
        }
    }

});