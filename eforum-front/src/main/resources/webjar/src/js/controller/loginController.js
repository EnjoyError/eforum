var app = require('../app');

app.controller('loginController', function($scope, $location) {
	$scope.login = function() {
		$location.path('/panel/message');
	}
});