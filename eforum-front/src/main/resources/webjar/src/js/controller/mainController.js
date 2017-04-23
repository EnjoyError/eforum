var app = require('../app');

app.controller('mainController', function($scope, articleService) {
	function listArticle(pageIndex, pageSize) {
	    var promise = articleService.listArticle(pageIndex, pageSize);
        promise.then(function(result) {
            $scope.articleList = result.data;
        }, function(result) {
        });
	}

	$scope.previous = function() {
	    listArticle(0, 5);
	}

	$scope.next = function() {
	    listArticle(1, 5);
	}

	listArticle(0, 5);
});