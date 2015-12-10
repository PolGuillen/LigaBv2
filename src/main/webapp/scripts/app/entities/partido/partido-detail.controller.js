'use strict';

angular.module('ligabApp')
    .controller('PartidoDetailController', function ($scope, $rootScope, $stateParams, entity, Partido, Temporada, Team, Arbitro) {
        $scope.partido = entity;
        $scope.load = function (id) {
            Partido.get({id: id}, function(result) {
                $scope.partido = result;
            });
        };
        $rootScope.$on('ligabApp:partidoUpdate', function(event, result) {
            $scope.partido = result;
        });
    });
