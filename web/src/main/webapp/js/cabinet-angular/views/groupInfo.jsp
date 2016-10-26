<%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 08.02.2016
--%>
<link href="../../../css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../../../css/custom.css" rel="stylesheet" media="screen">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>

    <div class="col-md-12">
<nav class="navbar navbar-default">
    <div class="navbar-header">
        <span class="navbar-brand">Группа</span>
    </div>

<div class="collapse navbar-collapse">
    <ul class="nav navbar-nav">

        <li uib-dropdown>
            <a id="group" href="#" ng-cloak uib-dropdown-toggle role="button" aria-expanded="false">{{selectedGroup.name}} <span class="caret"></span></a>
            <ul uib-dropdown-menu role="menu" id="groups">
                <li ng-repeat="group in groups">
                    <a href ng-click="selectGroup(group)" ng-cloak>{{group.name}}</a>
                </li>
            </ul>
        </li>
        <li>
            <span class="navbar-brand">Общий рейтинг группы</span>
        </li>
        <li><p id="courseRate" class="navbar-text" ng-cloak>{{groupRating}}</p></li>

    </ul>

</div>
</nav>
    </div>
<div class="col-md-12">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Студент</th>
            <th>Общий рейтинг</th>
            <th>1</th>
            <th>2</th>
            <th>3</th>
            <th>4</th>
            <th>5</th>
            <th>6</th>
            <th>7</th>
            <th>8</th>
            <th>9</th>
            <th>10</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="student in studentsInfo.slice(((currentPage-1)*itemsPerPage), ((currentPage)*itemsPerPage))">
            <td>{{student[0]}}</td>
            <td>{{showMark(student[1])}}</td>
            <td>{{showMark(student[2])}}</td>
            <td>{{showMark(student[3])}}</td>
            <td>{{showMark(student[4])}}</td>
            <td>{{showMark(student[5])}}</td>
            <td>{{showMark(student[6])}}</td>
            <td>{{showMark(student[7])}}</td>
            <td>{{showMark(student[8])}}</td>
            <td>{{showMark(student[9])}}</td>
            <td>{{showMark(student[10])}}</td>
            <td>{{showMark(student[11])}}</td>
        </tr>
        </tbody>
    </table>
<uib-pagination items-per-page="itemsPerPage" total-items="studentsInfo.length" ng-model="currentPage"></uib-pagination>


</div>

</div>