package org.eforum.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 封装基础字段，一般情况，所有entity都需要继承该entity
 * 
 * @author Sackr
 *
 */
@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
	/**
	 * 最后修改时间
	 */
	@Column(name = "LAST_UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateTime;
	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	/**
	 * 最后修改者id
	 */
	@Column(name = "LAST_UPDATE_USER_ID")
	private Long lastUpdateUserId;
	/**
	 * 最后修改者姓名
	 */
	@Column(name = "LAST_UPDATE_USER_NAME")
	private String lastUpdateUserName;
	/**
	 * 创建者Id
	 */
	@Column(name = "CREATE_USER_ID")
	private Long createUserId;

	/** 创建者姓名 */
	@Column(name = "CREATE_USER_NAME")
	private String createUserName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	public String getLastUpdateUserName() {
		return lastUpdateUserName;
	}

	public void setLastUpdateUserName(String lastUpdateUserName) {
		this.lastUpdateUserName = lastUpdateUserName;
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

	/**
	 * 判断该entity是否为新建的，该方法通过id是否为空来做判断的， 如果entity被手工赋予一个id的话，则该方法的判断结果将不准确。
	 * 
	 * PS：通常不应该手工来给entity赋上ID，而是由持久层来做
	 * 
	 * @return
	 */
	public boolean isNew() {
		return (null == id);
	}
}
