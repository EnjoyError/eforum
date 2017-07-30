var app = require('../app');
var RestTemplate = require('./restTemplate');

app.service('userService', function($http) {
    var rest = new RestTemplate($http);

	this.login = function(name, password, rememberMe) {
	    return rest.post('/login', {
	        name: name,
            password: password,
            rememberMe: rememberMe
	    });
	}

	this.logout = function() {
	    return rest.post('/logout');
	}

	this.addUser = function(name, email, password) {
	    return rest.post('/user', {
	        name: name,
	        email: email,
	        password: password
	    });
	}
});