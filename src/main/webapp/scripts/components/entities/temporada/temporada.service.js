'use strict';

angular.module('ligabApp')
    .factory('Temporada', function ($resource, DateUtils) {
        return $resource('api/temporadas/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.fecha_temporada = DateUtils.convertLocaleDateFromServer(data.fecha_temporada);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.fecha_temporada = DateUtils.convertLocaleDateToServer(data.fecha_temporada);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.fecha_temporada = DateUtils.convertLocaleDateToServer(data.fecha_temporada);
                    return angular.toJson(data);
                }
            }
        });
    });
