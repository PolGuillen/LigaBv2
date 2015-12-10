'use strict';

angular.module('ligabApp')
    .controller('SocioDetailController', function ($scope, $rootScope, $stateParams, entity, Socio, Team) {
        $scope.socio = entity;
        $scope.load = function (id) {
            Socio.get({id: id}, function(result) {
                $scope.socio = result;
            });
        };
        $rootScope.$on('ligabApp:socioUpdate', function(event, result) {
            $scope.socio = result;
        });
    });
