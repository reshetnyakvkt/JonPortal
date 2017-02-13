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
    <%--<link href="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet" media="screen">--%>
    <link href="/css/codemirror/codemirror.css" rel="stylesheet" media="screen">
    <link href="/css/custom.css" rel="stylesheet" media="screen">
    <link href="/css/codemirror/vibrant-ink.css" rel="stylesheet" media="screen">

    <title>jon.com.ua</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js" type="text/javascript"></script>

</head>
<body ng-app="CabinetApp">

<div class="back">
    <div id="battlestar" class="logo"></div>
</div>

<div id="container" class="container-fluid"<%-- ng-controller="cabinetController"--%>>
    <!-- Nav tabs -->


    <%@include file="../header.jsp" %>
    <br/>

    <div class="row well">

        <div class="col-md-12">
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <span class="navbar-brand">
                        <a href="/cabinet/indexAng.html#/"> Ваши задания</a>

                    </span>
                </div>

                <div class="navbar-header">
                    <span class="navbar-brand">
                        <a href="/cabinet/indexAng.html#/groupTask"> Задания группы</a>
                    </span>
                </div>

                <div class="navbar-header">
                    <span class="navbar-brand">
                        <a href="/cabinet/indexAng.html#/groupInfo">Группа</a>
                    </span>
                </div>

                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/cabinet/index1.html">Старая версия</a></li>
                    </ul>
                </div>


            </nav>
        </div>
        <div ng-view></div>

    </div>


</div>
<script src="/js/cabinet-angular/codemirror/lib/codemirror.js" type="text/javascript"></script>
<script src="/js/cabinet-angular/cabinetApp.js" type="text/javascript"></script>
<script src="/js/cabinet-angular/services/users.js" type="text/javascript"></script>
<script src="/js/cabinet-angular/services/sprints.js" type="text/javascript"></script>
<script src="/js/cabinet-angular/services/groups.js" type="text/javascript"></script>
<script src="/js/cabinet-angular/controllers/CabinetController.js" type="text/javascript"></script>
<script src="/js/cabinet-angular/controllers/GroupTaskController.js" type="text/javascript"></script>
<script src="/js/cabinet-angular/controllers/GroupInfoController.js" type="text/javascript"></script>
<script src="/js/cabinet-angular/libs/ui-bootstrap-tpls-1.1.2.min.js" type="text/javascript"></script>
<script src="/js/cabinet-angular/libs/angular-animate.min.js" type="text/javascript"></script>
<script src="/js/cabinet-angular/libs/angular-route.min.js" type="text/javascript"></script>
<footer id="footer"></footer>




</body>
</html>
