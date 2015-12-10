'use strict';

angular.module('ligabApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
