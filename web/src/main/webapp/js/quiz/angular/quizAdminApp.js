/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 */
var quizAdminApp = angular.module("quizAdminApp", ['ngRoute'])
    .config(function($routeProvider){

    $routeProvider.when('/editQuizzes', {
            templateUrl: 'editQuizzes.html',
            controller: 'quizzesEditController'
        });
    $routeProvider.when("/editQuiz", {
        templateUrl: "editQuestions.html",
        controller: 'questionsEditController'
    });
    $routeProvider.otherwise({redirectTo: '/editQuizzes'});

});

Array.prototype.remove = function(from, to) {
    var rest = this.slice((to || from) + 1 || this.length);
    this.length = from < 0 ? this.length + from : from;
    return this.push.apply(this, rest);
};