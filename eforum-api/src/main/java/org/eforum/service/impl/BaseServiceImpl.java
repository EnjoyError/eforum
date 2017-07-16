package org.eforum.service.impl;

import org.eforum.repository.CommonDao;
import org.eforum.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl implements BaseService {
	@Autowired
	protected CommonDao dao;

	public void setDao(CommonDao dao) {
		this.dao = dao;
	}

}
