<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 12.05.15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@include file="includes.jsp"%>
  <%--<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>--%>

  <script>
    $(function() {
      $( "button" )
              .button()
              .click(function( event ) {
                event.preventDefault();
                alert('click');
              });
    });
  </script>
</head>
<body>


<div id="container" class="container-fluid">
  <%@include file="header.jsp" %>

  <div class="row">
    <div class="col-md-9 col-md-push-3">.col-md-9 .col-md-push-3</div>
    <div class="col-md-3 col-md-pull-9">.col-md-3 .col-md-pull-9</div>
  </div>
  <div class="well">
    <div class="bar-info">
      Возможности удаленной работы:
    </div>
    <%--<div class="col-md-4 col-md-offset-4">.col-md-4 .col-md-offset-4</div>--%>
    <div class="row">
      <div class="col-md-4">.col-md-4</div>
      <button class="col-md-4 col-md-offset-4 btn btn-danger">A button element</button>
    </div>
    <ul class="well nav nav-pills nav-stacked">
      <li>
        <a href="#">Задания</a>
      </li>
    </ul>
    <div class="bar-info">
      Задания для домашней работы:
    </div>

    <ul class="well nav nav-pills nav-stacked">
      <li class="slideable">
        <a htef="#">TaskGroup1</a>
        <ul class="well nav nav-pills nav-stacked">
          <li>
            <a href="/codeValidator/index">Java Code Validator</a>
          </li>
          <li>
            <a href="ftp://com.jon.com.ua">Скачать</a>
          </li>
        </ul>
      </li>
      <li class="slideable">
        <a htef="#">TaskGroup2</a>
        <ul class="well nav nav-pills nav-stacked">
          <li>
            <a href="#">SubStub 1</a>
          </li>
          <li>
            <a href="#">SubStub 2</a>
          </li>
          <li>
            <a href="#">SubStub 3</a>
          </li>
          <li>
            <a href="#">SubStub 4</a>
          </li>
        </ul>
      </li>
      <li class="slideable">
        <a htef="#">TaskGroup3</a>
        <ul class="well nav nav-pills nav-stacked">
          <li>
            <a href="#">SubStub 1</a>
          </li>
          <li>
            <a href="#">SubStub 2</a>
          </li>
          <li>
            <a href="#">SubStub 3</a>
          </li>
          <li>
            <a href="#">SubStub 4</a>
          </li>
        </ul>
      </li>
    </ul>

  </div>

  <footer id="footer"></footer>
</div>
<%@include file="body.jsp" %>
</body>
</html>
