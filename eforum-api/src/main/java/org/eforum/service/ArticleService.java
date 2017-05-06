package org.eforum.service;

import org.eforum.entity.Article;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
	Page<Article> paginate(int pageNumber, int pageSize);
	
	Article findArticleById(Long id);

	List<Article> findSuggestionArticle(int pageSize);
}