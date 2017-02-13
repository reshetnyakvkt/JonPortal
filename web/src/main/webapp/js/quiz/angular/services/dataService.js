/**
 * Created by Reshetnyak Viktor on 28.01.2016.
 */
quizAdminApp.factory('dataService', function($http, $q){
    var addressQuiz = '';
    var _quizzes = null;
    var _editQuiz = null;
    var questionTypes = {
        repeatSelect: 0,
        selectedValue: {id: 0, name: 'Логический: Да/Нет'},
        availableValues: [
            {id: 0, name: 'Логический: Да/Нет'},
            {id: 1, name: 'Единственный'},
            {id: 2, name: 'Множественный'}
        ],
        types: ['YES_OR_NO', 'SINGLE_CHOICE', 'MULTIPLE_CHOICE']
    };
    return{
        getQuestionTypes: function(){
            return questionTypes;
        },
        getQuestionTypeByValue: function(value){
            if (value !== undefined && value != null){
                return questionTypes.types.indexOf(value);
            } else {
                return null;
            }
        },
        getQuestionTypeNameByValue: function(value){
            if (value !== undefined && value != null) {
                var idx = this.getQuestionTypeByValue(value);
                if (idx !== undefined && idx != null)
                    if (questionTypes.availableValues[idx] !== undefined && questionTypes.availableValues[idx] != null){
                        return questionTypes.availableValues[idx].name;
                    }
            }
            return null;
        },
        setQuizzes: function (quizzes) {
            _quizzes = quizzes;
        },
        getQuizzes: function (quizzes) {
            _quizzes = quizzes;
        },
        setEditQuiz: function(quiz){
            _editQuiz = quiz;
        },
        getEditQuiz: function(){
            return _editQuiz;
        },
        editQuiz: function(quiz){
            this.setEditQuiz(quiz);
            var deferred = $q.defer();
            $http({method: 'GET', url: '#/editQuiz/'}).
                success(function(data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function(data, status, headers, config) {
                    deferred.reject(status);
                });
        },
        getData: function(){
            var deferred = $q.defer();
            $http({method: 'GET', url: 'j_quizzes'}).
                success(function(data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function(data, status, headers, config) {
                    deferred.reject(status);
                });
            return deferred.promise;
        },
        getQuizzes: function(){
            var deferred = $q.defer();
            $http({method: 'GET', url: addressQuiz + 'j_quizzes'}).
                success(function(data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function(data, status, headers, config) {
                    deferred.reject(status);
                });
            return deferred.promise;
        },
        newQuiz: function(text, limit){
            var deferred = $q.defer();
            $http({method: 'GET', url: addressQuiz + 'j_quiz_new', params: {'text': text, 'limit': limit}}).
                success(function(data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function(data, status, headers, config) {
                    deferred.reject(status);
                });
            return deferred.promise;
        },
        saveQuiz: function(quiz){
            var deferred = $q.defer();
            var quizJson = angular.toJson(quiz);

            $http({method: 'POST', url: addressQuiz + 'j_quiz_save', params: {'quizJson': quizJson}}).
                success(function(data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function(data, status, headers, config) {
                    deferred.reject(status);
                });
            return deferred.promise;
        },
        delQuiz: function(id){
            var deferred = $q.defer();
            $http({method: 'GET', url: addressQuiz + 'j_quiz_del',  params: {'id': id}}).
                success(function(data, status, headers, config) {
                    deferred.resolve(data);
                }).
                error(function(data, status, headers, config) {
                    deferred.reject(status);
                });
            return deferred.promise;
        }
    }
});