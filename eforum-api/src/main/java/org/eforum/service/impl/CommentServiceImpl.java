package org.eforum.service.impl;

import org.eforum.entity.Article;
import org.eforum.entity.Comment;
import org.eforum.repository.CommentRepository;
import org.eforum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<Comment> listCommentByArticle(Article article, Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
        Page<Comment> page = commentRepository.findAll((root, query, cb) -> {
            query.where(cb.equal(root.get("article").as(Article.class), article));
            query.orderBy(cb.desc(root.get("createTime").as(Date.class)));
            return query.getRestriction();
        }, pageRequest);
        return page;
    }
}
