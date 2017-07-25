var app = require('../app');
var angular = require('angular');
app.controller('personalInformationController', function($scope,fileService) {
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
		var promise = fileService.uploadHeadPortrait(base64Data);
		promise.then(function(result) {
	        if (result.data.success) {
	        	alert("上传成功");
	        } else {
	            alert(result.data.message);
	        }
	    }, function(result) {
	    	alert("执行到这里" + result);
	    },function(result){
	    	alert("执行到这里   1" + result);
	    });
	}
   
});