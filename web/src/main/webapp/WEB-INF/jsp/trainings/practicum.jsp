<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 05.07.14
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

                <h3>Практикум для желающих</h3>

                <ul>
                    <li>Работа над Open source проэктом</li>
                    <ul>
                        <li>После того, как вы закончили обучение в It-center, у вас есть возможность</li>
                        <li>участвовать в написании проекта под руководством преподавателя</li>
                        <li>Условия такого участия обсуждеются в индивидуальном порядке</li>
                    </ul>
                </ul>

                <br/>

            </div>

        </div>
        <!-- tabs -->


    </div>

    <footer id="footer"></footer>
</div>
<%@include file="../body.jsp" %>

</body>
</html>