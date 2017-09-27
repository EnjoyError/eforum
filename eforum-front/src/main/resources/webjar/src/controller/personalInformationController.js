var app = require('../app');
var angular = require('angular');
app.controller('personalInformationController', function($scope,$cookies,personalInformationService,notificationService) {
	$scope.saveUserInfo = function(){
        personalInformationService.editUserInfo($scope);
	}

	$scope.toChangePasswordPage = function(){
	    angular.element(document.querySelector("#editPasswordPage"))[0].style.display = "block";
        util.openPopBox();
    }

    $scope.savePassword = function(){
        personalInformationService.changePassword($scope);
    }

    personalInformationService.loadUserInfoToScope($scope);

    $scope.toEditPersonalInformationPage = function(){
        angular.element(document.querySelector("#editPersonalInformationPage"))[0].style.display = "block";
        var copyUserInfo = angular.copy($scope.userInfo);
        notificationService.pubNotification("OPEN_EDIT_PERSONAL_INFORMATION_PAGE",copyUserInfo);
        util.openPopBox();
    }
});