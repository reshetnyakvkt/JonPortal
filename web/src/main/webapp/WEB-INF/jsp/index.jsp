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

<%@include file="header.jsp" %>

<div id="container" class="container-fluid">

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
                    <input type="checkbox" name="remember" value="1">
                    Запомнить меня </label>
                <button type="submit" name="submit" class="btn btn-info btn-block">
                    Войти
                </button>
            </form>

        </div>
        <div class="span9 well">
            <div class="alert alert-success lead text-info">
                Добро пожаловать на портал jon.com.ua
            </div>

            <div class="well">
                Этот портал предназначен для тех кто изучает Java.<br/>

                <p>В разделе <span class="label">Cкачать</span> находятся нужные для занятий файлы.</p>

                <p>Сейчас доступ для скачивания возможен через:</p>
                <ul class="nav nav-pills nav-stacked">
                    <li>
                        <a htef="#"><strong>HTTP</strong></a>
                    </li>
                    <li>
                        <a htef="#"><strong>FTP</strong></a>
                    </li>
                    <li>
                        <a htef="#"><strong>SMB</strong></a>
                    </li>
                </ul>
                <p>В разделе <span class="label">Занятия</span> находятся полезные материалы</p>

                <p>В разделе <span class="label">Задания</span> можно выполнять учебные задания</p>
            </div>

        </div>
    </div>

    <%--<div class="well">

        <div class="well">
            Добро пожаловать на портал jon.com.ua
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
