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
	
RestTemplate.fn.postForMultipart = function(url, params) {
    params = params || {};
    var promise = this.$http({
        method: 'POST',
        url: url,
        data: params,
        headers : {
        	   "Content-Type" : "multipart/form-data"
        	}
    });
    return promise;
}

module.exports = RestTemplate;