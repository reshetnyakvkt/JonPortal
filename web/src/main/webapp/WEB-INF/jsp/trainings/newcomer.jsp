<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03.09.13
  Time: 12:44
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

                <h3>Полезное для начинающих программистов</h3>

                <ul>
                    <li>Литература:</li>
                    <ul>
                        <li>Кей Хорстман, Гарри Корнелл. Библиотека профессионала. Java 2</li>
                        <li>Брюс Эккель. Философия Java</li>
                        <li>Фредерик Брукс. Мифический человеко-месяц</li>
                        <li>Патрик Ноутон, Герберт Шилдт. Java 2</li>
                        <li>Гради Буч. Объектно-ориентированный анализ и проектирование</li>
                        <li>Тимоти Бадд. Объектно-ориентированное программирование в действии</li>
                        <li>Эльдар Хабибулин. Самоучитель Java 2</li>
                    </ul>
                    <li>Правила обучения</li>
                    <ul>
                        <li>Приходить во время</li>
                        <li>Вести себя интеллегентно, уважая других студентов и преподавателя</li>
                        <li>Выключать телефоны</li>
                        <li>Задавая вопросы, старайтесь придерживаться темы обсуждения</li>
                    </ul>

                </ul>

                <p>Советы для успешного обучения:</p>

                <ul>
                    <li>Читайте много, пару часов в день</li>
                    <li>Интересуйтесь компьютерами, программированием, системами, алгоритмами</li>
                    <li>Много программируйте (минимум 3 часа в день)</li>
                    <li>Общайтесь с целеустремленными, ответственными студентами</li>
                </ul>

                <p>Полезные ссылки:</p>

                <ul>
                    <li><a href="http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/keplerr">Eclipse</a></li>
                    <li><a href="https://netbeans.org/downloads/">NetBeans</a></li>
                    <li><a href="http://www.jetbrains.com/idea/download/">Intellij Idea</a></li>
                    <li><a href="http://www.oracle.com/technetwork/java/codeconventions-150003.pdf">Стандарт именования идентификаторов</a></li>
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