var app = require('../app');

app.service('articleService', function($http,restService) {

	this.listArticle = function(pageNumber, pageSize, successCallback) {
	    restService.get('/article/getArticleList', {
	        pageNumber: pageNumber,
            pageSize: pageSize
	    }, successCallback);
	}

	this.getArticleById = function(id, successCallback) {
	    restService.get('/article/' + id, null, successCallback);
	}

	this.listSuggestionArticle = function(successCallback) {
	    restService.get('/article/suggestion', {
	        pageSize: 5
	    }, successCallback);
	}

	this.listComment = function(articleId, pageNumber, pageSize, successCallback) {
	    restService.get('/article/' + articleId + '/comment', {
	        pageNumber: pageNumber,
            pageSize: pageSize
	    }, successCallback);
	}
	
	this.publishArticle = function(articleTile,articleContent, successCallback){
		restService.post('/article/publish', {
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
		restService.uploadFile('article/uploadImages',fd,successCallback,errorCallback);
	};

	this.update = function(article, successCallback){
		restService.post('/article/update', article, successCallback);
	}
});