'use strict';

angular.module('ligabApp')
    .factory('Jugador', function ($resource, DateUtils) {
        return $resource('api/jugadors/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'topPlayers':{method: 'GET',isArray:true,url : '/api/bp-by-canastas/:canastas'},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.fecha_nacimiento = DateUtils.convertLocaleDateFromServer(data.fecha_nacimiento);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.fecha_nacimiento = DateUtils.convertLocaleDateToServer(data.fecha_nacimiento);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.fecha_nacimiento = DateUtils.convertLocaleDateToServer(data.fecha_nacimiento);
                    return angular.toJson(data);
                }
            }
        });
    });
