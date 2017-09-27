package org.eforum.front.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.authz.UnauthenticatedException;
import org.eforum.constant.StatusCode;
import org.eforum.exception.ServiceException;
import org.eforum.front.exception.FrontExceptionCode;
import org.eforum.produces.ResultJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController {
    protected static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler
    @ResponseBody
    public Object exceptionHandler(Exception e) {
        LOG.error(e.getMessage(), e);
        ResultJson result = new ResultJson();
        result.setSuccess(false);
        if (e instanceof ServiceException) {
            result.setMessage(e.getMessage());
            result.setCode(((ServiceException) e).getCode());
        } else if (e instanceof UnauthenticatedException) {
            result.setCode(FrontExceptionCode.UN_LOGIN);
            result.setMessage("您没有此操作权限！");
        } else {
            result.setCode(StatusCode.SC_INTERNAL_SERVER_ERROR);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}