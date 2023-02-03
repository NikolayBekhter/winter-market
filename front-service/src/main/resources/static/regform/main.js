angular.module('market').controller('authController', function ($scope, $http, $localStorage, $location, $rootScope) {

    $scope.tryToAuth = function () {
        $http.post('http://95.165.90.118:2190/auth/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $rootScope.mergeCart();

                    $location.path('/');

                }
            });
    };

    $scope.tryToReg = function () {
        $http.post('http://95.165.90.118:2190/auth/registration', $scope.new_user)
            .then(function (response) {
                alert('Аккаунт успешно создан!')
                $location.path('/auth');
            });
    };

    /* Изначально форма не заполнена и по этому считаем что все возможные ошибки есть  */
    var errorNull = true, errorMail = true, errorPass = true;

    /* Для удобства и уменьшения размера кода выносим функцию проверки поля на null в отдельную переменную */
    var checkNull = function(){
        $(this).val($(this).val().trim());
        if ($(this).val() =="") {
            /* Выводим сообщение об ошибке под элементом.
               This в данном случае это элемент, который инициировал вызов функции */
            ngNotify.set('Your notification message goes here!');
            $ngNotify("Поле нужно заполнить", "error");
            $(this).addClass("errtextbox");
            errorNull = true;
        } else {
            errorNull = false;
            $(this).removeClass("errtextbox");
        }
    };

    /* Проверяем значения полей Имя и Информация на null в момент когда они теряют фокус */
    $("#your_name").focusout(checkNull);
    $("#info").focusout(checkNull);

    /* Проверка поля Имя на соответствие длинны */
    $("#your_name").keyup(function(){
        var value = $(this).val();
        if (value.length > 24){
            ngNotify.set('Your notification message goes here!');
            $(this).notify("Максимум 25 символов", "info");
            $(this).val(value.slice(2,24));
        }
    });

    /* Проверяем корректность E-mail */
    $("#inputEmail").focusout(function(){
        var value = $(this).val().trim();
        /* Для этого используем регулярное выражение  */
        if (value.search(/^[a-z0-9]{3,}@mail\.com$/i) !== 0) {
            ngNotify.set('Your notification message goes here!');
            $(this).notify("E-mail введён не корректно", "error");
            $(this).addClass("errtextbox");
            errorMail = true;
        } else {
            $(this).removeClass("errtextbox");
            errorMail = false;
        }
    });

    /* Проверяем длину пароля */
    $("#inputPassword").focusout(function(){
        var value = $(this).val();
        if (value.length <= 4) {
            $(this).notify("Минимум 5 символов", "error");
            $(this).addClass("errtextbox");
            errorPass = true;
        } else {
            if (value.length > 9) {
                $(this).notify("Миксимум 10 символов", "error");
                $(this).addClass("errtextbox");
                errorPass = true;
            } else {
                errorPass = false;
                $(this).removeClass("errtextbox");
            }
        }
    });

    /* Проверяем соответствие пароля и подтверждения */
    $("#rePassword").focusout(function(){
        var value = $(this).val();
        if (value !== $("#password1").val()) {
            $(this).notify("Пароль не совпадает", "error");
            $(this).addClass("errtextbox");
            errorPass = true;
        } else {
            errorPass = false;
            $(this).removeClass("errtextbox");
        }
    });

    /* В результате клика по кнопке отправить если ошибок заполнения нет то форма отправляется иначе получаем сообщение об ошибке */
    $("#create").click(function(){
        if (!(errorNull || errorMail || errorPass)) {
            $("#regForm").submit();
        } else {
            $(this).notify("Форма пустая или заполнена не корректно", "error");
        }
    });

});
