package org.eforum.vo;

import java.util.Date;

/**
 * 帖子Vo
 * 
 * @author Sackr
 *
 */
public class ArticleVo {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 最后更新时间
	 */
	private Date lastUpdateTimeForAll;

	/**
	 * 创建人Id
	 */
	private Long createUserId;

	/** 创建人姓名 */
	private String createUserName;

	/**
	 * 总回复数
	 */
	private Long replyCount;

	/**
	 * 当前系统时间
	 */
	private Date currentTime = new Date();

	/** 置顶等级,越大等级越高 */
	private Integer topGrade = 0;

	/** 是否精华 */
	private Boolean isEssence = Boolean.FALSE;

	public Integer getTopGrade() {
		return topGrade;
	}

	public void setTopGrade(Integer topGrade) {
		this.topGrade = topGrade;
	}

	public Boolean getIsEssence() {
		return isEssence;
	}

	public void setIsEssence(Boolean isEssence) {
		this.isEssence = isEssence;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public Date getLastUpdateTimeForAll() {
		return lastUpdateTimeForAll;
	}

	public void setLastUpdateTimeForAll(Date lastUpdateTimeForAll) {
		this.lastUpdateTimeForAll = lastUpdateTimeForAll;
	}

	public Long getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Long replyCount) {
		this.replyCount = replyCount;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
