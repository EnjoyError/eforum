var app = require('../app');

app.controller('replyController', function($scope, $sce, $window, $routeParams, replyService, articleService) {
	$scope.commitReply = function(){
		var replyContent = $('#summernote').summernote('code');
		var articleId = $scope.$parent.articleId;
		var promise = replyService.commitReply(articleId,replyContent);
		promise.then(function(result) {
            if (result.data.success) {
            	alert("回复成功");
            	refreshReply($scope,$sce);
            	var replyContent = $('#summernote').summernote('code',"");
            } else {
                alert(result.data.message);
            }
        }, function(result) {
        	alert("执行到这里" + result);
        },function(result){
        	alert("执行到这里   1" + result);
        });
	}
	
	var refreshReply = function($scope,$sce){
		var promise = replyService.getReplyByArticleId($scope.$parent.articleId);
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
			alert("执行到这里" + result);
		});
	}
	
});