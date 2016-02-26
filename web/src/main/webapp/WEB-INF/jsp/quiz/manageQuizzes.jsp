<%--
 Created by Reshetnyak Viktor on 25.01.2016.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="quizAdminApp" lang="en">
<head>
    <meta charset="utf-8">
    <link href="../../../img/buttonRun.png" rel="icon" type="image/x-icon">
    <link rel="stylesheet" href="../../../css/bootstrap.min.css">
    <link href="../../../css/codemirror/codemirror.css" rel="stylesheet" media="screen">
    <link href="../../../css/custom.css" rel="stylesheet" media="screen">
    <link href="../../../css/codemirror/vibrant-ink.css" rel="stylesheet" media="screen">
    <title>jon.com.ua</title>
</head>
<body>
<div class="back">
    <div id="battlestar" class="logo"></div>
</div>
<div id="container" class="container-fluid">
    <%@include file="../header.jsp" %>
    <br/>
    <div class="row well">
        <h3 align="center">Панель управления тестами</h3>
        <ng-view></ng-view>
    </div>
</div>
<footer id="footer"></footer>
<script src="../../../js/angular/angular.min.js"></script>
<script src="../../../js/angular/angular-route.min.js"></script>
<script src="../../../js/quiz/angular/quizAdminApp.js"></script>
<script src="../../../js/quiz/angular/services/dataService.js"></script>
<script src="../../../js/quiz/angular/controllers/quizzesEditController.js"></script>
<script src="../../../js/quiz/angular/controllers/questionsEditController.js"></script>
<script src="../../../js/quiz/angular/controllers/answersEditController.js"></script>
</body>
</html>