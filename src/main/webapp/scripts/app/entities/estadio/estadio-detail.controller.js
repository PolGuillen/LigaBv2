'use strict';

angular.module('ligabApp')
    .controller('EstadioDetailController', function ($scope, $rootScope, $stateParams, entity, Estadio) {
        $scope.estadio = entity;
        $scope.load = function (id) {
            Estadio.get({id: id}, function(result) {
                $scope.estadio = result;
            });
        };
        $rootScope.$on('ligabApp:estadioUpdate', function(event, result) {
            $scope.estadio = result;
        });
    });
