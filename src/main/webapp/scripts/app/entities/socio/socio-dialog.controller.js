'use strict';

angular.module('ligabApp').controller('SocioDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Socio', 'Team',
        function($scope, $stateParams, $modalInstance, entity, Socio, Team) {

        $scope.socio = entity;
        $scope.teams = Team.query();
        $scope.load = function(id) {
            Socio.get({id : id}, function(result) {
                $scope.socio = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('ligabApp:socioUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.socio.id != null) {
                Socio.update($scope.socio, onSaveFinished);
            } else {
                Socio.save($scope.socio, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
