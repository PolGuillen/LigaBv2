'use strict';

angular.module('ligabApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


