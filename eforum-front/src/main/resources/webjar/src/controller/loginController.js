var app = require('../app');

app.controller('loginController', function($scope, $location, userService) {
    $scope.name = '';
    $scope.password = '';
    $scope.rememberMe = '';

	$scope.login = function() {
        var promise = userService.login($scope.name, $scope.password, $scope.rememberMe);
        promise.then(function(result) {
            if (result.success) {
                $location.path('/dashboard/message');
            } else {
                alert(result.message);
            }
        }, function(result) {
        });
	}
});