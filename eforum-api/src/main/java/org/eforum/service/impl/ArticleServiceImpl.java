package org.eforum.service.impl;

import java.util.List;

import org.eforum.entity.Article;
import org.eforum.exception.ServiceException;
import org.eforum.repository.ArticleRepository;
import org.eforum.service.ArticleService;
import org.eforum.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public Page<Article> listArticle(int pageNumber, int pageSize) {
		return articleRepository.findAll(new PageRequest(pageNumber - 1, pageSize));
	}

	@Override
	public Article findArticleById(Long id) {
		Article article = articleRepository.findOne(id);
		if(null == article){
			throw new ServiceException("未找到该帖子，或者该帖子已被删除！");
		}
		return article;
	}

	@Override
	public List<Article> findSuggestionArticle(int pageSize) {
		PageRequest pageRequest = new PageRequest(0, pageSize);
		Page<Article> page = articleRepository.findAll((root, query, cb) -> {
			query.orderBy(cb.asc(root.get("weight").as(Integer.class)));
			return query.getRestriction();
		}, pageRequest);
		return page.getContent();
	}

	@Override
	public Article saveOrUpdate(Article article) {
		Article willSaveArticle = null;
		if (!article.isNew()) {
			willSaveArticle = articleRepository.findOne(article.getId());
			if (null == willSaveArticle) {
				throw new ServiceException("未找到id为【" + article.getId() + "】的帖子");
			}
			EntityUtil.copyPropertiesOfEntity(article, willSaveArticle);
		} else {
			willSaveArticle = article;
		}
		articleRepository.save(willSaveArticle);
		return willSaveArticle;
	}
}