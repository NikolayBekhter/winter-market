angular.module('market').controller('welcomeController', function ($scope, $http, $localStorage, $rootScope) {
    $rootScope.isActiveUser($localStorage.winterMarketUser.username);
});