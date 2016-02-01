'use strict';

angular.module('ligabApp').controller('TeamDialogController',
    ['$scope', '$stateParams', '$modalInstance', '$q', 'entity', 'Team', 'Entrenador', 'Estadio', 'Socio', 'Temporada',
        function($scope, $stateParams, $modalInstance, $q, entity, Team, Entrenador, Estadio, Socio, Temporada) {

        $scope.team = entity;
        $scope.entrenadors = Entrenador.query({filter: 'team-is-null'});
        $q.all([$scope.team.$promise, $scope.entrenadors.$promise]).then(function() {
            if (!$scope.team.entrenador.id) {
                return $q.reject();
            }
            return Entrenador.get({id : $scope.team.entrenador.id}).$promise;
        }).then(function(entrenador) {
            $scope.entrenadors.push(entrenador);
        });
        $scope.estadios = Estadio.query({filter: 'team-is-null'});
        $q.all([$scope.team.$promise, $scope.estadios.$promise]).then(function() {
            if (!$scope.team.estadio.id) {
                return $q.reject();
            }
            return Estadio.get({id : $scope.team.estadio.id}).$promise;
        }).then(function(estadio) {
            $scope.estadios.push(estadio);
        });
        $scope.socios = Socio.query();
        $scope.temporadas = Temporada.query();
        $scope.load = function(id) {
            Team.get({id : id}, function(result) {
                $scope.team = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('ligabApp:teamUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.team.id != null) {
                Team.update($scope.team, onSaveFinished);
            } else {
                Team.save($scope.team, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
