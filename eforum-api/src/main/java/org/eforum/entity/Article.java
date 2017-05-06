package org.eforum.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * 文章
 */
@Entity(name = "article")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
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
	@Column
	private String content;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	@Temporal(TemporalType.DATE)
	private Date createTime;
	/**
	 * 权重
	 */
	@Column
	private Integer weight;
	
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

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}