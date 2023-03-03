(function () {
    angular
        .module('market',  ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'orderController'
            })
            .when('/auth', {
                templateUrl: 'regform/auth.html',
                controller: 'authController'
            })
            .when('/registration', {
                templateUrl: 'regform/registration.html',
                controller: 'authController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/admin', {
                templateUrl: 'admin/admin.html',
                controller: 'adminController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.winterMarketUser) {
            try {
                let jwt = $localStorage.winterMarketUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.winterMarketUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.winterMarketUser.token;
        }

        if (!$localStorage.winterMarketGuestCartId) {
            $http.get(/*'http://localhost:5555/cart/api/v1/cart/generate_uuid'*/'http://95.165.90.118:443/cart/api/v1/cart/generate_uuid')
                .then(function successCallback(response) {
                    $localStorage.winterMarketGuestCartId = response.data.value;
                });
        }
    }
})();

angular.module('market').controller('indexController', function ($rootScope, $location, $scope, $http, $localStorage) {
    // использовать для локального подключения
    // const contextPath = 'http://localhost:5555/auth/api/v1/';
    // использовать для удаленного подключения
    const contextPath = 'http://95.165.90.118:443/auth/api/v1/';

    $rootScope.showControl = function () {
        if ($localStorage.winterMarketUser && $localStorage.roleIndex >= 0) {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isRoleAdmin = function () {
        $http.get(contextPath + 'users/get_roles/' + $localStorage.winterMarketUser.username)
            .then(function (response) {
                let roles = response.data;
                $rootScope.roleIndex = roles.findIndex(item => item.name === 'ROLE_ADMIN');
            })
    };

    $rootScope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
        $location.path('/auth');
    };

    $scope.clearUser = function () {
        delete $localStorage.winterMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.winterMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.mergeCart = function () {
        $http.get(/*'http://localhost:5555/cart/api/v1/cart/' + $localStorage.winterMarketGuestCartId + '/merge'*/ 'http://95.165.90.118:443/cart/api/v1/cart/' + $localStorage.winterMarketGuestCartId + '/merge')
            .then(function (response) {
            });
    };

    $rootScope.isActiveUser = function (username) {
        $http.get(contextPath + 'users/is_active/' + username)
            .then(function (response) {
                $rootScope.isActive = response.data;
            })
    };

});