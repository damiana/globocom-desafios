'use strict';

var services = angular.module('ngdemoApp.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.factory('ListServersFactory', function ($resource) {
    return $resource(baseUrl + '/catalog/rest/server/list', {}, {
        query: { method: 'GET', isArray: true }
     })
});

services.factory('CreateServerFactory', function ($resource) {
    return $resource(baseUrl + '/catalog/rest/server/register', {}, {
        create: { method: 'POST'}
    })
});

services.factory('UpdateServerFactory', function ($resource) {
    return $resource(baseUrl + '/catalog/rest/server/modify', {}, {
        update: { method: 'PUT'}
    })
});

services.factory('DeleteServerFactory', function ($resource) {
    return $resource(baseUrl + '/catalog/rest/server/delete/:id', {}, {
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

services.factory('SearchServerFactory', function ($resource) {
    return $resource(baseUrl+ '/catalog/rest/server/search/:id', {}, {
        show: { method: 'GET'}
    })
});
