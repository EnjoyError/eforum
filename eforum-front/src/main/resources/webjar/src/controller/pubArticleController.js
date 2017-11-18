var app = require('../app');
var $ = require('jquery');

app.controller('pubArticleController', function($scope, $location, articleService) {
	$scope.publish = function(){
		var articleContent = $('#summernote').summernote('code');
		var articleTile = $('#articleTile').val();
		articleService.publishArticle(articleTile,articleContent,function(result) {
            if (result.data.success) {
                modal.alert("发布成功");
            	$location.path("/articleList");
            } else {
                modal.showMsg(result.data.message);
            }
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
            modal.showMsg("请求失败 " + result);
		});
	};

});


