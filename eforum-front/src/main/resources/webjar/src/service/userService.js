var app = require('../app');

app.service('userService', function($http, $q, restService) {
	this.login = function(name, password, rememberMe) {
	    return restService.post('/login', {
	        name: name,
            password: password,
            rememberMe: rememberMe
	    });
	}

	this.logout = function() {
	    return restService.post('/logout');
	}

	this.addUser = function(name, email, password) {
	    return restService.post('/user', {
	        name: name,
	        email: email,
	        password: password
	    });
	}
});