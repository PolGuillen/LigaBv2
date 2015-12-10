'use strict';

angular.module('ligabApp')
    .controller('EstadisticasDetailController', function ($scope, $rootScope, $stateParams, entity, Estadisticas, Jugador, Partido) {
        $scope.estadisticas = entity;
        $scope.load = function (id) {
            Estadisticas.get({id: id}, function(result) {
                $scope.estadisticas = result;
            });
        };
        $rootScope.$on('ligabApp:estadisticasUpdate', function(event, result) {
            $scope.estadisticas = result;
        });
    });
