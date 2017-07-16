var app = require('../app');

app.controller('replyController', function($scope, $sce, $routeParams, replyService, articleService) {
	$scope.commitReply = function(){
		var replyContent = $('#summernote').summernote('code');
		var articleId = $scope.$parent.articleId;
		var promise = replyService.commitReply(articleId,replyContent);
		promise.then(function(result) {
            if (result.data.success) {
            	alert("回复成功");
//            	$location.path("/articleList");
            } else {
                alert(result.data.message);
            }
        }, function(result) {
        	alert("执行到这里" + result);
        },function(result){
        	alert("执行到这里   1" + result);
        });
	};
	
	var promise = replyService.getReplyByArticleId($scope.$parent.article.id);
	promise.then(function(result) {
        if (result.data.success) {
        	$scope.replys = result.data.message;
        	angular.forEach($scope.replys,function(reply){
        		reply.content = $sce.trustAsHtml(reply.content);
        	});
        } else {
            alert(result.data.message);
        }
    }, function(result) {
    	alert("执行到这里" + result);
    },function(result){
    	alert("执行到这里   1" + result);
    });
	
	$('#summernote').summernote({
		lang : 'zh-CN',
		placeholder:'在这里输入你的回复',
		minHeight: 100,
		dialogsFade: true,
		dialogsInBody : true
	});
});