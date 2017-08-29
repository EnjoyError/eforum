package org.eforum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 全局参数类
 * 
 * @author huxiansheng
 *
 */
@Entity(name = "GlobalParam")
@Table (name="global_param")
public class GlobalParam extends BaseEntity {
	/** 参数键 */
	@Column(name = "G_KEY", unique = true)
	private String key;
	/** 参数值 */
	@Column(name = "G_VALUE")
	private String value;
	/** 描述 */
	@Column(name = "G_DESCRIPTION")
	private String description;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
