var app = require('../app');

app.controller('regController', function($scope, $location, userService) {
    $scope.name = '';
    $scope.email = '';
    $scope.password = '';

	$scope.reg = function() {
        userService.addUser($scope.name, $scope.email, $scope.password,function(result) {
            if (result.data.success) {
                modal.alert('注册成功');
                $location.path('/login');
            } else {
                modal.showMsg(result.data.message);
            }
        });
	}
});