package org.eforum.front.vo;

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
