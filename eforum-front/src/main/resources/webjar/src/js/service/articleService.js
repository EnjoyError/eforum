var app = require('../app');
var RestTemplate = require('../rest/restTemplate');

app.service('articleService', function($http, $q) {
    var rest = new RestTemplate($http, $q);

	this.listArticle = function(pageNumber, pageSize) {
	    return rest.get('/article', {
	        pageNumber: pageNumber,
            pageSize: pageSize
	    })
	}

	this.getArticle = function(id) {
	    return rest.get('/article/' + id);
	}

	this.listSuggestionArticle = function() {
	    return rest.get('/article/suggestion', {
	        pageSize: 5
	    });
	}
});