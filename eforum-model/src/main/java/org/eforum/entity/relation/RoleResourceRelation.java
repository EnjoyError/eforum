package org.eforum.entity.relation;

import org.eforum.entity.BaseEntity;
import org.eforum.entity.Resource;
import org.eforum.entity.Role;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 角色资源关联表
 */
@Entity(name = "RoleResourceRelation")
@Table(name = "ROLE_RESOURCE_RELATION")
public class RoleResourceRelation extends BaseEntity{
    /** 所属角色*/
    @JoinColumn(name = "ROLE_ID")
    @ManyToOne(targetEntity = Role.class)
    private Role role;
    /** 所属资源*/
    @JoinColumn(name = "RESOURCE_ID")
    @ManyToOne(targetEntity = Resource.class)
    private Resource resource;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
