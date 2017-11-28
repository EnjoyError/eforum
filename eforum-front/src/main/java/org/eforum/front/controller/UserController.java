package org.eforum.front.controller;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.eforum.constant.Constants;
import org.eforum.entity.Article;
import org.eforum.entity.User;
import org.eforum.exception.ServiceException;
import org.eforum.front.resolvers.AutoLoad;
import org.eforum.front.security.CurrentThreadContext;
import org.eforum.produces.ResultJson;
import org.eforum.service.ArticleService;
import org.eforum.service.UserService;
import org.eforum.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import java.util.Map;

@RestController
public class UserController extends BaseController {
	@Autowired
	private UserService userService;

	@Autowired
	private ArticleService articleService;

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
		userService.createUser(user);

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
	@RequiresAuthentication
	public Object changePassword(@AutoLoad UserVo userVo) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(Constants.CURRENT_USER_IN_SESSION);
		if(!user.getId().equals(userVo.getId())){
			throw new ServiceException("非法操作!");
		}
		userService.changePassword(userVo);
		return new ResultJson(true, "修改密码成功");
	}

	@RequestMapping(value = "/user/uploadHeadPortrait", method = RequestMethod.POST)
	@Transactional
	@RequiresAuthentication
	public Object uploadHeadPortrait(String base64Str) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(Constants.CURRENT_USER_IN_SESSION);
		user = userService.findUserById(user.getId());
		userService.uploadHeadPortrait(user, base64Str);
		return new ResultJson(true, "上传成功");
	}

	@RequestMapping(value = "/user/downloadheadPortrait/{userId:.*}", method = RequestMethod.GET)
	@Transactional
	public void downloadheadPortrait(@PathVariable("userId") Long userId, HttpServletResponse response) {
		User user = userService.findUserById(userId);
		userService.downloadheadPortrait(user, response);
	}

	@RequestMapping(value = "/user/loadCurrentUserInfo")
	@Transactional
	@RequiresAuthentication
	public Object loadCurrentUserInfo() {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(Constants.CURRENT_USER_IN_SESSION);
		user = userService.findUserById(user.getId());
		subject.getSession().setAttribute(Constants.CURRENT_USER_IN_SESSION,user);
		return new ResultJson(true, user);
	}

	@RequestMapping(value = "/user/saveUser")
	@Transactional
	@RequiresAuthentication
	public Object saveUser(@AutoLoad User user) {
		userService.saveUser(user);
		return new ResultJson(true, "保存成功");
	}

	@RequestMapping(value = "/user/userIsJy")
	@Transactional
	public Object userIsJy(@AutoLoad User user) {
		Boolean isJy = userService.userIsJy(user);
		return new ResultJson(true, isJy);
	}

	@RequestMapping(value = "/user/findUser")
	public Object findUser(@RequestBody Map map){
		Long userId = Long.valueOf(String.valueOf(map.get("userId")));
		User user = userService.findUserById(userId);
		user.setPassword(null);
		return new ResultJson(true, user);
	}

	@RequestMapping(value = "/user/shutupOrReleaseUser")
	@Transactional
	@RequiresAuthentication
	public Object shutupOrReleaseUser(@RequestBody Map<String, Object> map){
		Long userId = Long.valueOf(String.valueOf(map.get("userId")));
		User currentUser = CurrentThreadContext.getCurrentUser();
		if(currentUser.getId().longValue() == userId.longValue()){
			throw new ServiceException("不能对自己进行禁言操作！");
		}
		String isJy = String.valueOf(map.get("isJy"));
		if("1".equals(isJy)){
			userService.shutupUser(userId);
		} else {
			userService.ReleaseShutup(userId);
		}
		return new ResultJson(true, "保存成功");
	}
}