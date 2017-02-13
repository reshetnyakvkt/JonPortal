<%--
  Created by Reshetnyak Viktor on 05.02.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="quizContainer" ng-cloak>
    <h5>{{currentUser.login}}</h5>
    <div class="panel">
        <h4>{{quizName}}</h4>

        <div ng-show="isRunning">У Вас осталось {{countMinutesLeft}} мин : {{countSecondsLeft}} сек.</div>

        <div ng-hide="isRunning">
            <h5>Ограничение по времени: {{timeLimit / 60 | number:0}} минут</h5>
            <div class="form-group">
                <div class="btn btn-default" ng-click="toDashboard()">← Назад</div>
                <div class="btn btn-warning" ng-click="startQuiz()">Начать тест</div>
            </div>
        </div>

        <form class="form-group" ng-if="isRunning">
            <table class="table"><tr>
                <td width="40px">
                    <div ng-repeat="item in questionList">
                        <button type="button" class="btn {{getButtonType(item)}} btn-block" ng-click="chooseAnswer($index)">
                            {{$index + 1}}
                        </button>
                    </div>
                </td>
                <td>
                    <form class="form-horizontal">
                        <label>{{activeQuestion.question.name}}</label><br>
                        <div class="form-group" ng-repeat="answer in activeQuestion.question.answers">
                            <input type="{{getInputType(activeQuestion.question)}}" ng-model="answer.answered">
                            {{answer.name}}
                            </input>
                        </div>
                    </form>
                </td>
            </tr></table>
            <div class="form-group">
                <div class="btn btn-warning" ng-click="finalize()">Закончить</div>
                <div class="btn btn-primary" ng-click="confirmAnswer()">{{activeQuestion.question.answered ? "Отменить ответ" : "Ответить"}}</div>
            </div>
        </form>
    </div>

    <div class="panel" ng-if="lastResult.isExists">
        <div class="results {{activeQuestion === totalQuestions + 1 ? 'active' : 'inactive'}}">
            <h4>Результат</h4>
            <p>Ваш балл: {{percentage * 100}} %</p>
            <p>Всего вопросов: {{totalQuestions}}</p>
            <p>Правильных ответов: {{score}}</p>
            <button class="btn btn-default" ng-click="toDashboard()"> Назад </button>
        </div>
        <div align="center"  class="progress {{(activeQuestion >= 0 && activeQuestion < totalQuestions) ? 'active' : 'inactive'}}">
            <div class="qItem {{questionList[$index].questionState == 'answered' ? 'answered' : '' }}" ng-repeat="question in questionList"
                 ng-click="getQuizCtrlScope().activeQuestion = $index">
                {{$index + 1}}
            </div>
        </div>
    </div>
</div>