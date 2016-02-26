/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 */
function Answer(name, isCorrect){
    this.name = name;
    this.isCorrect = isCorrect;
}
function Question(name, questionType){
    this.name = name;
    this.questionType = questionType;
    this.answerList = [];
}

quizAdminApp.controller("questionsEditController", function ($scope, $http, $location, $templateCache, $routeParams, dataService) {
    $scope.quiz = dataService.getEditQuiz();
    if ($scope.quiz !== undefined && $scope.quiz !== null){
        $scope.timeLimitMins = Math.round($scope.quiz.timeLimit / 60);
    }

    $scope.exit = function(){
        $location.path("editQuizzes");
    }
    if ($scope.quiz === undefined || $scope.quiz == null){
        $scope.exit();
    }
    $scope.newQuestionText='';
    $scope.questionTypes = dataService.getQuestionTypes();
    $scope.getQuestionTypeNameByValue = function(value){
        return dataService.getQuestionTypeNameByValue(value);
    }
    $scope.newQuestion = function (text, q_type) {
        if (text !== undefined && text != "") {
            if (q_type === undefined || q_type == null) {
                q_type = 0;
            }
            var item = new Question(text, $scope.questionTypes.types[q_type]);
            $scope.quiz.questions.push(item);
            $scope.refreshNew();
        }
    }
    $scope.delQuestion = function(del_item){
        if (del_item === undefined) return;
        var idx = $scope.quiz.questions.indexOf(del_item);
        if (idx !== undefined && idx >= 0){
            $scope.quiz.questions.remove(idx);
        }
    }
    $scope.refreshNew = function(){
        $scope.newQuestionText = '';
    }
    $scope.refreshNew();
    $scope.saveExit = function(){
        $scope.quiz.timeLimit = $scope.timeLimitMins * 60;
        var promiseObj = dataService.saveQuiz($scope.quiz);
        promiseObj.then(function(value) {
            $location.path("editQuizzes");
        });
    }
});