/**
 * Created by Олег on 16.01.2016.
 */
function UserQuizResult(quiz, userQuiz){
    this.quiz = quiz;
    this.userQuiz = userQuiz;
};

quizApp.factory('quizzes', ['$http', function($http) {

    return {
        fetchQuizzes: function() {
            return $http.get(address + '/getQuizzes')
                .success(function(data) {
                    return data;
                })
                .error(function(err) {
                    return err;
                });
        },

        fetchQuizData: function(quizId) {
            return $http.get(address + '/getQuiz/' + quizId)
                .success(function(data) {
                    return data;
                })
                .error(function(err) {
                    return err;
                });
        },

        sendQuizData: function(quiz, userQuiz, isRunning) {
            var userQuizJson = angular.toJson(new UserQuizResult(quiz, userQuiz));

            console.log('userQuizJson: ' + userQuizJson);

            return $http({method: 'GET', url: '/submitUserQuiz',
                params: {'UserQuizAnswersJson': userQuizJson, 'IsRunning': isRunning}})
                .success(function(data) {
                    return data;
                })
                .error(function(err) {
                    return err;
                });
        }

    };

}]);