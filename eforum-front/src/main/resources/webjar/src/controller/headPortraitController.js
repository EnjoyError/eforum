var app = require('../app');
var angular = require('angular');
app.controller('headPortraitController', function($scope,fileService,$window) {
	$scope.myImage = '';
	$scope.myCroppedImage = '';
	$scope.uploadImage = function(){
		var base64Data = $scope.myCroppedImage;
		sendFile(base64Data);
	}
	var handleFileSelect = function(evt) {
		var file = evt.currentTarget.files[0];
		var reader = new FileReader();
		reader.onload = function(evt) {
			$scope.$apply(function($scope) {
				$scope.myImage = evt.target.result;
			});
		};
		reader.readAsDataURL(file);
	};
	angular.element(document.querySelector('#fileInput')).on('change',
			handleFileSelect);
	
	function sendFile(base64Data){
		fileService.uploadHeadPortrait(base64Data,function(result) {
	        if (result.data.success) {
                modal.alert("上传成功");
	        	$window.location.reload();
	        } else {
                modal.showMsg(result.data.message);
	        }
	    });
	}
   
});