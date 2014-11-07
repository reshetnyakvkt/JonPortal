<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes.jsp" %>
</head>
<body>
<%@include file="body.jsp" %>

<div id="container" class="container-fluid">

    <%@include file="header.jsp" %>
    <div class="row-fluid">
        <div class="span3 well">
            <h4 class="error">Вход</h4>

            <c:if test="${not empty error}">
                <div class="alert alert-error">
                        <%--Your login attempt was not successful, try again.<br /> Caused : --%>
                        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </div>
            </c:if>

            <form name="lform" method="POST" action="/login">
                <input type="text" id="username" class="span12" name="j_username" placeholder="Логин"/>
                <br/>
                <input type="password" id="password" class="span12" name="j_password" placeholder="Пароль"/>
                <label class="checkbox">
                    Запомнить меня

                <input type="checkbox" name="_spring_security_remember_me" /></label>

                <a href="/register.html">Зарегистрироваться</a>
                <button type="submit" name="submit" class="btn btn-info btn-block">
                    Войти
                </button>
            </form>

        </div>
        <div class="span9 well">
            <div id="tittleText" class="alert alert-success lead text-info">

                Портал для тех кто связался с Java
            </div>
            <div class="well">
                <p>В разделе <span class="label">Статьи</span> находятся полезные для обучающихся статьи</p>

                <p>В разделе <span class="label">Занятия</span> находится список литературы и видеоуроки</p>

                <p>В разделе <span class="label">Задачи</span> находится система проверки заданий для курса Foundation</p>

                <p>В разделе <span class="label">Cкачать</span> находятся нужные для занятий файлы</p>
            </div>

            <div class="alert alert-success lead text-info">
                Новости
            </div>

            <div class="well">

                <p>Обновлён дизайн by Stefan ©</p>

                <p>Добавлены видеоуроки для курса Foundation... <a href="/lessons.html">перейти</a></p>

                <p>Добавлена мультимодульная проверка для курса Professional</p>

                <p>Улучшен дизайн открытой части портала (Благодаря Stefan &#0169;)</p>

                <p>Добавлена статья "Вредоносный код"... <a href="/trainings/nedocode.html">перейти</a></p>

                <p>Закончена миграци на Git</p>

                <p>Закончена миграция на Java 8</p>

                <p>Добавлена проверка для заданий по работе с файлами</p>

                <p>Сделана проверка заданий для курса "Foundation"</p>

                <p>Сделана интеграция с SVN</p>
            </div>

        </div>
    </div>

    <%--<div class="well">

        <div class="well">
            Добро пожаловать на портал com.jon.com.ua
        </div>
        <div class="bar-info">
            Возможности, помогающие в работе:
        </div>

        <ul class="well nav nav-pills nav-stacked">
            <li>
                <a htef="#">Confluence</a>
            </li>
            <li>
                <a htef="#">SVN</a>
            </li>
            <li>
                <a htef="#">Assembla</a>
            </li>
            <li>
                <a htef="#">Samba</a>
            </li>
        </ul>

    </div>
--%>
</div>


<footer id="footer"></footer>

</body>
</html>
