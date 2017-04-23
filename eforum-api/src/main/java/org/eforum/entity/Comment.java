package org.eforum.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * 评论
 */
@Entity(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    /**
     * 文章主键
     */
    @Column(name = "article_id")
    private Long articleId;
    /**
     * 用户主键
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     * 评论内容
     */
    @Column
    private String content;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @Temporal(TemporalType.DATE)
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
