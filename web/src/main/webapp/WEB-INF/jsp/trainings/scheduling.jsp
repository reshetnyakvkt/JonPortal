<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 4/18/13
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
                <li class="active">
                    <a href="/trainings/scheduling.html">Расписание</a>
                </li>
                <li>
                    <a href="/trainings/rules.html">Правила</a>
                </li>
                <li>
                    <a href="#">Записаться</a>
                </li>
                <li>
                    <a href="/trainings/reviews.html">Отзывы</a>
                </li>
            </ul>
            <div class="tab-content">

                <table class="table table-striped table-bordered">
                    <caption>
                        <h4>Расписание тренинга "Основы баз данных"</h4>
                    </caption>
                    <thead>
                    <tr>
                        <th>День</th>
                        <th>Дата</th>
                        <th>Описание</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>21 апреля (17.00 - 20.00)</td>
                        <td>Введение в БД</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Ориентировочно 12 мая (17.00 - 20.00)</td>
                        <td>JDBC</td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>
        <!-- tabs -->


    </div>

    <footer id="footer"></footer>
</div>


</body>
</html>
