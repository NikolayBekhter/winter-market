angular.module('market').controller('cartController', function ($scope, $http, $location, $localStorage, $rootScope) {
    // использовать для локального подключения
    // const contextPath = 'http://localhost:5555/cart/api/v1/';
    // const contextCorePath = 'http://localhost:5555/core/api/v1/';
    // использовать для удаленного подключения
    const contextPath = 'http://95.165.90.118:443/cart/api/v1/';
    const contextCorePath = 'http://95.165.90.118:443/core/api/v1/';

    $scope.loadCart = function () {
        $http.get(contextPath + 'cart/' + $localStorage.winterMarketGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.deleteFromCart = function (productId) {
        $http.get(contextPath + 'cart/' + $localStorage.winterMarketGuestCartId + '/remove/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + 'cart/' + $localStorage.winterMarketGuestCartId + '/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    };

    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: contextPath + 'cart/' + $localStorage.winterMarketGuestCartId + '/change_quantity',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.createOrder = function () {
        if ($scope.cart.totalCost === 0) {
            alert('Добавьте хотя бы один товар!');
            $location.path('/store');
            return;
        } if (!$scope.isUserLoggedIn()) {
            alert('Необходимо авторизоваться!');
            $location.path('/auth');
            return;
        } if (!$rootScope.isActive.active) {
            alert('Ваша учетная запись не подтверждена!!! Чтобы продолжить, пройдите на электронной почте по ссылке.');
            $rootScope.isActiveUser($localStorage.winterMarketUser.username);
            return;
        }
        $http.post(contextCorePath + 'orders')
            .then(function (response) {
                $scope.clearCart();
                alert('Ваш заказ создан!');
                $location.path('/orders');
            });
    };

    $scope.loadCart();

});