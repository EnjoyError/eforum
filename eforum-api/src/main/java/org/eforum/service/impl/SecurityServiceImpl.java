package org.eforum.service.impl;

import org.eforum.entity.Role;
import org.eforum.service.SecurityService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SecurityServiceImpl extends BaseServiceImpl implements SecurityService {
    @Override
    public List<String> getRoleCodeByUsername(String username) {
        String hql = "FROM Role rl WHERE rl.id IN (SELECT urr.role.id FROM UserRoleRelation urr WHERE urr.user.name = :username)";
        Map<String, Object> condition = new HashMap<String, Object>(1);
        condition.put("username", username);
        return (List<String>) ((Object)dao.findByHql(hql, condition));
    }
}
