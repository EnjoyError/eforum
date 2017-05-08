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
	public Object listArticle(Integer pageNumber, Integer pageSize) {
		PageVo<Article> pageVo = new PageVo<>();
		Page<Article> page = articleService.listArticle(pageNumber, pageSize);
		pageVo.setData(page.getContent());
		pageVo.setPageCount(page.getTotalPages());
		pageVo.setPageSize(pageSize);
		pageVo.setPageIndex(pageNumber + 1);
		return pageVo;
	}
	
	@ApiOperation(value = "文章接口", notes = "获取文章", code = 200, produces = "application/json")
	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public Object findArticle(@PathVariable("id") Long id) {
		return articleService.findArticleById(id);
	}

	@ApiOperation(value = "文章接口", notes = "获取推荐文章", code = 200, produces = "application/json")
	@RequestMapping(value = "/article/suggestion", method = RequestMethod.GET)
	public Object findSuggestionArticle(int pageSize) {
		return articleService.findSuggestionArticle(pageSize);
	}

	@ApiOperation(value = "文章接口", notes = "获取文章评论", code = 200, produces = "application/json")
	@RequestMapping(value = "/article/{articleId}/comment", method = RequestMethod.GET)
	public Object listComment(@PathVariable("articleId") Long articleId, Integer pageNumber, Integer pageSize) {
		return null;
	}
}