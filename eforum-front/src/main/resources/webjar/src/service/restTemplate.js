function RestTemplate($http) {
    this.$http = $http;
}

RestTemplate.fn = RestTemplate.prototype;

RestTemplate.fn.get = function(url, params,successCallback) {
    var promise =  this.$http({
        method: 'GET',
        url: url,
        params: params
    });
    this.processPromise(promise,successCallback);
}

RestTemplate.fn.post = function(url, params,successCallback) {
    params = params || {};
    var promise = this.$http({
        method: 'POST',
        url: url,
        data: params
    });
    this.processPromise(promise,successCallback);
}

RestTemplate.fn.postForForm = function(url, params,successCallback) {
    params = params || {};
    var promise = this.$http({
        method: 'POST',
        headers:{
        	"Content-Type" : "application/x-www-form-urlencoded; charset=UTF-8"
        },
        url: url,
        data: $.param(params)
    });
    this.processPromise(promise,successCallback);
}
	
RestTemplate.fn.uploadFile = function(url,data,successCallback,errorCallback) {
    errorCallback = errorCallback || this.defaultErrorCallback;
	 $.ajax({
	        url : url,
	        type : 'POST',
	        data : data,
	        processData : false,
	        contentType : false,
	        success : function(result){
                //TODO 过滤操作
                if(successCallback){
                    successCallback(result);
                }
            },
	        error : errorCallback
	    });
}

RestTemplate.fn.defaultErrorCallback = function(){
    modal.showMsg("请求失败!");
}


RestTemplate.fn.processPromise = function(promise,successCallback){
    promise.then(function(result){
        //TODO 过滤操作
        if(successCallback){
            successCallback(result);
        }
    },this.defaultErrorCallback);
}



module.exports = RestTemplate;