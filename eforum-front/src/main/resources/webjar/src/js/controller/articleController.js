var app = require('../app');

app.controller('articleController', function($scope, $routeParams, articleService) {
    var promise = articleService.getArticle($routeParams.id);
	promise.then(function(result) {
		$scope.article = result;
	}, function(result) {
	});
});