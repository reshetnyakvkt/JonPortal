/**
 * Created by ���� on 28.01.2016.
 */
var cabinetApp = angular.module('CabinetApp', ['ui.bootstrap', 'ngAnimate', 'ngRoute']);
cabinetApp.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'cabinetController',
            templateUrl: '/js/cabinet-angular/views/user.jsp'
        })
        .when('/groupTask', {
            controller: 'groupTaskController',
            templateUrl: '/js/cabinet-angular/views/groupTask.jsp'
        })
        .when('/groupInfo', {
            controller: 'groupInfoController',
            templateUrl: '/js/cabinet-angular/views/groupInfo.jsp'
        })
        .otherwise({
            redirectTo: '/'
        });
});
