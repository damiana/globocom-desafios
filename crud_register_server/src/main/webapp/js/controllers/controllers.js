'use strict';

/* Controllers */

var app = angular.module('ngdemoApp.controllers', []);

// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
app.run(function ($rootScope, $templateCache) {
  $rootScope.$on('$viewContentLoaded', function () {
    $templateCache.removeAll();
  });
});

app.controller('ServerListCtrl', ['$scope', 'DeleteServerFactory','ListServersFactory', 'SearchServerFactory', '$location', '$http',
  function ($scope, DeleteServerFactory, ListServersFactory, SearchServerFactory, $location, $http) {

    /* callback for ng-click 'editUser': */
    $scope.editServer = function (serverId) {
      $location.path('/server-detail/' + serverId);
    };

    /* callback for ng-click 'deleteUser': */
    $scope.deleteServer = function (serverId) {
      DeleteServerFactory.delete({ id: serverId });
      $scope.servers = ListServersFactory.query();
     // $location.path('/server-list');
    };

    /* callback for ng-click 'createUser': */
    $scope.createNewServer = function () {
      $location.path('/server-creation');
    };

    /* callback for ng-click 'update list': */
    $scope.setDataUserFromServer = function() {
    	alert("asdadsdsdsdas " + $scope.servers);
    	
    	$http({
    	    method: 'GET',
    	    url: 'http://localhost:8080/catalog/ServerServlet',
    	    //params: { user: $scope.user, host: $scope.host,
    	    	//		port: $scope.port, password: $scope.password }
    	    params: {listserver: $scope.servers}
    	
    		}).then(function(response) {
    			console.log(response);
    		});
    };
    
    $scope.servers = ListServersFactory.query();

      //$http.get('http://localhost:8080/catalog/ServerServlet')
       //.then(function(response){
         // $scope.appsServer = response.data;                
        //});
  }]);


app.controller('ServerDetailCtrl', ['$scope', '$routeParams', 'UpdateServerFactory','SearchServerFactory', '$location',
  function ($scope, $routeParams, UpdateServerFactory, SearchServerFactory, $location) {

    /* callback for ng-click 'updateUser': */
    $scope.updateServer = function () {

      UpdateServerFactory.update($scope.server);
      $location.path('/server-list');
    };

    /* callback for ng-click 'cancel': */
    $scope.cancel = function () {
      $location.path('/server-list');
    };

    $scope.server = SearchServerFactory.show({id: $routeParams.id});
  }]);

app.controller('ServerCreationCtrl', ['$scope', 'CreateServerFactory', '$location',
  function ($scope, CreateServerFactory, $location) {

    /* callback for ng-click 'createNewUser': */
    $scope.createNewServer = function () {
      CreateServerFactory.create({"name_server":$scope.server.name_server,"username":$scope.server.username,"password":$scope.server.password,
    	  		"host":$scope.server.host,"port":$scope.server.host});
      $location.path('/server-list');
    }
  }]);
