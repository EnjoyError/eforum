package org.eforum.service.impl;

import org.eforum.entity.Article;
import org.eforum.repository.ArticleRepository;
import org.eforum.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public Page<Article> paginate(int pageNumber, int pageSize) {
		return articleRepository.findAll(new PageRequest(pageNumber, pageSize));
	}

	@Override
	public Article findArticleById(Long id) {
		return articleRepository.findOne(id);
	}
}