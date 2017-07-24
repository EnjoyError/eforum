var app = require('../app');
var angular = require('angular');
app.controller('personalInformationController', function($scope,fileService) {
	$scope.myImage = '';
	$scope.myCroppedImage = '';
	$scope.uploadImage = function(){
		 var $Blob = convertBase64UrlToBlob($scope.myCroppedImage);
		 sendFile($Blob);
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
	
	function getBlobBydataURL(dataURI,type){
        var binary = atob(dataURI.split(',')[1]);
        var array = [];
        for(var i = 0; i < binary.length; i++) {
            array.push(binary.charCodeAt(i));
        }
        return new Blob([new Uint8Array(array)], {type:type });
    }
	
	function convertBase64UrlToBlob(urlData){
	    
	    var bytes=window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte
	    
	    //处理异常,将ascii码小于0的转换为大于0
	    var ab = new ArrayBuffer(bytes.length);
	    var ia = new Uint8Array(ab);
	    for (var i = 0; i < bytes.length; i++) {
	        ia[i] = bytes.charCodeAt(i);
	    }

	    return new Blob( [ab] , {type : 'image/png'});
	}
	
	function sendFile(files){
		fileService.uploadHeadPortrait(files,function(result){
            if (result.success) {
            	alert("上传成功");
            } else {
            	console.log(result.message);
            }
		},function(result){
			alert("执行到这里" + result);
		});
	}
   
});