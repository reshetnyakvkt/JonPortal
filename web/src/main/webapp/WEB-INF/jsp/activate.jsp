<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes.jsp" %>
</head>
<body onload='document.lform.j_username.focus();'>

<%@include file="header.jsp" %>

<div id="container" class="container-fluid">

    <div class="row-fluid">
        <div class="span3 well">
            <h4 class="error">Ваш аккаунт успешно активирован</h4>

            <c:if test="${not empty message}">
                <div class="alert alert-error">
                        ${message}
                </div>
            </c:if>

            <form name="lform" method="POST" action="/login">
                <div class="form-group">
                    <label>Логин:</label>
                    <input type="text" id="username" class="form-control" name="j_username" placeholder="Логин" value="${login}"/>
                </div>
                <div class="form-group">
                    <label>Пароль:</label>
                    <input type="password" id="password" class="form-control" name="j_password" placeholder="Пароль" value="${pass}"/>
                </div>
                <button type="submit" name="submit" class="btn btn-default btn-block">
                    Войти
                </button>
            </form>

        </div>
    </div>

</div>

<footer id="footer"></footer>

</body>
</html>
