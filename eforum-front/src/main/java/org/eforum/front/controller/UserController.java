package org.eforum.front.controller;

import org.eforum.entity.User;
import org.eforum.produces.ResultJson;
import org.eforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "用户接口", notes = "获取用户信息", code = 200, produces = "application/json")
	public Object user(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ApiOperation(value = "用户接口", notes = "新增用户信息", code = 200, produces = "application/json")
	public Object user(User user) {
		User s = userService.saveUser(user);
		return new ResultJson(true, "用户id: " + s.getId());
	}
}