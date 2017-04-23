var app = require('../app');

app.service('articleService', function($http, $q) {
	this.listArticle = function(pageNumber, pageSize) {
		var deferred = $q.defer();
		$http({
			method: 'GET',
			url: '/article',
			params: {
				pageNumber: pageNumber,
				pageSize: pageSize
			}
		}).success(function(data, status, headers, config) {
			deferred.resolve(data);
		}).error(function(data, status, headers, config) {
			deferred.reject(data);
		});
		return deferred.promise;
	}

	this.getArticle = function(id) {
        var deferred = $q.defer();
        $http({
            method: 'GET',
            url: '/article/' + id
        }).success(function(data, status, headers, config) {
            deferred.resolve(data);
        }).error(function(data, status, headers, config) {
            deferred.reject(data);
        });
        return deferred.promise;
	}
});