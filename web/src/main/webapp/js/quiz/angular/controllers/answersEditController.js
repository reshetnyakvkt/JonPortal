/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 */
quizAdminApp.controller("answersEditController", function ($scope, dataService) {
    $scope.newAnswerText;
    $scope.newAnswerCorrect;
    $scope.questionType = 0;
    $scope.getQuestionTypeByValue = function(value){
        return dataService.getQuestionTypeByValue(value);
    }
    $scope.showAnswers = function(questionType){
        var value = null;
        if (questionType !== undefined && questionType != null) {
            value = $scope.getQuestionTypeByValue(questionType);
            if (value != 0) {
                return true;
            }
        }
        return false;
    }
    $scope.newAnswer = function (answers, questionType, text, isCorrect) {
        if (text !== undefined && text != "") {
            var item = new Answer(text, isCorrect);
            answers.push(item);
            this.setCorrectAnswer(answers, questionType, item);
            $scope.refreshNew();
        }
    }
    $scope.delAnswer = function(answers, questionType, del_item){
        if (del_item === undefined) return;
        var idx = answers.indexOf(del_item);
        if (idx !== undefined && idx >= 0){
            answers.remove(idx);
            if (answers.length > 0){
                this.setCorrectAnswer(answers, questionType, answers[0]);
            }
        }
    }
    $scope.setCorrectAnswer = function(answers, questionType, set_item){
        var item_is_correct;
        var item;
        var questionTypeValue = $scope.getQuestionTypeByValue(questionType);
        if (questionTypeValue == 1){
            if ( set_item !== undefined && set_item.isCorrect) {
                item_is_correct = set_item;
            }
        }
        for (var i = 0; i < answers.length; i++) {
            item = answers[i];

            if (questionTypeValue == 1) {
                if (item_is_correct === undefined){
                    if (item.isCorrect) {
                        item_is_correct = item;
                    }
                } else {
                    if (item_is_correct !== item){
                        item.isCorrect = false;
                    }
                }
            }
        }
        if (questionTypeValue == 1 && item_is_correct === undefined) {
            if (set_item !== undefined){
                set_item.isCorrect = true;
            } else {
                if (answers.length > 0){
                    answers[0].isCorrect = true;
                }
            }
        }
    }
    $scope.refreshNew = function(){
        $scope.newAnswerText = '';
        $scope.newAnswerCorrect = false;
    };
    $scope.refreshNew();
});
