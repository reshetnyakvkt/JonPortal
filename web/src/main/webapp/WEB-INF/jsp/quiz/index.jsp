<%--
  Created by Reshetnyak Viktor on 05.02.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../../../img/buttonRun.png" rel="icon" type="image/x-icon">
    <link rel="stylesheet" href="../../../css/bootstrap.min.css">
    <link href="../../../css/codemirror/codemirror.css" rel="stylesheet" media="screen">
    <link href="../../../css/custom.css" rel="stylesheet" media="screen">
    <link href="../../../css/codemirror/vibrant-ink.css" rel="stylesheet" media="screen">
    <title>jon.com.ua</title>
</head>
<body ng-app="QuizApp">
<div class="back">
    <div id="battlestar" class="logo"></div>
</div>

<div id="container" class="container-fluid">
    <%@include file="../header.jsp" %>
    <br/>
    <div class="row well">
        <ng-view></ng-view>
    </div>
</div>
<footer id="footer"></footer>

<script src="../../../js/angular/angular.min.js"></script>
<script src="../../../js/angular/angular-route.min.js"></script>
<script src="../../../js/quiz/angular/quizApp.js"></script>
<script src="../../../js/quiz/angular/controllers/dashController.js"></script>
<script src="../../../js/quiz/angular/controllers/quizController.js"></script>
<script src="../../../js/quiz/angular/controllers/userController.js"></script>
<script src="../../../js/quiz/angular/services/quizzes.js"></script>
<script src="../../../js/quiz/angular/services/users.js"></script>
</body>
</html>