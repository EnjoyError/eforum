package org.eforum.service.impl;

import org.eforum.entity.Message;
import org.eforum.service.MessageService;
import org.springframework.stereotype.Service;

/**
 * 消息服务
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl implements MessageService{
    public Message send(Message message){
        dao.save(message);
        return message;
    }
}
