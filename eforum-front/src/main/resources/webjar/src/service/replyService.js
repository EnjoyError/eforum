var app = require('../app');
var RestTemplate = require('./restTemplate');

app.service('replyService', function($http) {
    var rest = new RestTemplate($http);
    
    this.commitReply = function(articleId,replyContent){
    	if(null == replyContent){
    		alert("帖子内容不能为空！");
    	}
    	if(articleId == null){
    		alert("必须基于某个帖子下面才能操作回复！");
    	}
    	return rest.postForForm('/reply/commitReply', {
    		articleId : articleId,
    		replyContent: replyContent
	    });
    }

	this.getReplyByArticleId = function(articleId) {
		if (null == articleId) {
			alert("帖子id不能为空！");
		}
		return rest.postForForm('/reply/getReplyByArticleId', {
			articleId : articleId
		});
	}
	
	this.getReplyCountByArticleId = function(articleId){
		if (null == articleId) {
			alert("帖子id不能为空！");
		}
		return rest.postForForm('/reply/getReplyCountByArticleId', {
			articleId : articleId
		});
	}
});