'use strict';

angular.module('ligabApp').controller('PartidoDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Partido', 'Temporada', 'Team', 'Arbitro',
        function($scope, $stateParams, $modalInstance, entity, Partido, Temporada, Team, Arbitro) {

        $scope.partido = entity;
        $scope.temporadas = Temporada.query();
        $scope.teams = Team.query();
        $scope.arbitros = Arbitro.query();
        $scope.load = function(id) {
            Partido.get({id : id}, function(result) {
                $scope.partido = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('ligabApp:partidoUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.partido.id != null) {
                Partido.update($scope.partido, onSaveFinished);
            } else {
                Partido.save($scope.partido, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
