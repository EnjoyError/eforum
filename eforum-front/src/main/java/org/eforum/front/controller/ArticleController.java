package org.eforum.front.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class ArticleController extends BaseController {
	@ApiOperation(value = "帖子接口", notes = "获取帖子信息", code = 200, produces = "application/json")
	@RequestMapping(value = "/articles/{pageIndex}", method = RequestMethod.GET)
	public Object articles(@PathVariable("pageIndex") Integer pageIndex) {
		return null;
	}
}