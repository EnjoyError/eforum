package org.eforum.service.impl;

import org.eforum.entity.Article;
import org.eforum.repository.ArticleRepository;
import org.eforum.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public Page<Article> listArticle(int pageNumber, int pageSize) {
		return articleRepository.findAll(new PageRequest(pageNumber, pageSize));
	}

	@Override
	public Article findArticleById(Long id) {
		return articleRepository.findOne(id);
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
}