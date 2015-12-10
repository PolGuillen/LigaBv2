'use strict';

angular.module('ligabApp')
    .controller('JugadorDetailController', function ($scope, $rootScope, $stateParams, entity, Jugador, Team) {
        $scope.jugador = entity;
        $scope.load = function (id) {
            Jugador.get({id: id}, function(result) {
                $scope.jugador = result;
            });
        };
        $rootScope.$on('ligabApp:jugadorUpdate', function(event, result) {
            $scope.jugador = result;
        });
    });
