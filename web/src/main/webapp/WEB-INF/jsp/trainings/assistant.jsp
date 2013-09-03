<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.09.13
  Time: 11:53
  To change this template use File | Settings | File Templates.
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
            <%@include file="menu.jsp" %>
            <div class="tab-content">

                <h3>Инструкция для помощников</h3>

                <ul>
                    <li>Цели:</li>
                    <ul>
                        <li>Формирование культуры разработчика (взаимоотношения, качество работы)</li>
                        <li>Оказание достаточной помощи подопечному для решения практических задач курса</li>
                        <li>Собственное развитие помощника</li>
                        <li>Помощь в развитии тренеру</li>
                    </ul>
                    <li>Правила</li>
                    <ul>
                        <li>Приходить за 5 минут до начала занятия</li>
                        <li>Если вы не можете прийти на занятие, нужно сообщить об этом заранее</li>
                        <li>Активный режим: Постоянное уделение внимания подопечным. Применяется если у подопечных есть
                            вопросы или им нужна помощь </li>
                        <li>Пассивный режим: Обходы один раз в 10-20 минут. Применяется если у студентов нет на данный
                            момент вопросов и они самостоятельно заняты решением задания</li>
                        <li>Активно участвовать в обсуждениях на форуме</li>
                        <li>Если у вас есть сложности с оказанием помощи, обсудите это с тренером</li>
                        <li>Если студент хронически не получает помощь, он должен обсудить это с тренером</li>
                        <li>Если помощник участвовал в проведении 3 курсов и имеет опыт работы 6 месяцев, он может
                            заменять тренера при проведении занятий</li>
                    </ul>

                </ul>

                <br/>

            </div>

        </div>
        <!-- tabs -->


    </div>

    <footer id="footer"></footer>
</div>


</body>
</html>
