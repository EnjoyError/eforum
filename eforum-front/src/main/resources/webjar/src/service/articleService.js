var app = require('../app');
var RestTemplate = require('./restTemplate');

app.service('articleService', function($http) {
    var rest = new RestTemplate($http);

	this.listArticle = function(pageNumber, pageSize, successCallback) {
	    rest.get('/article/getArticleList', {
	        pageNumber: pageNumber,
            pageSize: pageSize
	    }, successCallback);
	}

	this.getArticleById = function(id, successCallback) {
	    rest.get('/article/' + id, successCallback);
	}

	this.listSuggestionArticle = function(successCallback) {
	    rest.get('/article/suggestion', {
	        pageSize: 5
	    }, successCallback);
	}

	this.listComment = function(articleId, pageNumber, pageSize, successCallback) {
	    rest.get('/article/' + articleId + '/comment', {
	        pageNumber: pageNumber,
            pageSize: pageSize
	    }, successCallback);
	}
	
	this.publishArticle = function(articleTile,articleContent, successCallback){
		rest.post('/article/publish', {
			title: articleTile,
			content: articleContent
	    }, successCallback);
	}
	
	this.parseArticleList = function(articleList){
		if(null == articleList){
			return null;
		}
		return articleList.data;
	}
	
	this.uploadImages = function(images,successCallback,errorCallback){
		var fd = new FormData();
        for (var i=0; i<images.length; i++) {
            fd.append("images", images[i]);
        }
		rest.uploadFile('article/uploadImages',fd,successCallback,errorCallback);
	}
});