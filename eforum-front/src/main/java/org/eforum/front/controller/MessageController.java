package org.eforum.front.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.eforum.entity.Article;
import org.eforum.entity.Message;
import org.eforum.entity.User;
import org.eforum.exception.ServiceException;
import org.eforum.front.resolvers.AutoLoad;
import org.eforum.front.security.CurrentThreadContext;
import org.eforum.produces.PageVo;
import org.eforum.produces.ResultJson;
import org.eforum.service.ArticleService;
import org.eforum.service.MessageService;
import org.eforum.service.ReplyService;
import org.eforum.util.ConvertUtil;
import org.eforum.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 消息控制器
 */
@RestController
@RequestMapping(value = "/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;
    @RequestMapping(value = "/send")
    @Transactional
    @RequiresAuthentication
    public Object send(@AutoLoad Message message) {
        User fromUser = CurrentThreadContext.getCurrentUser();
        message.setFromUser(fromUser);
        messageService.send(message);
        return new ResultJson(true, "发送成功");
    }

}