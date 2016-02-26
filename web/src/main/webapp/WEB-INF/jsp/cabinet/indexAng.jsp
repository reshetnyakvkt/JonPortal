<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 06.06.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link href="/img/buttonRun.png" rel="icon" type="image/x-icon">
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet" media="screen">
    <link href="/css/codemirror/codemirror.css" rel="stylesheet" media="screen">
    <link href="/css/custom.css" rel="stylesheet" media="screen">
    <link href="/css/codemirror/vibrant-ink.css" rel="stylesheet" media="screen">

    <title>jon.com.ua</title>
    <script src="../../../js/angular.js" type="text/javascript"></script>


</head>
<body ng-app="CabinetApp">

<div class="back">
    <div id="battlestar" class="logo"></div>
</div>

<div id="container" class="container-fluid" ng-controller="cabinetController">
    <!-- Nav tabs -->


    <%@include file="../header.jsp" %>
    <br/>

    <div class="row well">
        <div class="col-md-12">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="Tooltip on bottom">
                    <span class="navbar-brand"><b id="student">{{username}}</b></span>
                </div>

                <div class="navbar-header">
                    <span class="navbar-brand">Рейтинг</span>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><p id="courseRate" class="navbar-text">80</p></li>
                        <li><p id="sprintRate" class="navbar-text">90</p></li>
                        <li class="dropdown">
                            <a id="group" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">{{selectedGroup.name}} <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu" id="groups">

                            </ul>
                        </li>
                        <li class="dropdown">
                            <a id="sprint" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Этап <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu" id="sprints">

                            </ul>
                        </li>

                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/cabinet/index1.html">Старая версия</a></li>
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
                            <a data-toggle="collapse" data-parent="#tableAccordion" href="#collapseTable">Список заданий</a>
                        </h4>
                    </div>
                    <div id="collapseTable" class="panel-collapse collapse in">
                        <%--<div>--%>
                            <div id="tasks">
                                <table id="table_id" class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Название задания</th>
                                        <th>Оценка</th>
                                        <th></th>
                                    </tr>
                                    </thead>
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
                                <a data-toggle="collapse" data-parent="#resAccordion" href="#collapseRes">Результат проверки</a>
                            </h4>
                        </div>
                        <div id="collapseRes" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div id="result"></div>
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
                            <a data-toggle="collapse" data-parent="#textAccordion" href="#collapseOne">Текст задания</a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <div id="taskText"></div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="panel-group" id="codeAccordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#codeAccordion" href="#collapseTwo">Код решения</a>
                            <%--<button id="format" type="button" class="btn btn-default">Форматировать</button>--%>
                            <button id="save" data-toggle="tooltip" data-placement="bottom" title="Запомнить решение" class='btn btn-default'><span class='glyphicon glyphicon-floppy-disk'></span></button>
                        </h4>

                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <textarea id="code" name="code">
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
    </div>

</div>
<script src="../../../js/cabinet-angular/cabinetApp.js" type="text/javascript"></script>
<script src="../../../js/cabinet-angular/services/users.js" type="text/javascript"></script>
<script src="../../../js/cabinet-angular/services/sprints.js" type="text/javascript"></script>
<script src="../../../js/cabinet-angular/controllers/CabinetController.js" type="text/javascript"></script>


<footer id="footer"></footer>




</body>
</html>
