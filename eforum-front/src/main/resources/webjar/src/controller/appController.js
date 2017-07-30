var app = require('../app');

app.controller('appController', function($scope) {
	$scope.$on("loginAndLogoutForUp", function(event, msg) {
		console.log("parent", msg);
		$scope.$broadcast("loginAndLogoutForDown", msg);
	});
});