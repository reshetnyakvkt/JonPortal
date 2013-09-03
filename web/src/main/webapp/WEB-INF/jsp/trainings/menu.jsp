<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.09.13
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav nav-tabs">
    <li id="mitem6"><a href="/trainings/newcomer.html"><button class="btn btn-link" type="button">Начинающим</button></a></li>
    <li id="mitem1"><a href="/trainings/list.html"><button class="btn btn-link" type="button">Тренинг по Oracle</button></a></li>
    <li id="mitem2"><a href="/trainings/scheduling.html"><button class="btn btn-link" type="button">Расписание</button></a></li>
    <li id="mitem3"><a href="/trainings/rules.html"><button class="btn btn-link" type="button">Правила участия в тренингах</button></a></li>
    <li id="mitem4"><a href="/trainings/assistant.html"><button class="btn btn-link" type="button">Для помощников</button></a></li>
    <li id="mitem5"><a href="/trainings/reviews.html"><button class="btn btn-link" type="button">Отзывы</button></a></li>
</ul>

<script type="text/javascript">
    $(document).ready(function() {

        // управление анимацией меню
        <%--'это не ошибка. Просто смешение jQuery и JSTL синтаксиса--%>
        $('#<c:out value="${item}"/>').addClass("active").css('position','relative').css('top',-100).animate({top: 0},300);
    })
</script>