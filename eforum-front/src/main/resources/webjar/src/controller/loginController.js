var app = require('../app');

app.controller('loginController', function($scope, $location, userService, $cookieStore) {
    $scope.name = '';
    $scope.password = '';
    $scope.rememberMe = '';

	$scope.login = function() {
        userService.login($scope.name, $scope.password, $scope.rememberMe,function(result) {
            if (result.data.success) {
            	var msg = {
            			isLogin:true,
            			username:result.data.message.username
            	}
            	$scope.$emit("loginAndLogoutForUp",msg);
                $location.path('/');
            } else {
                modal.showMsg(result.data.message);
            }
        });
	}
});