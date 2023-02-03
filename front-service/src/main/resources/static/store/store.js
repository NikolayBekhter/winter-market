angular.module('market').controller('storeController', function ($scope, $http, $localStorage, $rootScope) {
    const contextPath = 'http://localhost:5555/core/api/v1/';
    const contextCartPath = 'http://localhost:5555/cart/api/v1/';
    // const contextPath = 'http://95.165.90.118:2190/core/api/v1/';
    // const contextCartPath = 'http://95.165.90.118:2190/cart/api/v1/';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + 'products',
            method: 'GET',
            params: {
                p: pageIndex,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null
            }
        }).then(function (response) {
            $scope.ProductPage = response.data;
            $scope.indexNumber = $scope.ProductPage.totalPages;
            $scope.generatePagesList($scope.ProductPage.totalPages);
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

    $scope.generatePagesList = function (totalPages) {
        $scope.pagesList = [];
        for (let i = 0; i < totalPages; i++) {
            $scope.pagesList.push(i + 1);
        }
    }

    $scope.isThereIndex = function () {
        if ($scope.indexNumber > 1) {
            return true;
        } else {
            return false;
        }
    }

    $scope.previousPage = function (page, delta) {
        if ((page + delta) >= 0) {
            $scope.loadProducts(page + delta);
        }
    }

    $scope.nextPage = function (page, delta) {
        if ((page + delta) <= $scope.indexNumber) {
            $scope.loadProducts(page + delta);
        }
    }

    $scope.loadProducts();

});