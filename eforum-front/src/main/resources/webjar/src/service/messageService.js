var app = require('../app');

app.service('messageService', function($http,restService) {

	this.send = function(message, userId, successCallback) {
	    restService.post('/message/send', {
	        content : message,
            toUser : {
	        	id : userId
            }
	    }, successCallback);
	}
});