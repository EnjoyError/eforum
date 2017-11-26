package org.eforum.service;

import java.util.List;

import org.eforum.entity.Reply;
import org.eforum.produces.PageVo;
import org.eforum.vo.ArticleVo;

public interface ReplyService {

	public void commitReply(Long articleId, String replyContent);

	public PageVo<Reply> getReplyByArticleId(Long articleId, Integer pageNum, Integer pageSize);

	public Long getReplyCountByArticleId(Long articleId);

	public void refreshReplyCount(List<ArticleVo> vos);

}
