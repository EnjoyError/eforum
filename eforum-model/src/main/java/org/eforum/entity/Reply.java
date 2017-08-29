package org.eforum.entity;

import javax.persistence.*;

/**
 * 帖子的回复
 */
@Entity(name = "Reply")
@Table(name = "REPLY")
public class Reply extends BaseEntity {
	/**
	 * 所属帖子
	 */
	@JoinColumn(name = "ARTICLE_ID")
	@ManyToOne(targetEntity = Article.class)
	private Article article;
	/**
	 * 文章内容
	 */
	@Lob 
	@Column(columnDefinition = "TEXT")
	private String content;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
