var app = require('../app');

app.controller('articleController', function($scope, $sce, $routeParams,replyService, articleService) {
	$scope.title = $routeParams.article.title;
	$scope.article = $routeParams.article;
	$scope.content = $sce.trustAsHtml($routeParams.article.content);
	$scope.articleId = $routeParams.article.id;
	
	var promise = replyService.getReplyCountByArticleId($scope.article.id);
	promise.then(function(result) {
        if (result.data.success) {
        	$scope.replyCount = result.data.message;
        } else {
            alert(result.data.message);
        }
    }, function(result) {
    	alert("执行到这里" + result);
    },function(result){
    	alert("执行到这里   1" + result);
    });
});