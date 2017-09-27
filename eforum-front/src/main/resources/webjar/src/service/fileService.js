var app = require('../app');

app.service('fileService', function($http,restService) {

	this.uploadImages = function(images,successCallback,errorCallback){
		var fd = new FormData();
        for (var i=0; i<images.length; i++) {
            fd.append("images", images[i]);
        }
        restService.uploadFile('article/uploadImages',fd,successCallback,errorCallback);
	}
	
	this.uploadHeadPortrait = function(base64Str,successCallback){
        restService.postForForm('user/uploadHeadPortrait',{base64Str:base64Str},successCallback);
	}
});