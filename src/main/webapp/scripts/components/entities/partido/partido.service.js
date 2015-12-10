'use strict';

angular.module('ligabApp')
    .factory('Partido', function ($resource, DateUtils) {
        return $resource('api/partidos/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.inicioPartido = DateUtils.convertDateTimeFromServer(data.inicioPartido);
                    data.finalPartido = DateUtils.convertDateTimeFromServer(data.finalPartido);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
