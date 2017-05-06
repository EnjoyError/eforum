var app = require('../app');
var tpl = require('../templates/sidebar.html');

app.directive('sidebar', function() {
	return {
		restrict: 'E',
		template: tpl,
		replace: true,
		link: function($scope, elm, attr, controller) {
		}
	}
});