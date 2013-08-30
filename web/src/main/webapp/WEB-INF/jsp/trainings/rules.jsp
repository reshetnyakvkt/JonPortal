<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 4/21/13
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
                <li class="active">
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
                <h3>Основные правила участия в онлайн тренингах</h3>
                <ul>
                    <li>Тренинг проходит с 17.00 по 20.00 по Киевскому времени</li>
                    <li>За 15 минут до занятия начинается проверка подключений</li>
                    <li>Занимаемся с перерывом 10 минут, с 18.30 до 18.45</li>
                    <li>Тренер рассказывает теорию, затем все выполняют практическое задание</li>
                    <li>Если вы ничего не говорите в данный момент то хорошим тоном будет "поставить микрофон
                        на <i>mute</i>"</li>
                    <li>При выполнении практического задания тренер может подключаться к вашему рабочему столу,
                        для этого нужно подтвердить запрос тренера на подключение</li>
                    <li>После подключения тренера можно дать управление рабочим столом, для этого нужно нажать кнопку
                        с курсором мыши напротив имени тренера в окне TeamViewer</li>
                    <li>После окончания тренинга, хорошим тоном будет оставить свой отзыв, отправив его на скайп теренеру</li>
                </ul>

            </div>

        </div>
        <!-- tabs -->


    </div>

    <footer id="footer"></footer>
</div>


</body>
</html>
