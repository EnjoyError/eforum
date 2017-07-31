
var app = require('../app');

app.controller('pubArticleController', function($scope, $location, articleService) {
	$scope.publish = function(){
		var articleContent = $('#summernote').summernote('code');
		var articleTile = $('#articleTile').val();
		var promise = articleService.publishArticle(articleTile,articleContent);
		promise.then(function(result) {
            if (result.data.success) {
            	alert("发布成功");
            	$location.path("/articleList");
            } else {
                alert(result.data.message);
            }
        }, function(result) {
        	alert("执行到这里" + result);
        },function(result){
        	alert("执行到这里   1" + result);
        });
		
	};
	$('#summernote').summernote({
		lang : 'zh-CN',
		placeholder:'在这里输入文章内容',
		minHeight: 300,
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
							imageUrls[i],function(img){
						img.attr('data-filename', "img");
						img.attr('enlarge-pic', "");
					});
				}
            } else {
            	console.log(result.message);
            }
		},function(result){
			alert("执行到这里" + result);
		});
	};

});


