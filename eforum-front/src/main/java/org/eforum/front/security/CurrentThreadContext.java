package org.eforum.front.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.eforum.constant.Constants;
import org.eforum.entity.User;

/**
 * 线程上下文
 */
public class CurrentThreadContext {
    /**
     * 获取当前登入用户
     * 
     * @return
     */
    public static User getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute(Constants.CURRENT_USER_IN_SESSION);
        return user;
    }
}
