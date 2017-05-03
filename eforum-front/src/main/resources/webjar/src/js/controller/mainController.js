var app = require('../app');

app.controller('mainController', function($scope, articleService) {
    $scope.pageCount = 0;
    $scope.currPageIndex = 0;

	function listArticle(pageIndex) {
	    var PAGE_SIZE = 5;
	    var promise = articleService.listArticle(pageIndex, PAGE_SIZE);
        promise.then(function(result) {
            $scope.articleList = result.data;
            $scope.pageCount = result.pageCount;
            $scope.pageList = [];
            var startIndex = 0, endIndex = PAGE_SIZE;
            if ($scope.pageCount <= PAGE_SIZE) {
                endIndex = $scope.pageCount;
            } else if ($scope.currPageIndex + PAGE_SIZE > $scope.pageCount) {
                startIndex = $scope.pageCount - PAGE_SIZE;
                endIndex = startIndex + PAGE_SIZE;
            } else if ($scope.currPageIndex >= 2) {
                startIndex = $scope.currPageIndex - 2;
                endIndex = startIndex + PAGE_SIZE;
            }
            for (var i = startIndex + 1; i <= endIndex; i++)
                $scope.pageList.push(i);
        }, function(result) {
        });
	}

	function suggestionArticleList() {
	    var promise = articleService.listSuggestionArticle();
	    promise.then(function(result) {
	        $scope.suggestionArticleList = result;
	    }, function(result) {
	    });
	}

	$scope.previous = function() {
	    if ($scope.currPageIndex > 0)
	        $scope.currPageIndex--;
	    listArticle($scope.currPageIndex);
	}

	$scope.next = function() {
	    if ($scope.currPageIndex < $scope.pageCount - 1)
	        $scope.currPageIndex++;
	    listArticle($scope.currPageIndex);
	}

	$scope.selectPage = function(pageIndex) {
	    $scope.currPageIndex = pageIndex - 1;
	    listArticle($scope.currPageIndex);
	}

	$scope.isActivePage = function(pageIndex) {
	    return pageIndex - 1 == $scope.currPageIndex;
	}

	listArticle($scope.currPageIndex);
	suggestionArticleList();
});