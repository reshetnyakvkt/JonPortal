/**
 * Created by Олег on 16.01.2016.
 */
User = function(id, login, password, isAdmin){
    this.id = id;
    this.login = login;
    this.password = password;
    this.isAdmin = isAdmin;
}

quizApp.controller('dashController', ['$scope', 'quizzes', 'users', '$location', function($scope, quizzes, users, $location) {
    $scope.currentUser = users.getCurrentUser();
    if($scope.currentUser !== undefined && $scope.currentUser !== null) {
        if ($scope.currentUser.roles !== undefined && $scope.currentUser.roles !== null
            && $scope.currentUser.roles.indexOf('ADMIN')) {
            console.log('dashController-2');
            $scope.isAdmin = true;
        }
    } else {
        //после отладки убрать ----------------------
        console.log('dashController - create anonymous(viktor_reshetnyak/ADMIN)');
        $scope.currentUser = new User(3, 'viktor_reshetnyak', '1', true);
        $scope.isAdmin = true;
        users.setCurrentUser($scope.currentUser);
        //--------------------------------------------
    }

    quizzes.fetchQuizzes().success(function(data) {
        $scope.quizList = data;
    })
        .error(function(err) {
            $scope.readError = err;
        });

    $scope.goToQuiz = function(id) {
        $location.path('/goToQuiz/' + id);

    };
    $scope.toProfilePage = function() {
        $location.path('/userData');
    }
}]);