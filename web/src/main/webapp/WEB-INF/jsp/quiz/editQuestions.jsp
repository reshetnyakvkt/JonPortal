<!--
Created by Reshetnyak Viktor on 25.01.2016!
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div ng-cloak>
<h4>Тест:</h4>
<div class="panel">
    <form name="quizForm">
        <div class="form-inline">
            <div class="form-group">
                <textarea rows="2" cols="70" class="form-control" ng-model="quiz.description" required placeholder="Название теста"></textarea>
            </div>
            <label>Время в мин</label>
            <div class="form-group">
                <input type="number" class="form-control" required ng-model="timeLimitMins"/>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-8">
                    <button type="submit" class="btn btn-success" ng-click="saveExit()">Сохранить<br>и выйти</button>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <button type="submit" class="btn btn-danger" ng-click="exit()">Выйти без<br>сохранения</button>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="page-header">
    <h4>Список вопросов</h4>
</div>
<div class="panel">
    <form name="questionForm">
        <div class="form-inline">
            <div class="form-group">
                <textarea rows="2" cols="70" class="form-control" ng-model="newQuestionText" placeholder="Вопрос"></textarea>
            </div>
            <div class="form-group">
                <label for="typeSelect">Тип вопроса: </label>
                <select class="form-control" name="typeSelect" id="typeSelect"
                        ng-options="option.name for option in questionTypes.availableValues track by option.id"
                        ng-model="questionTypes.selectedValue"></select>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-8">
                    <button class="btn btn-success" ng-click="newQuestion(newQuestionText, questionTypes.selectedValue.id)">Добавить вопрос</button>
                </div>
            </div>
        </div>
    </form>
    <table class="table table-bordered">
        <thead>
        <tr class="active">
            <th>Вопрос</th>
            <th>Тип ответа</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="question in quiz.questions">
            <td>{{question.name}}<br>
                <div ng-controller="answersEditController" ng-show="{{showAnswers(question.questionType)}}">
                    <div class="form-inline" name="answerForm">
                        <div class="form-group">
                            <input class="form-control" ng-model="newAnswerText" placeholder="Ответ"/>
                        </div>
                        <div class="form-group">
                            <label>Верный</label>
                            <input type="checkbox" class="form-control" ng-model="newAnswerCorrect"/>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-8">
                                <button class="btn btn-success btn-sm"
                                        ng-click="newAnswer(question.answerList, question.questionType, newAnswerText, newAnswerCorrect)">
                                    Добавить ответ
                                </button>
                            </div>
                        </div>
                    </div>

                    <table class="table table-condensed" class="active">
                        <thead>
                        <tr class="active">
                            <th>Ответ</th>
                            <th>Верный</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tr ng-repeat="answer in question.answerList">
                            <td>{{answer.name}}</td>
                            <td>
                                <input type="checkbox" ng-model="answer.isCorrect"
                                       ng-click="setCorrectAnswer(question.answerList, question.questionType, answer)"/>
                            </td>
                            <td><button class="btn btn-warning btn-sm"
                                        ng-click="delAnswer(question.answerList, question.questionType, answer)">Удалить ответ</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
            <td>{{getQuestionTypeNameByValue(question.questionType)}}
            </td>
            <td><button class="btn btn-danger" ng-click="delQuestion(question)">Удалить<br>вопрос</button></td>
        </tr>
        </tbody>
    </table>
</div>
</div>