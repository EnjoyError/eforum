package org.eforum.service;

import org.eforum.entity.Article;
import org.eforum.entity.Comment;
import org.springframework.data.domain.Page;

public interface CommentService {
    Page<Comment> listCommentByArticle(Article article, Integer pageNumber, Integer pageSize);
}
