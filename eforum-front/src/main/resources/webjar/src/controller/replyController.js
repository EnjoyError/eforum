var app = require('../app');
var $ = require('jquery');
app.controller('replyController', function($scope, $sce, $window, $routeParams, replyService, articleService) {
	$scope.commitReply = function(){
		var replyContent = $('#summernote').summernote('code');
		var articleId = $scope.$parent.articleId;
		replyService.commitReply(articleId,replyContent,function(result) {
            if (result.data.success) {
                modal.alert("回复成功");
                $('#summernote').summernote('code',"");
                refreshReply($scope,$sce);
            } else {
                modal.showMsg(result.data.message);
            }
        });
	}
	
	var refreshReply = function($scope,$sce){
		replyService.getReplyByArticleId($scope.$parent.articleId,function(result) {
	        if (result.data.success) {
	        	$scope.replys = result.data.message;
	        	angular.forEach($scope.replys,function(reply){
	        		reply.content = $sce.trustAsHtml(reply.content);
	        	});
	        } else {
                modal.showMsg(result.data.message);
	        }
	    });
	}
	
	refreshReply($scope,$sce);

	
	$('#summernote').summernote({
		lang : 'zh-CN',
		placeholder:'在这里输入你的回复',
		minHeight: 100,
		dialogsFade: true,
		dialogsInBody : true,
		callbacks:{
			onImageUpload : sendFile
		}
	});
	
	function sendFile(files){
		articleService.uploadImages(files,function(result){
            if (result.success) {
            	var imageUrls = result.message;
            	for (i in imageUrls) {
					$('#summernote').summernote('insertImage',
							imageUrls[i],'img');
				}
            } else {
            	console.log(result.message);
            }
		},function(result){
            modal.showMsg("请求失败 " + result);
		});
	}
	
});