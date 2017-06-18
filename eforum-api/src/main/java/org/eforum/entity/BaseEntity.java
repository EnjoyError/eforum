package org.eforum.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	 * 最后修改者
	 */
	@JoinColumn(name = "LAST_UPDATE_USER_ID")
	@ManyToOne(targetEntity = User.class)
	private User lastUpdateUser;
	/**
	 * 创建者
	 */
	@JoinColumn(name = "CREATE_USER_ID")
	@ManyToOne(targetEntity = User.class)
	private User createUser;

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

	public User getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(User lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
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
