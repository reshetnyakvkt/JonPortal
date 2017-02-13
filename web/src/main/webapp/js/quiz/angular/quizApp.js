/**
 * Created by Олег on 16.01.2016.
 */
var quizApp = angular.module('QuizApp', ['ngRoute']);
var address = ''; //'http://192.168.1.136:8084';

quizApp.config(function ($routeProvider) {
    $routeProvider
        //.when('/', {
        //    controller: 'userController',
        //    //templateUrl: '../../static/views/authentication.html'
        //    templateUrl: 'authentication.html'
        //})
        //.when('/registerUser', {
        //    controller: 'userController',
        //    //templateUrl: '../../static/views/registerUser.html'
        //    templateUrl: 'registerUser.html'
        //})
        .when('/userData', {
            controller: 'userController',
            templateUrl: 'userData.html'
        })
        .when('/dashboard', {
            controller: 'dashController',
            templateUrl: 'dashboard.html'
        })
        .when('/goToQuiz/:quizId', {
            controller: 'quizController',
            templateUrl: 'quizPage.html'
        })
        .when('/editQuiz/:quizId', {
            controller: 'quizController',
            templateUrl: 'editQuiz.html'
        })
        .when('/testRun/:quizId', {
            controller: 'quizController',
            templateUrl: 'quizRun.html'
        })
        .otherwise({
            redirectTo: '/dashboard'
        });
});
