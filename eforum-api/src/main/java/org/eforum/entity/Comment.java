package org.eforum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 评论
 */
@Entity(name = "Comment")
@Table(name = "COMMENT")
public class Comment extends BaseEntity{
	/**
	 * 文章主键
	 */
	@JoinColumn(name = "article_id")
	@ManyToOne(targetEntity = Article.class)
	private Article article;
	/**
	 * 用户主键
	 */
	@JoinColumn(name = "user_id")
	@ManyToOne(targetEntity = User.class)
	private User user;
	/**
	 * 评论内容
	 */
	@Column
	private String content;
	/**
	 * 所属主评论
	 */
	@ManyToOne(targetEntity = Comment.class)
	@JoinColumn(name = "main_comment_id")
	private Comment mainComment;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Comment getMainComment() {
		return mainComment;
	}

	public void setMainComment(Comment mainComment) {
		this.mainComment = mainComment;
	}

}
