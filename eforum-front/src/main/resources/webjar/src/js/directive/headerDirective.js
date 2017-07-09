var app = require('../app');
var tpl = require('../templates/header.html');

app.directive('eforumHeader', function($location, userService) {
	return {
		restrict: 'E',
		template: tpl,
		replace: true,
		link: function($scope, elm, attr) {
			$scope.title = 'eforum';

			$scope.login = function() {
				$location.path('/login');
			};

			$scope.reg = function() {
				$location.path('/reg');
			};
			
			$scope.logout = function() {
		        var promise = userService.logout();
		        promise.then(function(result) {
		            if (result.data.success) {
		            	var msg = {
		            			isLogin:false
		            	}
		            	$scope.$emit("loginAndLogoutForUp",msg);
		            	$location.path("/login");
		            } else {
		                alert(result.data.message);
		            }
		        }, function(result) {
		        	alert("执行到这里" + result);
		        },function(result){
		        	alert("执行到这里   1" + result);
		        });
			};
			
			$scope.myCenter = function() {
				$location.path("/dashboard/message");
			};
			
			$scope.toEditArticlePage = function() {
				$location.path("/pubArticle")
			}
		}
	}
});