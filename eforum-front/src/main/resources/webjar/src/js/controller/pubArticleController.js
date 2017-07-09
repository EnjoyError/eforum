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
		lang : 'zh-CN'
	});
});


