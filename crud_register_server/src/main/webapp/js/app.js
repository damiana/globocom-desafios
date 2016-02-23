'use strict';

angular.module('ngdemoApp', [
  'ngdemoApp.services',
  'ngdemoApp.controllers'
  ])
.config(function ($routeProvider, $httpProvider) {
  //$routeProvider.when('/dummy', {templateUrl: 'views/dummy.html', controller: 'DummyCtrl'});
  $routeProvider.when('/server-list', {templateUrl: 'views/server-list.html', controller: 'ServerListCtrl'});
  $routeProvider.when('/server-detail/:id', {templateUrl: 'views/server-detail.html', controller: 'ServerDetailCtrl'});
  $routeProvider.when('/server-creation', {templateUrl: 'views/server-creation.html', controller: 'ServerCreationCtrl'});
  $routeProvider.otherwise({redirectTo: '/dummy'});

  /* CORS... */
  /* http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api */
  $httpProvider.defaults.useXDomain = true;
  delete $httpProvider.defaults.headers.common['X-Requested-With'];
});
