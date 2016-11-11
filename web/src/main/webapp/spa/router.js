/**
 * Created by dev on 11/10/16.
 */

var routerApp = angular.module('app', ['ui.router']);

routerApp .config(function($stateProvider, $locationProvider,  $urlRouterProvider) {

    $locationProvider.html5Mode({ enabled: true, requireBase: false });
    $urlRouterProvider.otherwise('/');

    $stateProvider

        .state('about', {
            url: '/about',
            component: 'about',
        })

        .state('hello', {
            url: '/hello',
            component: 'hello',
        });

})
    .component('about', {
        templateUrl: 'spa/templates/home.html'
    })
    .component('hello', {
        templateUrl: 'spa/templates/helloWorld.html',

        controller: function() {
            this.greeting = 'hello';

            this.toggleGreeting = function() {
                this.greeting = (this.greeting == 'hello') ? 'whats up' : 'hello'
            }
        }
    });
