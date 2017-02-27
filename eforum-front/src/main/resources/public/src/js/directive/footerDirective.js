var app = require('../app');
var tpl = require('../templates/footer.html');

app.directive('eforumFooter', function() {
	return {
		restrict: 'E',
		template: tpl,
		replace: true,
		link: function($scope, elm, attr, controller) {
			$scope.copyright = 'GPL-3.0';
		}
	}
});