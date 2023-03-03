angular.module('market').controller('adminController', function ($scope, $http, $localStorage) {
    // использовать для локального подключения
    // const contextPathCore = 'http://localhost:5555/core/api/v1/';
    // const contextPathAuth = 'http://localhost:5555/auth/api/v1/';
    // использовать для удаленного подключения
    const contextPathCore = 'http://95.165.90.118:443/core/api/v1/';
    const contextPathAuth = 'http://95.165.90.118:443/auth/api/v1/';

    /*$scope.deleteProduct = function (productId) {
        $http.delete(contextPath + 'products/' + productId)
            .then(function (response) {
                alert("Продукт удалён!");
            });
    };*/

    $scope.setRole = function () {
        console.log($scope.user)
        $http.post(contextPathAuth + 'users/set_role', $scope.user)
            .then(function successCallback(response) {
                console.log(response.data);
                alert('Роль успешно добавлена!')
            });
    };

    $scope.saveOrUpdateProduct = function () {
            $http.post(contextPathCore + 'products/create', $scope.save_or_update_product)
                .then(function (response) {
                    alert("Успех!");
                });
        };

    /*$scope.loadProducts = function (pageIndex = 1) {
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

    $scope.loadProducts();*/

});