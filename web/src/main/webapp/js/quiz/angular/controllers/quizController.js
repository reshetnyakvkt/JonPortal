/**
 * Created by Олег on 17.01.2016.
 */

quizApp.controller('quizController', ['$scope', '$routeParams', 'users', 'quizzes', '$timeout', '$location',
    function($scope, $routeParams, users, quizzes, $timeout, $location) {
        var quizId = $routeParams.quizId;
        $scope.activeQuestion = {
            rest: 0,
            question: {}
        };
        $scope.questionsAnswered = 0;
        $scope.timerCount = 0;
        $scope.isRunning = false;
        $scope.currentUser = users.getCurrentUser();
        $scope.lastResult = {
            isExists: false,
            userQuizAnswers: [],
            score : 0
        };

        $scope.getButtonType = function(answerQuestion){
            if (answerQuestion.answered){
                return 'btn-default'
            } else {
                return 'btn-primary'
            }
        };
        $scope.getInputType = function(question){
            switch(question.questionType){
                case 'YES_OR_NO':
                    return 'radio';
                    break;
                case 'SINGLE_CHOICE':
                    return 'radio';
                    break;
                case 'MULTIPLE_CHOICE':
                    return 'checkbox';
                    break;
            }
        }

        $scope.chooseAnswer = function(idx) {
            $scope.activeQuestion.question = $scope.questionList[idx];
            $scope.activeQuestion.index = idx;
        };

        $scope.confirmAnswer = function() {
            $scope.questionList[$scope.activeQuestion.index].answered = !$scope.questionList[$scope.activeQuestion.index].answered;
        };

        $scope.finalize = function() {
            $scope.isRunning = false;
            sendResults();
        };

        quizzes.fetchQuizData(quizId).success(function(data) {
            $scope.quiz = data.quiz;
            $scope.userQuiz = data.userQuiz;
            $scope.quizName = $scope.quiz.description;
            $scope.timeLimit = $scope.quiz.timeLimit;
            if ($scope.userQuiz != null){
                $scope.questionList = $scope.userQuiz.questions;
                $scope.isRunning = $scope.userQuiz.isRunning;
            }
            if ($scope.isRunning && $scope.timeLimit > 0) {
                $scope.initCountDown();
            }
        })
            .error(function(err) {
                $scope.readError = err;
            });

        $scope.initCountDown = function() {
            var timerCount = $scope.timeLimit;
            var countDown = function() {
                if (timerCount <= 0) {
                    //$scope.activeQuestion = $scope.totalQuestions + 1;
                    $scope.finalize();
                } else {
                    $scope.countDownLeft = timerCount;
                    $scope.countMinutesLeft = Math.floor($scope.countDownLeft / 60);
                    $scope.countSecondsLeft = $scope.countDownLeft % 60;
                    timerCount--;
                    $timeout(countDown, 1000);
                }
            };
            $scope.countDownLeft = timerCount;
            countDown();
        };

        var sendResults = function() {
            quizzes.sendQuizData($scope.quiz, $scope.userQuiz, $scope.isRunning)
                .success(function(data) {
                    if (data !== undefined && data != null) {
                        $scope.userQuiz.id = data;
                    } else {
                        alert('Ошибка отправки данных на сервер!');
                    }
                })
                .error(function(err) {
                    $scope.message = err;
                })
        };

        $scope.startQuiz = function() {
            $scope.isRunning = true;
            sendResults();

            $scope.activeQuestion.rest = $scope.questionList.length;
            $scope.chooseAnswer(0);

            if ($scope.timeLimit > 0) {
                $scope.initCountDown();
            }
        };
        $scope.toDashboard = function() {
            $location.path('/dashboard');
        }
    }]);