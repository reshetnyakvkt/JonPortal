<%--
  Created by Reshetnyak Viktor on 05.02.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<link href="../../css/style.css" rel="stylesheet" type="text/css">--%>
<div class="dash">
    <p class="text-right small">
        {{currentUser.id}},{{currentUser.login}}
        <button type="button" class="btn btn-success btn-xs" ng-click="toProfilePage()">Profile</button>
    </p>
    <div class="panel">
        <h3>Тесты</h3>
        <h5>{{readError}}</h5>
        <div ng-repeat="quiz in quizList">
            <button type="button" class="btn btn-default btn-block " ng-click="goToQuiz(quiz.id)">
                <p class="text-left"><strong>{{quiz.description}}</strong></p>
                <p class="text-right"><small>вопросов {{quiz.questions.length}}, время {{quiz.timeLimit / 60 | number:0}} мин</small></p>
            </button>
        </div>
    </div>
</div>
