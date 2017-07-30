function RestTemplate($http) {
    this.$http = $http;
}

RestTemplate.fn = RestTemplate.prototype;

RestTemplate.fn.get = function(url, params) {
    var promise =  this.$http({
        method: 'GET',
        url: url,
        params: params
    });
    return promise;
}

RestTemplate.fn.post = function(url, params) {
    params = params || {};
    var promise = this.$http({
        method: 'POST',
        url: url,
        data: params
    });
    return promise;
}

RestTemplate.fn.postForForm = function(url, params) {
    params = params || {};
    var promise = this.$http({
        method: 'POST',
        headers:{
        	"Content-Type" : "application/x-www-form-urlencoded; charset=UTF-8"
        },
        url: url,
        data: $.param(params)
    });
    return promise;
}
	
RestTemplate.fn.uploadFile = function(url,data,successCallback,errorCallback) {
	 $.ajax({
	        url : url,
	        type : 'POST',
	        data : data,
	        processData : false,
	        contentType : false,
	        success : successCallback,
	        error : errorCallback
	    });
}

module.exports = RestTemplate;