<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header id="header" role="banner" class="navbar">
    <div class="navbar-inner">
        <ul class="nav nav-pills">
            <li id="item1"><a href="/index.html">Главная</a></li>
            <li id="item2"><a href="/trainings/index.html">Статьи</a></li>
            <li id="item3"><a href="/lessons.html">Занятия</a></li>
            <li id="item4"><a href="/codeValidator/index">Задания</a></li>
            <li id="item6"><a href="/tasksg.html">Задания g</a></li>
            <li id="item5"><a href="/download.html">Скачать</a></li>
        </ul>
    </div>

    <script type="text/javascript">
        $(document).ready(function() {

            // управление анимацией меню
            <%--'это не ошибка. Просто смешение jQuery и JSTL синтаксиса--%>
            $('#<c:out value="${item}"/>').addClass("active").css('position','relative').css('top',-100).animate({top: 0},300);

            // управление анимацией основного контента
            $('#header').siblings('div').css('opacity',0).animate({opacity:1},600);
        })
    </script>

</header>
