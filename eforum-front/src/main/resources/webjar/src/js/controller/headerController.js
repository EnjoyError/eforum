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
});