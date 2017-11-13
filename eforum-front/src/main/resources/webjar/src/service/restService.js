var app = require('../app');
var $ = require('jquery');
app.service('restService', function($http,filterService) {

    this.get = function(url, params,successCallback) {
        var promise =  $http({
            method: 'GET',
            url: url,
            params: params
        });
        this.processPromise(promise,successCallback);
    }

    this.post = function(url, params,successCallback) {
        params = params || {};
        var promise = $http({
            method: 'POST',
            url: url,
            data: params
        });
        this.processPromise(promise,successCallback);
    }

    this.postForForm = function(url, params,successCallback) {
        params = params || {};
        var promise = $http({
            method: 'POST',
            headers:{
                "Content-Type" : "application/x-www-form-urlencoded; charset=UTF-8"
            },
            url: url,
            data: $.param(params)
        });
        this.processPromise(promise,successCallback);
    }

    this.uploadFile = function(url,data,successCallback,errorCallback) {
        errorCallback = errorCallback || this.defaultErrorCallback;
        $.ajax({
            url : url,
            type : 'POST',
            data : data,
            processData : false,
            contentType : false,
            success : function(result){
                filterService.doFilter(result);
                if(successCallback){
                    successCallback(result);
                }
            },
            error : errorCallback
        });
    }

    this.defaultErrorCallback = function(){
        modal.showMsg("请求失败!");
    }


    this.processPromise = function(promise,successCallback){
        promise.then(function(result){
            filterService.doFilter(result);
            if(successCallback){
                successCallback(result);
            }
        },this.defaultErrorCallback);
    }

});

