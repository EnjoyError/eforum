package org.eforum.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * 评论
 */
@Entity(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
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
	 * 创建时间
	 */
	@Column(name = "create_time")
	@Temporal(TemporalType.DATE)
	private Date createTime;
	/**
	 * 所属主评论
	 */
	@ManyToOne(targetEntity = Comment.class)
	@JoinColumn(name = "main_comment_id")
	private Comment mainComment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Comment getMainComment() {
		return mainComment;
	}

	public void setMainComment(Comment mainComment) {
		this.mainComment = mainComment;
	}

}
