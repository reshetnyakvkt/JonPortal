<%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 08.02.2016
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../css/custom.css" rel="stylesheet" media="screen">


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-12">

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Название</th>
                <th>Студент</th>
                <th>Оценка</th>
                <th>Рейтинг</th>
            </tr>
        </thead>
        <tbody>
        <tr ng-repeat="task in tasksDTO">
            <td>{{task.name}}</td>
            <td>{{task.userName}}</td>
            <td>{{task.result.substring(0, task.result.indexOf('\n'))}}</td>
            <td>{{task.rate}}</td>
        </tr>
        </tbody>
    </table>


</div>

