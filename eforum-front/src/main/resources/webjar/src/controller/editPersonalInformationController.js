var app = require('../app');
app.controller('editPersonalInformationController', function($scope,personalInformationService,notificationService) {
	$scope.saveUserInfo = function(){
        personalInformationService.editUserInfo($scope);
	};
	var openEditPersonalInformationPage = function(userInfo){
		$scope.userInfo = userInfo;
	};
    notificationService.registerCallBack("OPEN_EDIT_PERSONAL_INFORMATION_PAGE",openEditPersonalInformationPage);
});