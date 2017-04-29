package org.eforum.front.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eforum.constant.StatusCode;
import org.eforum.exception.ServiceException;
import org.eforum.produces.ResultJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler
	@ResponseBody
	public Object exceptionHandler(Exception e) {
		log.error(e.getMessage(), e);
		ResultJson result = new ResultJson();
		result.setSuccess(false);
		result.setMessage(e.getMessage());
		if (e instanceof ServiceException) {
			result.setCode(((ServiceException)e).getCode());
		} else {
			result.setCode(StatusCode.SC_INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}