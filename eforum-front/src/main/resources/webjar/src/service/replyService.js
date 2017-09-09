var app = require('../app');
var RestTemplate = require('./restTemplate');

app.service('replyService', function($http) {
    var rest = new RestTemplate($http);
    
    this.commitReply = function(articleId,replyContent, successCallBack){
    	if(null == replyContent){
            modal.showMsg("帖子内容不能为空！");
    	}
    	if(articleId == null){
            modal.showMsg("必须基于某个帖子下面才能操作回复！");
    	}
    	rest.postForForm('/reply/commitReply', {
    		articleId : articleId,
    		replyContent: replyContent
	    },successCallBack);
    }

	this.getReplyByArticleId = function(articleId, successCallBack) {
		if (null == articleId) {
            modal.showMsg("帖子id不能为空！");
		}
		rest.postForForm('/reply/getReplyByArticleId', {
			articleId : articleId
		}, successCallBack);
	}
	
	this.getReplyCountByArticleId = function(articleId, successCallBack){
		if (null == articleId) {
            modal.showMsg("帖子id不能为空！");
		}
		rest.postForForm('/reply/getReplyCountByArticleId', {
			articleId : articleId
		}, successCallBack);
	}
});