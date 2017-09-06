package org.eforum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 管理员
 */
@Entity(name = "admin")
public class Admin extends BaseEntity{
    /**
     * 登录账号
     */
    @Column
    private String name;
    /**
     * 登录密码
     */
    @Column
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
