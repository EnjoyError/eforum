var app = require('../app');
var RestTemplate = require('./restTemplate');

app.service('fileService', function($http) {
    var rest = new RestTemplate($http);
	
	this.uploadImages = function(images,successCallback,errorCallback){
		var fd = new FormData();
        for (var i=0; i<images.length; i++) {
            fd.append("images", images[i]);
        }
		rest.uploadFile('article/uploadImages',fd,successCallback,errorCallback);
	}
	
	this.uploadHeadPortrait = function(base64Str){
		return rest.postForForm('user/uploadHeadPortrait',{base64Str:base64Str});
	}
});