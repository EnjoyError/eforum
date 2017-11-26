var app = require('../app');

app.service('replyService', function($http,restService) {
    this.commitReply = function(articleId,replyContent, successCallBack){
    	if(null == replyContent){
            modal.showMsg("帖子内容不能为空！");
    	}
    	if(articleId == null){
            modal.showMsg("必须基于某个帖子下面才能操作回复！");
    	}
    	restService.postForForm('/reply/commitReply', {
    		articleId : articleId,
    		replyContent: replyContent
	    },successCallBack);
    }

	this.getReplyByArticleId = function(articleId, pageNum, pageSize, successCallBack) {
		if (null == articleId) {
            modal.showMsg("帖子id不能为空！");
		}
		restService.postForForm('/reply/getReplyByArticleId', {
			articleId : articleId,
			pageNum : pageNum,
			pageSize : pageSize
		}, successCallBack);
	}
	
	this.getReplyCountByArticleId = function(articleId, successCallBack){
		if (null == articleId) {
            modal.showMsg("帖子id不能为空！");
		}
		restService.postForForm('/reply/getReplyCountByArticleId', {
			articleId : articleId
		}, successCallBack);
	}
});