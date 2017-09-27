var app = require('../app');

app.service('personalInformationService', function($http,$window,restService, $cookies) {

	this.editUserInfo = function(scope) {
	    restService.post('/user/saveUser', {
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
		restService.post('/user/loadCurrentUserInfo',null,function(result) {
			if (result.data.success) {
				scope.userInfo = result.data.message;
			} else {
				modal.showMsg(result.data.message);
			}
		});
	}


	this.changePassword = function(scope){
        restService.post('/user/changePassword', {
            id : $cookies.get("userId"),
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