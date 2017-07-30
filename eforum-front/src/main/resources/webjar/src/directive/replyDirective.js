var app = require('../app');
var tpl = require('../templates/reply.html');

app.directive('reply', function($location,replyService) {
	return {
		restrict: 'E',
		template: tpl,
		replace: true,
		link: function($scope) {
			
		}
	}
});