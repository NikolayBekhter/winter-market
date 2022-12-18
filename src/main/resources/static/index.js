angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/winter/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
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
        $http.get(contextPath + '/products/' + productId)
            .then(function (response) {
                alert(response.data.title);
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    };

    $scope.updateProduct = function () {
            $http.post(contextPath + '/products', $scope.update_product)
                .then(function (response) {
                    console.log(response.data);
                    $scope.loadProducts();
                });
        };
    $scope.loadCart= function () {
                $http.get(contextPath + '/cart')
                    .then(function (response) {
                        console.log(response.data);
                        $scope.cart = response.data;
                    });
            };
    $scope.sendToBasket = function (productId) {
            $http.get(contextPath + '/cart/add/'+ productId)
                .then(function (response) {
                    $scope.loadCart();
                });
        };
    $scope.deleteFromCart = function (basketId) {
            $http.get(contextPath + '/cart/delete/' + basketId)
                .then(function (response) {
                    $scope.loadCart();
                });
        };
    $scope.loadProducts();
    $scope.loadCart();
});