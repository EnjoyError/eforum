var app = require('../app');
var RestTemplate = require('./restTemplate');

app.service('personalInformationService', function($http,$window,util) {
    var rest = new RestTemplate($http);

	this.editUserInfo = function(scope) {
	    var promise = rest.post('/user/saveUser', {
	    	id : scope.userInfo.id,
	        realName: scope.userInfo.realName,
	        email: scope.userInfo.email,
	        mobileNumber: scope.userInfo.mobileNumber,
	        address: scope.userInfo.address,
	        gender : scope.userInfo.gender,
	        personalizedSignature: scope.userInfo.personalizedSignature
	    });
        promise.then(function(result) {
            if (result.data.success) {
                alert("保存成功");
                util.closePopBox();
                $window.location.reload();
            } else {
                alert(result.data.message);
            }
        }, function(result) {
            alert("执行到这里" + result);
        },function(result){
            alert("执行到这里   1" + result);
        });
	}
	


	this.loadUserInfoToScope = function(scope) {
		if(null == scope.userId || "" == scope.userId){
            console.log("userId is null");
            return ;
		}
		var promise = rest.post('/user/loadUserInfo', {
			id : scope.userId
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


	this.changePassword = function(scope){
        var promise = rest.post('/user/changePassword', {
            id : scope.userId,
			oldPassword : scope.editUser.oldPassword,
			password : scope.editUser.newPassword

        });
        promise.then(function(result) {
            if (result.data.success) {
                alert("修改成功");
                scope.editUser = null;
                util.closePopBox();
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