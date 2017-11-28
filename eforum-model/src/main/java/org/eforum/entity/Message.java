package org.eforum.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 消息
 */
@Entity(name = "Message")
@Table(name = "MESSAGE")
public class Message extends BaseEntity{
    /** 消息类型*/
    private String type = MessageType.CHART;
    /** 发送者*/
    @JoinColumn(name = "FROM_USER_ID")
    @ManyToOne(targetEntity = User.class)
    private User fromUser;
    /** 接受者*/
    @JoinColumn(name = "TO_USER_ID")
    @ManyToOne(targetEntity = User.class)
    private User toUser;
    /** 消息内容*/
    private String content;
    /** 是否已查阅*/
    private Boolean beChecked = Boolean.FALSE;

    public Boolean getBeChecked() {
        return beChecked;
    }

    public void setBeChecked(Boolean beChecked) {
        this.beChecked = beChecked;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
