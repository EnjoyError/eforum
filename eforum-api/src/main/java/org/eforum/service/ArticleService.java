package org.eforum.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.eforum.entity.Article;
import org.eforum.produces.PageVo;
import org.eforum.vo.ArticleVo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface ArticleService {
	@Transactional
	PageVo<ArticleVo> listArticle(int pageNumber, int pageSize);

	Article findArticleById(Long id);

	List<Article> findSuggestionArticle(int pageSize);

	/**
	 * 保存或者更新帖子
	 * 
	 * @param article
	 * @return
	 */
	@Transactional
	Article saveOrUpdate(Article article);

	/**
	 * 保存帖子的图片。只是预先保存，如果是第一次发帖，则还没有帖子ID，但是图片需要先行保存。
	 * 
	 * @param images
	 * @return
	 */
	List<String> saveImagesOfArticle(MultipartFile[] images);

	/**
	 * 下载图片
	 * 
	 * @param imageName
	 * @param response
	 */
	void downloadImageOfArticle(String imageName, HttpServletResponse response);

	/**
	 * 刷新修改时间
	 * 
	 * @param articleId
	 * @param lastUpdateTime
	 */
	void refreshLastUpdateTimeForAll(Long articleId, Date lastUpdateTime);

}