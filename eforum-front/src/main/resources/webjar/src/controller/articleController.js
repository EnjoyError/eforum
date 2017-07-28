var app = require('../app');

app.controller('articleController', function($scope, $sce, $routeParams,replyService, articleService) {
	var id = $routeParams.id;
	$scope.articleId = id;
	var promise = articleService.getArticleById(id);
	promise.then(function(result) {
        if (result.data.success) {
        	var article = result.data.message;
        	article.content = $sce.trustAsHtml(article.content);
        	$scope.article = article;
        } else {
            alert(result.data.message);
        }
    }, function(result) {
    	alert("执行到这里" + result);
    },function(result){
    	alert("执行到这里   1" + result);
    });

	var promiseForReplyService = replyService.getReplyCountByArticleId(id);
	promiseForReplyService.then(function(result) {
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