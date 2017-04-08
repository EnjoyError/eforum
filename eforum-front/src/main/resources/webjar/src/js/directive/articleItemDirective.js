var app = require('../app');
var tpl = require('../templates/articleItem.html');

app.directive('articleItem', function() {
	return {
		restrict: 'E',
		template: tpl,
		replace: true,
		scope: {
			title: '@',
			description: '@',
			url: '@'
		},
		link: function($scope, elm, attr, controller) {
		}
	}
});