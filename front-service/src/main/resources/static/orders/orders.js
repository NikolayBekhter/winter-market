angular.module('market').controller('orderController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/core/api/v1/';
    // const contextPath = 'http://95.165.90.118:2190/core/api/v1/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'orders')
            .then(function (response) {
                $scope.order = response.data;

            });
    };

    $scope.showUser = function () {
        $localStorage.winterMarketUser.username;
    };

    $scope.deleteOrder = function (orderId) {
        $http.delete(contextPath + 'orders/' + orderId)
            .then(function (response) {
                $scope.loadOrders();
            });
    };

    $scope.loadOrders();

});