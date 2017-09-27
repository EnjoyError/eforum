var app = require('../app');

app.controller('headerController', function($scope, $location, $cookies, userService, notificationService) {
	//注册消息监听
    notificationService.registerCallBack("SESSION_INVALID",function(){
        refreshUserName();
	});

	$scope.title = 'eforum';
	$scope.login = function() {
		$location.path('/login');
	};

	$scope.reg = function() {
		$location.path('/reg');
	};
	
	$scope.logout = function() {
        userService.logout(function(result) {
            if (result.data.success) {
            	$cookies.remove('username');
            	$cookies.remove('userId');
            	refreshUserName();
            	$location.path("/login");
            } else {
                modal.showMsg(result.data.message);
            }
        });
	};
	
	$scope.myCenter = function() {
		$location.path("/dashboard/message");
	};
	
	$scope.toEditArticlePage = function() {
		$location.path("/pubArticle")
	}
	
	$scope.$on("loginAndLogoutForDown", function(event,msg){
		refreshUserName();
	});
	
	function refreshUserName(){
		var userName = $cookies.get("username");
		var userId = $cookies.get("userId");
		if(undefined != userName && null != userName){
			$scope.visible = false;
			$scope.username = userName;
			$scope.userId = userId;
		}else{
			$scope.visible = true;
			$scope.username = null;
			$scope.userId = null;
		}
	};
	refreshUserName();
	//监听路由时间刷新head样式
	$scope.$on("$routeChangeStart", function(event, next, current){
			if(next.$$route.originalPath == "/" || next.$$route.originalPath == ""){
				cleanActive($scope);
				$scope.main = "active";
			}
			if(next.$$route.originalPath == "/about"){
				cleanActive($scope);
				$scope.about = "active";
			}
			if(next.$$route.originalPath == "/articleList"){
				cleanActive($scope);
				$scope.articleList = "active";
			}
		}
	);

	function cleanActive($scope){
		$scope.main = "";
		$scope.about = "";
		$scope.articleList = "";
	}
});