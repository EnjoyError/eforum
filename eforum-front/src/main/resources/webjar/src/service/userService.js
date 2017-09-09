var app = require('../app');
var RestTemplate = require('./restTemplate');

app.service('userService', function($http) {
    var rest = new RestTemplate($http);

	this.login = function(name, password, rememberMe, successCallBack) {
	    rest.post('/login', {
	        name: name,
            password: password,
            rememberMe: rememberMe
	    }, successCallBack);
	}

	this.logout = function(successCallBack) {
	    rest.post('/logout',null, successCallBack);
	}

	this.addUser = function(name, email, password, successCallBack) {
	    rest.post('/user', {
	        name: name,
	        email: email,
	        password: password
	    },successCallBack);
	}
});