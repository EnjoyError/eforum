var app = require('../app');

app.controller('headerController', function($scope, $location, $cookies, userService) {
	$scope.title = 'eforum';
	$scope.login = function() {
		$location.path('/login');
	};

	$scope.reg = function() {
		$location.path('/reg');
	};
	
	$scope.logout = function() {
        var promise = userService.logout();
        promise.then(function(result) {
            if (result.data.success) {
            	$cookies.remove('username');
            	refreshUserName();
            	$location.path("/login");
            } else {
                alert(result.data.message);
            }
        }, function(result) {
        	alert("执行到这里" + result);
        },function(result){
        	alert("执行到这里   1" + result);
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
		if(undefined != userName && null != userName){
			$scope.visible = false;
			$scope.username = userName;
		}else{
			$scope.visible = true;
			$scope.username = null;
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