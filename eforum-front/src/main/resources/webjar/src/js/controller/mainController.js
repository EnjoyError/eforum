var app = require('../app');

app.controller('mainController', function($scope, articleService) {
	var promise = articleService.list(0, 10);
	promise.then(function(result) {
		$scope.articleList = result.data;
	}, function(data) {
	});
});