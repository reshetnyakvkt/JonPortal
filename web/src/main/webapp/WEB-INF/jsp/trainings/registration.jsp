<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 4/11/14
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

                <h3>Инструкция по регистрации</h3>

                <ul>
                    <li>Регистрация состоит из двух шагов:</li>
                    <ul>
                        <li>Регистрация на сайте <b>Ассембла</b></li>
                        <li>Регистрация на сайте <b>Джон</b></li>
                    </ul>
                </ul>

                <ul>
                    <li>Регистрация на Ассембле:</li>
                    <ul>
                        <li>Выслать мне на скайп адрес почтового ящика, куда вы готовы принять приглашение</li>
                        <li>Получить приглашение на почту</li>
                        <li>Перейдя по ссылке в письме приглашении</li>
                        <li>Зарегистрироваться, введя в качестве логина свои name_surname, через нижнее подчеркивание</li>
                        <li>Учтите, что пароль должен содержать большие, маленькие буквы, цифры (пароль надо запомнить)</li>
                    </ul>
                    <li>Регистрация на Джоне:</li>
                    <ul>
                        <li>На главной странице надо перейти по ссылке "Зарегистрироваться" (под "Запомнить меня")</li>
                        <li>При регистрации нужно ввести свой логин и пароль, такие же, как на ассембле</li>
                        <li>После регистрации вам необходимо перейти на главную страницу и зайти в свой кабинет</li>
                    </ul>
                </ul>
                <br/>
            </div>
        </div>


    </div>

    <footer id="footer"></footer>
</div>


</body>
</html>

