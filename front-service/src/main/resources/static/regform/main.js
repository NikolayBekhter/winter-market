angular.module('market').controller('authController', function ($scope, $http, $localStorage, $location) {

    $scope.tryToAuth = function () {
        console.log($scope.user)
        $http.post('http://localhost:5555/auth/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $location.path('/');

                }
            });
    };

    $scope.tryToReg = function () {
        $http.post('http://localhost:5555/auth/users', $scope.new_user)
            .then(function (response) {
                alert('Аккаунт успешно создан!')
                $location.path('/auth');
            });
    };

});
