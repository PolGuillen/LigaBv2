'use strict';

angular.module('ligabApp')
    .controller('TemporadaDetailController', function ($scope, $rootScope, $stateParams, entity, Temporada, Liga, Team) {
        $scope.temporada = entity;
        $scope.load = function (id) {
            Temporada.get({id: id}, function(result) {
                $scope.temporada = result;
            });
        };
        $rootScope.$on('ligabApp:temporadaUpdate', function(event, result) {
            $scope.temporada = result;
        });
    });
