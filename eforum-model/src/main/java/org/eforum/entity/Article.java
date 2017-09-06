package org.eforum.entity;

import javax.persistence.*;
import java.util.Date;

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

	/** 置顶等级,越大等级越高 */
	@Column(name = "TOP_GRADE")
	private Integer topGrade = 0;

	/** 是否精华 */
	@Column(name = "IS_ESSENCE")
	private Boolean isEssence = Boolean.FALSE;

	public Boolean getIsEssence() {
		return isEssence;
	}

	public void setIsEssence(Boolean isEssence) {
		this.isEssence = isEssence;
	}

	public Integer getTopGrade() {
		return topGrade;
	}

	public void setTopGrade(Integer topGrade) {
		this.topGrade = topGrade;
	}

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