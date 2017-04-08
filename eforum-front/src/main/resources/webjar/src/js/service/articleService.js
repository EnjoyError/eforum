var app = require('../app');

app.service('articleService', function($http, $q) {
	this.list = function(pageNumber, pageSize) {
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
});