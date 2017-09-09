var app = require('../app');
var RestTemplate = require('./restTemplate');

app.service('personalInformationService', function($http,$window) {
    var rest = new RestTemplate($http);

	this.editUserInfo = function(scope) {
	    rest.post('/user/saveUser', {
	    	id : scope.userInfo.id,
	        realName: scope.userInfo.realName,
	        email: scope.userInfo.email,
	        mobileNumber: scope.userInfo.mobileNumber,
	        address: scope.userInfo.address,
	        gender : scope.userInfo.gender,
	        personalizedSignature: scope.userInfo.personalizedSignature
	    },function(result) {
            if (result.data.success) {
                modal.alert("保存成功");
                util.closePopBox();
                $window.location.reload();
            } else {
                modal.showMsg(result.data.message);
            }
        });
	}
	


	this.loadUserInfoToScope = function(scope) {
		if(null == scope.userId || "" == scope.userId){
            console.log("userId is null");
            return ;
		}
		rest.post('/user/loadUserInfo', {
			id : scope.userId
		},function(result) {
			if (result.data.success) {
				scope.userInfo = result.data.message;
			} else {
                modal.showMsg(result.data.message);
			}
		});
	}


	this.changePassword = function(scope){
        rest.post('/user/changePassword', {
            id : scope.userId,
			oldPassword : scope.editUser.oldPassword,
			password : scope.editUser.newPassword

        },function(result) {
            if (result.data.success) {
                modal.alert("修改成功");
                scope.editUser = null;
                util.closePopBox();
            } else {
                modal.showMsg(result.data.message);
            }
        });
	}
});