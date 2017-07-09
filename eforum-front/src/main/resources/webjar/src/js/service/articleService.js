var app = require('../app');
var RestTemplate = require('../rest/restTemplate');

app.service('articleService', function($http) {
    var rest = new RestTemplate($http);

	this.listArticle = function(pageNumber, pageSize) {
	    return rest.get('/article/getArticleList', {
	        pageNumber: pageNumber,
            pageSize: pageSize
	    })
	}

	this.getArticleById = function(id) {
	    return rest.get('/article/' + id);
	}

	this.listSuggestionArticle = function() {
	    return rest.get('/article/suggestion', {
	        pageSize: 5
	    });
	}

	this.listComment = function(articleId, pageNumber, pageSize) {
	    return rest.get('/article/' + articleId + '/comment', {
	        pageNumber: pageNumber,
            pageSize: pageSize
	    });
	}
	
	this.publishArticle = function(articleTile,articleContent){
		return rest.post('/article/publish', {
			title: articleTile,
			content: articleContent
	    });
	}
	
	this.parseArticleList = function(articleList){
		if(null == articleList){
			return null;
		}
		return articleList.data;
	}
});