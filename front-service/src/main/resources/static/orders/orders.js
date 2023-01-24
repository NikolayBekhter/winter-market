angular.module('market').controller('orderController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/core/api/v1/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'orders')
            .then(function (response) {
                console.log(response.data);
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