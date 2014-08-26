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
            <h4 class="error">Регистрация</h4>

            <c:if test="${not empty message}">
                <div class="alert alert-error">
                        <%--Your login attempt was not successful, try again.<br /> Caused : --%>
                        ${message}
                </div>
            </c:if>

            <form name="lform" method="POST" action="/register">
                Формат логина: imya_familiya
                <input type="text" id="username" class="span12" name="j_username" placeholder="Логин"/>
                <br/>
                <input type="password" id="password" class="span12" name="j_password" placeholder="Пароль"/>
                <select name="group" class="form-control span12">

                    <c:forEach var="group" items="${groups}">
                        <c:out value="${group}"/>
                        <div class="alert alert-error">
                            <option value="${group.id}">${group.name}</option>
                        </div>
                    </c:forEach>
<%--
                    <option value="one">One</option>
                    <option value="two">Two</option>
                    <option value="three">Three</option>
                    <option value="four">Four</option>
                    <option value="five">Five</option>
--%>
                </select>
                <input type="text" id="code" class="span12" name="code" placeholder="Код группы"/>
                <button type="submit" name="submit" class="btn btn-info btn-block">
                    Зарегистрироваться
                </button>
            </form>

        </div>
    </div>

</div>

<footer id="footer"></footer>


</body>
</html>
