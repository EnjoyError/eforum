var app = require('../app');

app.controller('articleController', function($scope, $routeParams, articleService) {
    var promise = articleService.getArticle($routeParams.id);
	promise.then(function(data) {
		$scope.article = data;
	}, function(data) {
	});
});