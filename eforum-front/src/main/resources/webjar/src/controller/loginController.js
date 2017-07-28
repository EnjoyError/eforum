var app = require('../app');

app.controller('loginController', function($scope, $location, userService, $cookieStore) {
    $scope.name = '';
    $scope.password = '';
    $scope.rememberMe = '';

	$scope.login = function() {
        var promise = userService.login($scope.name, $scope.password, $scope.rememberMe);
        promise.then(function(result) {
            if (result.data.success) {
            	var msg = {
            			isLogin:true,
            			username:result.data.message.username
            	}
            	$scope.$emit("loginAndLogoutForUp",msg);
                $location.path('/');
            } else {
                alert(result.data.message);
            }
        }, function(result) {
        	alert("执行到这里" + result);
        },function(result){
        	alert("执行到这里   1" + result);
        });
	}
});