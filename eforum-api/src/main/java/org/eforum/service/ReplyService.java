package org.eforum.service;

import java.util.List;

import org.eforum.entity.Reply;
import org.eforum.vo.ArticleVo;

public interface ReplyService {

	public void commitReply(Long articleId, String replyContent);

	public List<Reply> getReplyByArticleId(Long articleId);

	public Long getReplyCountByArticleId(Long articleId);

	public void refreshReplyCount(List<ArticleVo> vos);

}
