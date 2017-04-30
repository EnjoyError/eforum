package org.eforum.front.controller;

import org.eforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
}