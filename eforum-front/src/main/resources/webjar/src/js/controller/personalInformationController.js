var app = require('../app');
var angular = require('angular');
app.controller('personalInformationController', function($scope,$cookies,personalInformationService) {
	$scope.saveUserInfo = function(){
        var promise = personalInformationService.editUserInfo($scope);
        promise.then(function(result) {
            if (result.data.success) {
            	alert("保存成功");
            } else {
                alert(result.data.message);
            }
        }, function(result) {
        	alert("执行到这里" + result);
        },function(result){
        	alert("执行到这里   1" + result);
        });
	}
	$scope.userId = $cookies.get("userId");
	personalInformationService.loadUserInfoToScope($scope);
});