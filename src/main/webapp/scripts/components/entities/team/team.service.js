'use strict';

angular.module('ligabApp')
    .factory('Team', function ($resource, DateUtils) {
        return $resource('api/teams/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.fecha_creacion = DateUtils.convertLocaleDateFromServer(data.fecha_creacion);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.fecha_creacion = DateUtils.convertLocaleDateToServer(data.fecha_creacion);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.fecha_creacion = DateUtils.convertLocaleDateToServer(data.fecha_creacion);
                    return angular.toJson(data);
                }
            }
        });
    });
