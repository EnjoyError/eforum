var app = require('../app');
var $ = require('jquery');
app.controller('articleController', function($scope, $sce, $compile, $routeParams,replyService, articleService) {
	var id = $routeParams.id;
	$scope.articleId = id;
	articleService.getArticleById(id,function(result) {
        if (result.data.success) {
        	var article = result.data.message;
        	$scope.article = article;
        	var contentDom = $($compile(article.content)($scope));
        	contentDom.appendTo("[data-ng-bind-html='content']");
        } else {
            modal.showMsg(result.data.message);
        }
    });

	replyService.getReplyCountByArticleId(id,function(result) {
        if (result.data.success) {
        	$scope.replyCount = result.data.message;
        } else {
            modal.showMsg(result.data.message);
        }
    });
});