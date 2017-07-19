var app = require('../app');

app.controller('headerController', function($scope, $location) {
	if(undefined == $scope.visible){
		$scope.visible = true;
	}
	$scope.$on("loginAndLogoutForDown", function(event,msg){
		if(true == msg.isLogin){
			$scope.visible = false;
			$scope.username = msg.username;
		}else{
			$scope.visible = true;
			$scope.username = null;
		}
	});
	
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