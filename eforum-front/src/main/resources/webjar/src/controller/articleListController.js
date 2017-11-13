var app = require('../app');
var $ = require('jquery');

app.controller('articleListController', function($scope,$sce,$location,articleService,userService,notificationService) {
	$scope.viewArticle = function(id){
		 $location.path('/article').search({
         	id : id
         });
	}

	function getArticleList() {
		var pageIndex = $scope.paginationConf.currentPage;
		var pageSize = $scope.paginationConf.itemsPerPage;
		
		articleService.listArticle(pageIndex,pageSize,function(result) {
	        if (result.data.success) {
	        	var message = result.data.message;
	        	// 配置分页基本参数
	        	$scope.paginationConf.currentPage = message.pageIndex,
	        	$scope.paginationConf.totalItems = message.dataCount,
	        	$scope.paginationConf.itemsPerPage = message.pageSize,
	        	$scope.paginationConf.pagesLength = message.pageCount,
	        	$scope.articleList = articleService.parseArticleList(message);
	        } else {
                modal.showMsg(result.data.message);
	        }
	    });
	}
	
	// 配置分页基本参数
	$scope.paginationConf = {
		currentPage : 1,
		itemsPerPage : 15,
		perPageOptions : [ 10, 20, 30, 40, 50 ],
		onChange : function(){
			getArticleList();
		}
	};
	
	//首次触发查询
    getArticleList();


    $scope.writeUserInfoToDataContent = function($tooltip){
		var userId = $tooltip.$scope.$parent.$parent.x.createUserId;
        var articleId = $tooltip.$scope.$parent.$parent.x.id;
        userService.findUser(userId,articleId, function(result){
            if (result.data.success) {
                var message = result.data.message;
                $tooltip.$scope.userInfo = message;
            } else {
                modal.showMsg(result.data.message);
            }
		});
	};

    $scope.onShow = function($modal){
        $('.switch input').bootstrapSwitch({
            size : 'mini',
            onColor : 'success',
            onText : '是',
			offText : '否'
		});
        $modal.$id
        notificationService.pubNotification("OPERATION_ARTICLE", $modal.$id);
    };
});