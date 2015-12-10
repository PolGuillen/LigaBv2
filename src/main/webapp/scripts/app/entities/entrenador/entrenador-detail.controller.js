'use strict';

angular.module('ligabApp')
    .controller('EntrenadorDetailController', function ($scope, $rootScope, $stateParams, entity, Entrenador) {
        $scope.entrenador = entity;
        $scope.load = function (id) {
            Entrenador.get({id: id}, function(result) {
                $scope.entrenador = result;
            });
        };
        $rootScope.$on('ligabApp:entrenadorUpdate', function(event, result) {
            $scope.entrenador = result;
        });
    });
