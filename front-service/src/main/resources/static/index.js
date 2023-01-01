angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/core/api/v1';

    $scope.tryToAuth = function() {
        $http.post('http://localhost:5555/auth/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
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

    $scope.loadCart = function () {
            $http.get('http://localhost:5555/cart/api/v1/cart')
                    .then(function (response) {
                        console.log(response.data);
                        $scope.cart = response.data;
                        let totalCost = response.data.totalCost;
                        console.log(totalCost + ' items');
                    });
    };

    $scope.sendToBasket = function (productId) {
            $http.get('http://localhost:5555/cart/api/v1/cart/add/'+ productId)
                .then(function (response) {
                    $scope.loadCart();
                });
    };
    $scope.deleteFromCart = function (productId) {
            $http.get('http://localhost:5555/cart/api/v1/cart/remove/' + productId)
                .then(function (response) {
                    $scope.loadCart();
                });
    };

    $scope.clearCart = function () {
            $http.get('http://localhost:5555/cart/api/v1/cart/clear')
                 .then(function (response) {
                     $scope.loadCart();
                 });
    };

    $scope.changeQuantity = function (productId, delta) {
            $http({
                url: 'http://localhost:5555/cart/api/v1/cart/change_quantity',
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
        $http.post('http://localhost:5555/core/api/v1/orders')
            .then(function (response) {
                console.log(response.data);
                $scope.clearCart();
                alert('Ваш заказ создан!');
            });
    };



    $scope.loadProducts();
    $scope.loadCart();

});