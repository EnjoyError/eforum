var angular = require('angular');
require('angular-route');
require('angular-cookies');
require('./lib/paging/tm.pagination');
require('./lib/crop/ng-img-crop');
require('angular-strap');	//angularStrap
require('angular-animate');	//angular动画，angularStrap需要该插件
require('angular-motion');	//引入css，angularStrap需要
require('angular-sanitize');
var app = angular.module('app', ['ngRoute','ngCookies','tm.pagination', 'ngImgCrop', 'mgcrea.ngStrap', 'ngAnimate', 'ngSanitize']);
//angularjs 1.6.0 以上版本需要配置,否则路由无法正常使用
app.config(["$locationProvider",function($locationProvider){
	$locationProvider.hashPrefix("");
}]);

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
		templateUrl: 'views/main.html'
	});
	$routeProvider.when('/about', {
		templateUrl: 'views/about.html',
		controller: 'aboutController'
	});
	$routeProvider.when('/article', {
		templateUrl: 'views/article.html',
		controller: 'articleController'
	});
	$routeProvider.when('/pubArticle', {
		templateUrl: 'views/editArticle.html',
		controller: 'pubArticleController'
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
	$routeProvider.when('/dashboard/personalInformation', {
		templateUrl: 'views/dashboard/personalInformation.html',
		controller: 'personalInformationController'
	});
	$routeProvider.when('/dashboard/headPortrait', {
		templateUrl: 'views/dashboard/headPortrait.html',
		controller: 'headPortraitController'
	});
	$routeProvider.when('/articleList', {
		templateUrl: 'views/articleList.html',
		controller: 'articleListController'
	});
}]);

app.run(function($rootScope, globalService) {
	// 路由监听事件 
	$rootScope.$on('$routeChangeStart', function(event, next, current) {
		if(current && next && "views/article.html" == current.$$route.templateUrl && "views/articleList.html" == next.$$route.templateUrl){
            globalService.isFromArticleToList = true;
		} else {
            globalService.isFromArticleToList = false;
		}
	});
});

//自定义过滤器，用来对帖子列表时间进行转换
app.filter('myDate', function($filter) { //可以注入依赖
    return function(lastUpdateTimeForAll,currentTime) {
    	var subTime = currentTime - lastUpdateTimeForAll;
    	var day20  = 20*24*60*60*1000;	//20天
    	var day1 = 24*60*60*1000;		//1天
    	var hour1 = 60*60*1000;			//1小时
    	var min1 = 60*1000;				//1分钟
    	var viewTime = "";				//需要显示的时间
    	if(subTime >= day20){
    		viewTime = $filter('date')(lastUpdateTimeForAll, "yyyy-MM-dd hh:mm:ss"); 
    		return viewTime;
    	}
    	if(subTime >= day1){
    		viewTime = Math.floor(subTime/day1);
    		return  viewTime + " 天前"
    	}
    	if(subTime >= hour1){
    		viewTime = Math.floor(subTime/hour1);
    		return  viewTime + " 小时前"
    	}
    	if(subTime >= min1){
    		viewTime = Math.floor(subTime/min1);
    		return  viewTime + " 分钟前"
    	}
    	viewTime = "刚刚";
        return viewTime;
    }
});

module.exports = app;