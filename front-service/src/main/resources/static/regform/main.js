angular.module('market').controller('authController', function ($scope, $http, $localStorage, $location, $rootScope) {
    const contextPath = 'http://localhost:5555/auth/';
    // закомментировано, использовать для удаленного подключения
    // const contextPath = 'http://95.165.90.118:2190/auth/';

    $scope.tryToAuth = function () {
        $http.post(contextPath + 'auth', $scope.user)
            .then(function successCallback(response) {
                console.log(response)
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $rootScope.mergeCart();

                    $location.path('/');

                }
            });
    };

    $scope.tryToReg = function () {
        $http.post(contextPath + 'registration', $scope.new_user)
            .then(function (response) {
                alert('Аккаунт успешно создан!')
                $location.path('/auth');
            })
    };

});
