'use strict';

angular.module('ligabApp')
    .controller('ArbitroDetailController', function ($scope, $rootScope, $stateParams, entity, Arbitro, Liga) {
        $scope.arbitro = entity;
        $scope.load = function (id) {
            Arbitro.get({id: id}, function(result) {
                $scope.arbitro = result;
            });
        };
        $rootScope.$on('ligabApp:arbitroUpdate', function(event, result) {
            $scope.arbitro = result;
        });
    });
