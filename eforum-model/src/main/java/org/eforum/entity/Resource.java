package org.eforum.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 资源类
 */
@Entity(name = "Resource")
@Table(name = "RESOURCE")
public class Resource extends BaseEntity{
    /** 编码*/
    private String code;
    /** 资源名*/
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

