package org.eforum.service;

import org.eforum.entity.Role;
import org.eforum.entity.User;
import org.eforum.vo.UserVo;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Transactional
public interface SecurityService {
    public List<String> getRoleCodeByUsername(String username);
}