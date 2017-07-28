var app = require('../app');

app.service('restService', function($http, $q, $location) {
    this.get = function(url, params) {
        params = params || {};
        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: url,
            params: params
        }).success(function(data, status, headers, config) {
            deferred.resolve(data);
        }).error(function(data, status, headers, config) {
            deferred.reject(data);
        });
        return deferred.promise;
    }

    this.post = function(url, params) {
        params = params || {};
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: url,
            params: params
        }).success(function(data, status, headers, config) {
            deferred.resolve(data);
        }).error(function(data, status, headers, config) {
            deferred.reject(data);
        });
        return deferred.promise;
    }
});