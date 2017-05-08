var app = require('../app');

app.controller('articleController', function($scope, $routeParams, articleService) {
    (function() {
        var promise = articleService.getArticle($routeParams.id);
        promise.then(function(result) {
            $scope.article = result;
        }, function(result) {
        });
        function getArticle() {
        }
	})();

	(function() {
        var promise = articleService.listSuggestionArticle();
        promise.then(function(result) {
            $scope.suggestionArticleList = result;
        }, function(result) {
        });
    })();
});