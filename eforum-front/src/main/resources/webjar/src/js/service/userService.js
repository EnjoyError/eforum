var app = require('../app');
var RestTemplate = require('../rest/restTemplate');

app.service('userService', function($http, $q) {
    var rest = new RestTemplate($http, $q);

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
});