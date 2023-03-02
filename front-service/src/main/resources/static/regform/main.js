angular.module('market').controller('authController', function ($scope, $http, $localStorage, $location, $rootScope) {
    // использовать для локального подключения
    const contextPath = 'http://localhost:5555/auth/api/v1/';
    // использовать для удаленного подключения
    // const contextPath = 'http://95.165.90.118:443/auth/api/v1';

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

                    $http.get(contextPath + 'users/get_roles/' + $localStorage.winterMarketUser.username)
                        .then(function (response) {
                            let roles = response.data;
                            $localStorage.roleIndex = roles.findIndex(item => item.name === 'ROLE_ADMIN');
                        })

                    $location.path('/');

                }
            });
    };

    $scope.tryToReg = function () {
        $http.post(contextPath + 'registration', $scope.new_user)
            .then(function (response) {
                alert('Аккаунт успешно создан! Подтвердите вашу почту.')
                $location.path('/auth');
            })
    };

});
