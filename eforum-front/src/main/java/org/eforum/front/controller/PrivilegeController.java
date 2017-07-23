package org.eforum.front.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.eforum.constant.Constants;
import org.eforum.entity.User;
import org.eforum.front.vo.UserVo;
import org.eforum.produces.ResultJson;
import org.eforum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class PrivilegeController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(PrivilegeController.class);
	@Autowired
	private UserService userService;

	@ApiOperation(value = "权限接口", notes = "用户登录", code = 200, produces = "application/json")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Transactional
	public Object login(Model model, @RequestBody UserVo userVo, HttpServletResponse response) {
		UsernamePasswordToken token = new UsernamePasswordToken(userVo.getName(), userVo.getPassword());
		if (userVo.isRememberMe()) {
			token.setRememberMe(true);
		}
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (IncorrectCredentialsException ice) {
			LOG.error("用户名或者密码错误", ice);
			return new ResultJson(false, "用户名或者密码错误");
		} catch (UnknownAccountException uae) {
			LOG.error("用户名不存在", uae);
			return new ResultJson(false, "用户名不存在");
		} catch (ExcessiveAttemptsException eae) {
			LOG.error("登录错误次数过多", eae);
			return new ResultJson(false, "登录错误次数过多");
		} catch (Exception e) {
			LOG.error("未知错误", e);
			return new ResultJson(false, e.getMessage());
		}
		User findUser = userService.findUserByName(userVo.getName());
		subject.getSession().setAttribute(Constants.CURRENT_USER_IN_SESSION, findUser);
		Map<String, String> messageMap = new HashMap<String, String>();
		messageMap.put("username", (String) subject.getPrincipal());
		Cookie cookie = new Cookie("username", (String) subject.getPrincipal());
		response.addCookie(cookie);
		return new ResultJson(true, messageMap);
	}

	@ApiOperation(value = "权限接口", notes = "用户注销", code = 200, produces = "application/json")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public Object logout(HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().removeAttribute(Constants.CURRENT_USER_IN_SESSION);
		subject.logout();
		Cookie cookie = new Cookie("username", (String) subject.getPrincipal());
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		return new ResultJson(true, "注销成功");
	}
}
