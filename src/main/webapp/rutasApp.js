/**
 * Created by jhipster on 24/02/16.
 */
angular.module('myApp')
    .config(function ($stateProvider,$urlRouterProvider) {
        $urlRouterProvider.otherwise("/");
        $stateProvider
            .state('ej1', {
                url: '/ej1',
                data: {
                    pageTitle: 'Ej1'
                },
                views: {
                    'content@': {
                        templateUrl: 'ej1.html',
                        controller: 'topJugadorsCtrl'
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Jugadores', function($stateParams, Jugador) {
                        return Jugador.topPlayers();
                    }]
                }
            })


    });
