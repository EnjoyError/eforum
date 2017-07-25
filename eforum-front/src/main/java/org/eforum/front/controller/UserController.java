package org.eforum.front.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.eforum.constant.Constants;
import org.eforum.entity.User;
import org.eforum.exception.ServiceException;
import org.eforum.front.vo.UserVo;
import org.eforum.produces.ResultJson;
import org.eforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController extends BaseController {
	@Autowired
	private UserService userService;

	@ApiOperation(value = "用户接口", notes = "新增用户", code = 200, produces = "application/json")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@Transactional
	public Object addUser(@RequestBody UserVo userVo) {
		User user = new User();
		try {
			BeanUtils.copyProperties(user, userVo);
		} catch (Exception e) {
			throw new ServiceException("保存用户信息出错", e);
		}
		user.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
		userService.addUser(user);

		return new ResultJson(true, "保存用户信息成功");
	}

	@ApiOperation(value = "用户接口", notes = "获取用户文章列表", code = 200, produces = "application/json")
	@RequestMapping(value = "/user/{userId}/article", method = RequestMethod.POST)
	public Object listArticle(@PathVariable("userId") Long userId, Integer pageNumber, Integer pageSize) {
		return null;
	}

	@ApiOperation(value = "用户接口", notes = "获取用户评论列表", code = 200, produces = "application/json")
	@RequestMapping(value = "/user/{userId}/comment", method = RequestMethod.POST)
	public Object listComment(@PathVariable("userId") Long userId, Integer pageNumber, Integer pageSize) {
		return null;
	}

	@ApiOperation(value = "用户接口", notes = "修改密码", code = 200, produces = "application/json")
	@RequestMapping(value = "/user/changePassword", method = RequestMethod.POST)
	@Transactional
	public Object changePassword(String newPassword) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(Constants.CURRENT_USER_IN_SESSION);
		newPassword = DigestUtils.md5Hex(newPassword);
		userService.changePassword(user, newPassword);
		return new ResultJson(true, "修改密码成功");
	}

	@RequestMapping(value = "/user/uploadHeadPortrait", method = RequestMethod.POST)
	@Transactional
	public Object uploadHeadPortrait(String base64Str) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(Constants.CURRENT_USER_IN_SESSION);
		user = userService.findUserById(user.getId());
		userService.uploadHeadPortrait(user, base64Str);
		return new ResultJson(true, "上传成功");
	}

	@RequestMapping(value = "/user/downloadheadPortrait", method = RequestMethod.GET)
	@Transactional
	public void downloadheadPortrait(HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(Constants.CURRENT_USER_IN_SESSION);
		userService.downloadheadPortrait(user, response);
	}
}