var app = require('../app');

app.service('articleService', function($http, $q, restService) {
	this.listArticle = function(pageNumber, pageSize) {
	    return restService.get('/article', {
	        pageNumber: pageNumber,
            pageSize: pageSize
	    })
	}

	this.getArticle = function(id) {
	    return restService.get('/article/' + id);
	}

	this.listSuggestionArticle = function() {
	    return restService.get('/article/suggestion', {
	        pageSize: 5
	    });
	}

	this.listComment = function(articleId, pageNumber, pageSize) {
	    return restService.get('/article/' + articleId + '/comment', {
	        pageNumber: pageNumber,
            pageSize: pageSize
	    });
	}
});