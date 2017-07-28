var app = require('../app');
var tpl = require('../templates/header.html');

app.directive('eforumHeader', function() {
	return {
		restrict: 'E',
		template: tpl,
		replace: true,
		link: function($scope, elm, attr) {
		}
	}
});