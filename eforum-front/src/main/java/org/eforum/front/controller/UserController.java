package org.eforum.front.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.eforum.entity.User;
import org.eforum.param.UserParam;
import org.eforum.produces.ResultJson;
import org.eforum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@ApiOperation(value = "用户接口", notes = "新增用户", code = 200, produces = "application/json")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public Object addUser(@RequestBody UserParam userParam) {
		User findUser = null;
		findUser = userService.findUserByName(userParam.getName());
		if (findUser != null)
			return new ResultJson(false, "账号已被注册");

		findUser = userService.findUserByEmail(userParam.getEmail());
		if (findUser != null)
			return new ResultJson(false, "邮箱已被注册");

		try {
			User user = new User();
			BeanUtils.copyProperties(user, userParam);
			user.setPassword(DigestUtils.md5Hex(userParam.getPassword()));
			userService.addUser(user);
		} catch (Exception e) {
			LOG.error("保存用户信息出现错误", e);
			return new ResultJson(false, "未知错误");
		}
		return new ResultJson(true, "保存用户信息成功");
	}
}