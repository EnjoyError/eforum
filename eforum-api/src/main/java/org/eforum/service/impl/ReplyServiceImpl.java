package org.eforum.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.eforum.constant.Constants;
import org.eforum.entity.Article;
import org.eforum.entity.Reply;
import org.eforum.entity.User;
import org.eforum.exception.ServiceException;
import org.eforum.produces.PageVo;
import org.eforum.service.ArticleService;
import org.eforum.service.ReplyService;
import org.eforum.util.StringUtils;
import org.eforum.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl extends BaseServiceImpl implements ReplyService {
	@Autowired
	private ArticleService articleService;

	@Override
	public void commitReply(Long articleId, String replyContent) {
		if (null == articleId) {
			throw new ServiceException("所属帖子ID不能为空!");
		}
		if (StringUtils.isNullOrEmpty(replyContent) || "<p><br></p>".equals(replyContent)) {
			throw new ServiceException("回复内容不能为空！");
		}
		Article article = articleService.findArticleById(articleId);
		if (null == article) {
			throw new ServiceException("未找到ID为【" + articleId + "】的帖子!");
		}
		Reply reply = new Reply();
		reply.setContent(replyContent);
		reply.setArticle(article);
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(Constants.CURRENT_USER_IN_SESSION);
		if (null == user) {
			user = dao.get(User.class, 1L);
			dao.save(reply, user);
		} else {
			dao.save(reply);
		}
		articleService.refreshLastUpdateTimeForAll(articleId, reply.getLastUpdateTime());
	}

	@SuppressWarnings("unchecked")
	public PageVo<Reply> getReplyByArticleId(Long articleId, Integer pageNum, Integer pageSize) {
		if (null == articleId) {
			throw new ServiceException("帖子ID不能为空！");
		}
		String hql = "FROM Reply reply WHERE reply.article.id = '" + articleId + "' ORDER BY reply.createTime DESC";
		PageVo<Reply> replyPage = dao.pagingQueryAndPackage(hql,pageNum,pageSize,Reply.class);
		return replyPage;
	}

	@Override
	public Long getReplyCountByArticleId(Long articleId) {
		String hql = "SELECT COUNT(*) FROM Reply reply WHERE reply.article.id = :id";
		Long replyCount = (Long) dao.findUniqueByHql(hql, "id", articleId);
		return replyCount;
	}

	@Override
	public void refreshReplyCount(List<ArticleVo> vos) {
		for (ArticleVo vo : vos) {
			Long count = getReplyCountByArticleId(vo.getId());
			vo.setReplyCount(count);
		}
	}
}
