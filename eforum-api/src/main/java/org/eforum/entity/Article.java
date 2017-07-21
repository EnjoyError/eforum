package org.eforum.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 文章
 */
@Entity(name = "Article")
@Table(name = "ARTICLE")
public class Article extends BaseEntity {
	/**
	 * 用户主键
	 */
	@JoinColumn(name = "user_id")
	@ManyToOne(targetEntity = User.class)
	private User user;
	/**
	 * 文章标题
	 */
	@Column
	private String title;
	/**
	 * 文章描述
	 */
	@Column
	private String description;
	/**
	 * 文章内容
	 */
	@Lob 
	@Column(columnDefinition = "TEXT")
	private String content;
	/**
	 * 权重
	 */
	@Column
	private Integer weight;
	
	/** 最后修改时间，包括评论，当有新的评论时，该时间也会更新 */
	@Column(name = "LAST_UPDATE_TIME_FOR_ALL")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateTimeForAll;
	
	public Date getLastUpdateTimeForAll() {
		return lastUpdateTimeForAll;
	}

	public void setLastUpdateTimeForAll(Date lastUpdateTimeForAll) {
		this.lastUpdateTimeForAll = lastUpdateTimeForAll;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}