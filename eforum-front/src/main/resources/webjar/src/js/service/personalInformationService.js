var app = require('../app');
var RestTemplate = require('../rest/restTemplate');

app.service('personalInformationService', function($http) {
    var rest = new RestTemplate($http);

	this.editUserInfo = function(scope) {
	    return rest.post('/user', {
	        realName: scope.userInfo.name,
	        email: scope.userInfo.email,
	        mobileNumber: scope.userInfo.mobileNumber,
	        address: scope.userInfo.address,
	        gender : scope.userInfo.gender,
	        personalizedSignature: scope.userInfo.personalizedSignature
	    });
	}
	


	this.loadUserInfoToScope = function(scope) {
		promise = rest.post('/user', {
			userId : scope.userId
		});
		promise.then(function(result) {
			if (result.data.success) {
				scope.userInfo = result.data.message;
			} else {
				alert(result.data.message);
			}
		}, function(result) {
			alert("执行到这里" + result);
		}, function(result) {
			alert("执行到这里   1" + result);
		});
	}
});