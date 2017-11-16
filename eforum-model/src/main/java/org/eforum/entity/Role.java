package org.eforum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色
 */
@Entity(name = "Role")
@Table(name = "ROLE")
public class Role extends BaseEntity{
    /** 编码*/
    @Column(name = "CODE")
    private String code;
    /** 角色名称*/
    @Column(name = "NAME")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
