var app = require('../app');
var tpl = require('../templates/header.html');

app.directive('eforumHeader', function($location) {
	return {
		restrict: 'E',
		template: tpl,
		replace: true,
		link: function($scope, elm, attr, controller) {
			$scope.title = 'eforum';

			$scope.login = function() {
				$location.path('/login');
			}

			$scope.reg = function() {
				$location.path('/reg');
			}
		}
	}
});