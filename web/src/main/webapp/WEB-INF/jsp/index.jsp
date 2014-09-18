<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes.jsp" %>
    <style>
        .errorblock {
            text-align: center;
            color: #ff0000;
            background-color: #ffEEEE;
            border: 2px solid #ff0000;
            padding: 1px;
            margin: 1px;
        }
    </style>
</head>
<body onload='document.lform.j_username.focus();'>


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
                <input type="password" id="password" class="span12" name="j_password" placeholder="Имя"/>
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
            <div class="alert alert-success lead text-info">
                Портал для тех кто связался с Java
            </div>

            <div class="well">
                <p>В разделе <span class="label">Cкачать</span> находятся нужные для занятий файлы</p>

                <p>В разделе <span class="label">Статьи</span> находятся полезные для обучающихся статьи</p>

                <p>В разделе <span class="label">Занятия</span> находится список литературы</p>

                <p>В разделе <span class="label">Задания</span> можно проверять сделанные задачи</p>
            </div>
            <div class="well">
                <h3>Новости</h3>

                <p>Улучшен дизайн открытой части портала (Благодаря Stefan &#0169;)</p>

                <p><a href="/trainings/nedocode.html">Добавлена статья "Вредоносный код"</a></p>

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
