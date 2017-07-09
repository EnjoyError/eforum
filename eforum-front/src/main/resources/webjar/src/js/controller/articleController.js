var app = require('../app');

app.controller('articleController', function($scope, $sce, $routeParams, articleService) {
	$scope.title = $routeParams.article.title;
	$scope.content = $sce.trustAsHtml($routeParams.article.content);
});