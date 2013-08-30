<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 4/20/13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../includes.jsp" %>
</head>
<body>

<div id="container" class="container-fluid">
    <%@include file="../header.jsp" %>


    <div class="well">

        <!-- tabs -->
        <div class="tabbable tabs-left">
            <ul class="nav nav-tabs">
                <li>
                    <a href="/trainings/index.html">Все тренинги</a>
                </li>
                <li>
                    <a href="/trainings/scheduling.html">Расписание</a>
                </li>
                <li>
                    <a href="/trainings/rules.html">Правила</a>
                </li>
                <li>
                    <a href="#">Записаться</a>
                </li>
                <li class="active">
                    <a href="/trainings/reviews.html">Отзывы</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="well">
                    Отзыв
                </div>
            </div>

        </div>
        <!-- tabs -->


    </div>

    <footer id="footer"></footer>
</div>


</body>
</html>
