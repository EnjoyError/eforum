var angular = require('angular');
require('angular-route');
var app = angular.module('app', ['ngRoute']);

app.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
	// 禁用route缓存
	if (!$httpProvider.defaults.headers.get) {
		$httpProvider.defaults.headers.get = {};
	}
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	$httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
	$httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
	
	$routeProvider.otherwise({redirectTo: '/'});
	$routeProvider.when('/', {
		templateUrl: 'views/main.html',
		controller: 'mainController'
	});
	$routeProvider.when('/about', {
		templateUrl: 'views/about.html',
		controller: 'aboutController'
	});
	$routeProvider.when('/article/:id', {
		templateUrl: 'views/article.html',
		controller: 'articleController'
	});
	$routeProvider.when('/login', {
		templateUrl: 'views/login.html',
		controller: 'loginController'
	});
	$routeProvider.when('/reg', {
		templateUrl: 'views/reg.html',
		controller: 'regController'
	});
	
	$routeProvider.when('/dashboard/message', {
		templateUrl: 'views/dashboard/message.html',
		controller: 'messageController'
	});
	$routeProvider.when('/dashboard/favorite', {
		templateUrl: 'views/dashboard/favorite.html',
		controller: 'favoriteController'
	});
	$routeProvider.when('/dashboard/password', {
		templateUrl: 'views/dashboard/password.html',
		controller: 'passwordController'
	});
}]);

app.run(function($rootScope) {
	// 路由监听事件 
	$rootScope.$on('$routeChangeStart', function(event, next, current) {
	});
});

module.exports = app;