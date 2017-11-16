package org.eforum.entity.relation;

import org.eforum.entity.BaseEntity;
import org.eforum.entity.Role;
import org.eforum.entity.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户角色关联类
 */
@Entity(name = "UserRoleRelation")
@Table(name = "USER_ROLE_RELATION")
public class UserRoleRelation extends BaseEntity{
    /** 所示用户*/
    @JoinColumn(name = "USER_ID")
    @ManyToOne(targetEntity = User.class)
    private User user;
    /** 所属角色*/
    @JoinColumn(name = "ROLE_ID")
    @ManyToOne(targetEntity = Role.class)
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
