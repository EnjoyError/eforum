package org.eforum.service;

import org.eforum.entity.Message;

public interface MessageService {
    /**
     * 发送消息
     * @param message
     * @return
     */
    public Message send(Message message);
}
