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
}