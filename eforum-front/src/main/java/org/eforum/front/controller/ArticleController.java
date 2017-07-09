package org.eforum.front.controller;

import org.eforum.entity.Article;
import org.eforum.entity.Comment;
import org.eforum.front.util.ConvertUtil;
import org.eforum.front.vo.ArticleVo;
import org.eforum.produces.PageVo;
import org.eforum.produces.ResultJson;
import org.eforum.service.ArticleService;
import org.eforum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class ArticleController extends BaseController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	
	@ApiOperation(value = "文章接口", notes = "获取文章列表", code = 200, produces = "application/json")
	@RequestMapping(value = "/article/getArticleList", method = RequestMethod.GET)
	public Object listArticle(Integer pageNumber, Integer pageSize) {
		Page<Article> page = articleService.listArticle(pageNumber, pageSize);
		PageVo<Article> pageVo = new PageVo<>();
		pageVo.setData(page.getContent());
		pageVo.setPageSize(pageSize);
		pageVo.setPageCount(page.getTotalPages());
		pageVo.setPageIndex(pageNumber);
		return new ResultJson(true, pageVo);
	}
	
	@ApiOperation(value = "文章接口", notes = "获取文章", code = 200, produces = "application/json")
	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public Object findArticle(@PathVariable("id") Long id) {
		Article article = articleService.findArticleById(id);
		return new ResultJson(true, article);
	}

	@ApiOperation(value = "文章接口", notes = "获取推荐文章", code = 200, produces = "application/json")
	@RequestMapping(value = "/article/suggestion", method = RequestMethod.GET)
	public Object findSuggestionArticle(int pageSize) {
		return articleService.findSuggestionArticle(pageSize);
	}

	@ApiOperation(value = "文章接口", notes = "获取文章评论", code = 200, produces = "application/json")
	@RequestMapping(value = "/article/{articleId}/comment", method = RequestMethod.GET)
	public Object listComment(@PathVariable("articleId") Long articleId, Integer pageNumber, Integer pageSize) {
		Article article = articleService.findArticleById(articleId);
		Page<Comment> page = commentService.listCommentByArticle(article, pageNumber, pageSize);
		PageVo<Comment> pageVo = new PageVo<>();
		pageVo.setData(page.getContent());
		pageVo.setPageCount(page.getTotalPages());
		pageVo.setPageSize(pageSize);
		pageVo.setPageIndex(pageNumber + 1);
		return pageVo;
	}
	
	@ApiOperation(value = "文章接口", notes = "发布帖子", produces = "application/json")
	@RequestMapping(value = "/article/publish")
	public Object publishArticle(@RequestBody ArticleVo articleVo){
		Article article = ConvertUtil.convertVoToEntity(articleVo, Article.class);
		article = articleService.saveOrUpdate(article);
		return new ResultJson(true, String.valueOf(article.getId()));
	}
}