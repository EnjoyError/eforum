var app = require('../app');
var angular = require('angular');
app.controller('personalInformationController', function(util,$scope,$cookies,personalInformationService) {
	$scope.saveUserInfo = function(){
        personalInformationService.editUserInfo($scope);
	}
	$scope.userId = $cookies.get("userId");
	personalInformationService.loadUserInfoToScope($scope);

	$scope.toChangePasswordPage = function(){
	    angular.element(document.querySelector("#editPasswordPage"))[0].style.display = "block";
        util.openPopBox();
    }

    $scope.savePassword = function(){
        personalInformationService.changePassword($scope);
    }

    $scope.toEditPersonalInformationPage = function(){
        angular.element(document.querySelector("#editPersonalInformationPage"))[0].style.display = "block";
        util.openPopBox();
    }
});