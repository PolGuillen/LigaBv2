'use strict';

angular.module('ligabApp')
    .controller('TeamDetailController', function ($scope, $rootScope, $stateParams, entity, Team, Entrenador, Estadio, Jugador, Socio, Temporada) {
        $scope.team = entity;
        $scope.load = function (id) {
            Team.get({id: id}, function(result) {
                $scope.team = result;
            });
        };
        $rootScope.$on('ligabApp:teamUpdate', function(event, result) {
            $scope.team = result;
        });
    });
