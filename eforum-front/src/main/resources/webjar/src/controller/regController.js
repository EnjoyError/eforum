var app = require('../app');

app.controller('regController', function($scope, $location, userService) {
    $scope.name = '';
    $scope.email = '';
    $scope.password = '';

	$scope.reg = function() {
        var promise = userService.addUser($scope.name, $scope.email, $scope.password);
        promise.then(function(result) {
            if (result.success) {
                alert('注册成功');
                $location.path('/login');
            } else {
                alert(result.message);
            }
        }, function(result) {
        });
	}
});