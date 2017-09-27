var app = require('../app');

app.service('userService', function($http,restService) {

	this.login = function(name, password, rememberMe, successCallBack) {
	    restService.post('/login', {
	        name: name,
            password: password,
            rememberMe: rememberMe
	    }, successCallBack);
	};

	this.logout = function(successCallBack) {
	    restService.post('/logout',null, successCallBack);
	};

	this.addUser = function(name, email, password, successCallBack) {
	    restService.post('/user', {
	        name: name,
	        email: email,
	        password: password
	    },successCallBack);
	};

	this.findUser = function(userId, articleId, successCallBack){
        restService.post('/user/findUser', {
            userId: userId,
            articleId: articleId
        },successCallBack);
	};
});