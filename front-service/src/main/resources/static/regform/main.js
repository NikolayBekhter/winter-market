angular.module('market').controller('authController', function ($scope, $http, $localStorage, $location, $rootScope) {
    // использовать для локального подключения
    // const contextPath = 'http://localhost:5555/auth/';
    // использовать для удаленного подключения
    const contextPath = 'http://95.165.90.118:2190/auth/';

    $scope.tryToAuth = function () {
        $scope.isActiveUser($scope.user.username);
        console.log($scope.isActive.active);
        if ($scope.isActive.active) {
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
        } else {
            alert('Ваша учетная запись не подтверждена!!! Пройдите на электронной почте по ссылке.');
        }
    };

    $scope.tryToReg = function () {
        $http.post(contextPath + 'registration', $scope.new_user)
            .then(function (response) {
                alert('Аккаунт успешно создан!')
                $location.path('/auth');
            })
    };

    $scope.isActiveUser = function (username) {
        $http.get(contextPath + 'users/is_active/' + username)
            .then(function (response) {
                //console.log(response.data.active);
                $scope.isActive = response.data;
                console.log($scope.isActive.active)
            })
        // console.log($scope.isActive);
        // return $scope.isActive;
    };

});
