package org.eforum.front.security;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.eforum.constant.StatusCode;
import org.eforum.produces.ResultJson;
import org.eforum.util.JsonUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤请求
 */
public class FrontAccessControlFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        Subject subject = this.getSubject(request, response);
        return subject.isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isAjax(request) && isSessionTimeOut(request)) {
            ResultJson result = new ResultJson();
            result.setSuccess(false);
            result.setCode(StatusCode.SC_SESSION_TIME_OUT);
            result.setMessage("登入超时，请重新登入!");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JsonUtils.toJson(result));
            return false;
        }

        return true;
    }

    /**
     * 判断是否是之前登入过，session超时了
     *
     * @param request
     * @return
     */
    private boolean isSessionTimeOut(ServletRequest request) {
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        if(null == cookies){
            return false;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断请求是否是ajax请求
     *
     * @param request
     * @return
     */
    private boolean isAjax(ServletRequest request) {
        String requestType = ((HttpServletRequest) request).getHeader("x-requested-with");
        if (null != requestType && requestType.equals("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }
}
