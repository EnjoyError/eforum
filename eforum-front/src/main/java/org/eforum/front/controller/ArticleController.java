package org.eforum.front.controller;

import org.eforum.entity.Article;
import org.eforum.produces.PageVo;
import org.eforum.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class ArticleController extends BaseController {
	@Autowired
	private ArticleService articleService;
	
	@ApiOperation(value = "文章接口", notes = "获取文章列表", code = 200, produces = "application/json")
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public Object list(int pageNumber, int pageSize) {
		PageVo<Article> pageVo = new PageVo<>();
		Page<Article> page = articleService.paginate(pageNumber, pageSize);
		pageVo.setData(page.getContent());
		pageVo.setPageCount(page.getTotalPages());
		pageVo.setPageSize(pageSize);
		pageVo.setPageIndex(pageNumber + 1);
		return pageVo;
	}
	
	@ApiOperation(value = "文章接口", notes = "获取文章", code = 200, produces = "application/json")
	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public Object find(@PathVariable("id") Long id) {
		return articleService.findArticleById(id);
	}
}