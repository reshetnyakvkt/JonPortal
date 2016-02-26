<link href="../../../img/buttonRun.png" rel="icon" type="image/x-icon">
<link href="../../../css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../../../css/codemirror/codemirror.css" rel="stylesheet" media="screen">
<link href="../../../css/custom.css" rel="stylesheet" media="screen">
<link href="../../../css/codemirror/vibrant-ink.css" rel="stylesheet" media="screen">
<script src="../../../js/cabinet-angular/codemirror/lib/codemirror.js" type="text/javascript"></script>

<%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 08.02.2016
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="Tooltip on bottom">
                <span class="navbar-brand" ng-cloak><b id="student">{{username}}</b></span>
            </div>

            <div class="navbar-header">
                <span class="navbar-brand">Рейтинг</span>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><p id="courseRate" class="navbar-text" ng-cloak>{{userCourseRate}}</p></li>
                    <li><p id="sprintRate" class="navbar-text" ng-cloak>{{userSprintRate}}</p></li>
                    <li uib-dropdown>
                        <a id="group" href="#" uib-dropdown-toggle role="button" aria-expanded="false" ng-cloak>{{selectedGroup.name}} <span class="caret"></span></a>
                        <ul uib-dropdown-menu role="menu" id="groups">
                            <li ng-repeat="group in groups">
                                <a href ng-click="selectGroup(group)">{{group.name}}</a>
                            </li>
                        </ul>
                    </li>
                    <li uib-dropdown>
                        <a id="sprint" href="#" uib-dropdown-toggle role="button" aria-expanded="false" ng-cloak>{{selectedSprint.name}} <span class="caret"></span></a>
                        <ul uib-dropdown-menu role="menu" id="sprints">
                            <li ng-repeat="sprint in sprints">
                                <a href ng-click="selectSprint(sprint)">{{sprint.name}}</a>
                            </li>
                        </ul>
                    </li>

                </ul>
                <ul class="nav navbar-nav navbar-right" ng-show="isAdmin()">
                    <li><a href="/quiz/manageQuizzes">Админка тестов</a></li>
                </ul>


            </div><!-- /.navbar-collapse -->

        </div><!-- /.container-fluid -->
    </nav>
</div>

<div class="col-md-4">
    <div class="panel-group" id="tableAccordion">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a ng-click="elementCollapse.table = !elementCollapse.table">Список заданий</a>
                </h4>
            </div>
            <div uib-collapse="elementCollapse.table">
                <%--<div>--%>
                <div id="tasks">
                    <table id="table_id" class="table <%--table-striped--%> table-bordered" ng-cloak>
                        <thead>
                        <tr>
                            <th>Название задания</th>
                            <th>Оценка</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="task in userTasks" ng-click="selectTask(task)"
                            class="{{getCabinetCtrlScope().selectedTask === task ? 'selectedTask' : ''}}">
                            <td>{{task.name}}</td>
                            <td>{{task.result.substring(0, task.result.indexOf('\n'))}}</td>
                            <td ng-click="checkUserTask(task)">
                                <button  data-toggle='tooltip' data-placement='bottom' title='Проверить
                                            задание' class='btn btn-default play'><span class="{{task.taskUnchecked ? 'glyphicon glyphicon-refresh glyphicon-refresh-animate'
                                            : 'glyphicon glyphicon-play'}}"></span></button>
                            </td>
                        </tr>

                        </tbody>
                    </table>
                </div>

                <%--</div>--%>
            </div>
        </div>
    </div>
    <div class="list-group">
        <div class="panel-group" id="resAccordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a ng-click="elementCollapse.result = !elementCollapse.result">Результат проверки</a>
                    </h4>
                </div>
                <div uib-collapse="elementCollapse.result">
                    <div class="panel-body">
                        <div id="result" ng-cloak>{{selectedTask.result}}</div>
                    </div>
                </div>
            </div>
        </div>


    </div>

    <div id="modal" class="modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Результат проверки</h4>
                </div>
                <div id="modalRes" class="modal-body">
                    One fine body…
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                </div>
            </div>
        </div>
    </div>


</div>
<div class="col-md-8">
    <div class="panel-group" id="textAccordion">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a ng-click="elementCollapse.text = !elementCollapse.text">Текст задания</a>
                </h4>
            </div>
            <div uib-collapse="elementCollapse.text">
                <div class="panel-body">
                    <div id="taskText" ng-cloak>{{selectedTask.text}}</div>
                </div>
            </div>
        </div>
    </div>


    <div class="panel-group" id="codeAccordion">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a ng-click="elementCollapse.code = !elementCollapse.code">Код решения</a>
                    <button id="save" data-toggle="tooltip" data-placement="bottom" title="Запомнить решение" class='btn btn-default' ng-click="saveCode()">
                        <span class='glyphicon glyphicon-floppy-disk'></span>
                    </button>
                </h4>

            </div>
            <div uib-collapse="elementCollapse.code">
                <div class="panel-body">
                            <textarea id="code" name="code" >
                     class A {
                        public static
                    }
            </textarea>
                    <%--<div id="taskText">HTML stands for HyperText Markup Language. HTML is the main markup language for describing the structure of Web pages. <a href="http://www.tutorialrepublic.com/html-tutorial/" target="_blank">Learn more.</a></div>--%>
                </div>
            </div>
        </div>
    </div>


</div>