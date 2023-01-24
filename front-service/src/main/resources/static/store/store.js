angular.module('market').controller('storeController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/core/api/v1/';
    const contextCartPath = 'http://localhost:5555/cart/api/v1/';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + 'products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null
            }
        }).then(function (response) {
            console.log(response.data);
            $scope.ProductPage = response.data.content;
        });
    };

    $scope.showProductInfo = function (productId) {
        $http.get(contextPath + 'products/' + productId)
            .then(function (response) {
                alert(response.data.title + ' ' + response.data.categoryTitle);
            });
    };

    $scope.sendToBasket = function (productId) {
        $http.get(contextCartPath + 'cart/' + $localStorage.winterMarketGuestCartId + '/add/'+ productId)
            .then(function (response) {
            });
    };

    $scope.loadProducts();

});