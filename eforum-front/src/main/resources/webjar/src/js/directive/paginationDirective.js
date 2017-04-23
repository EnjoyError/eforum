var app = require('../app');
var tpl = require('../templates/header.html');

app.directive('pagination', function($location) {
	return {
		restrict: 'E',
		template: tpl,
		replace: true,
		scope: {
            url: '@',
            count: '@',
        },
		link: function($scope, elm, attr, controller) {
		    $scope.previous = function() {
		    }

		    $scope.next = function() {
		    }
		}
	}
});