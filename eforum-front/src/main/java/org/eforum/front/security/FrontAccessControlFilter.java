package org.eforum.front.security;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.eforum.constant.StatusCode;
import org.eforum.produces.ResultJson;
import org.eforum.util.JsonUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FrontAccessControlFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        Subject subject = this.getSubject(request, response);
        return subject.isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = this.getSubject(request, response);
        if (!subject.isAuthenticated()) {
            ResultJson result = new ResultJson();
            result.setSuccess(false);
            result.setCode(StatusCode.SC_UNAUTHORIZED);

            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JsonUtils.toJson(result));
            return false;
        }
        return true;
    }
}
