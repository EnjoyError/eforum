package org.eforum.front.interceptor;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.annotation.Aspect;
import org.eforum.constant.Constants;
import org.eforum.entity.BaseEntity;
import org.eforum.entity.User;
import org.eforum.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义一个拦截器，用来拦截调用persist或merge方法的。该拦截器会在保存entity之前更新updateInfo
 *
 * @author huxiansheng
 *
 */
@Aspect
@Component
public class DaoInterceptor implements Interceptor {
	private final Logger logger = LoggerFactory.getLogger(DaoInterceptor.class);

	@Override
	public void invoke(Object[] args) {
		Object obj = args[0];
		BaseEntity entity = null;
		if (!(obj instanceof BaseEntity)) {
			logger.error("切面方法refreshUpdateInfo执行未能获取所需参数。期待参数：BaseEntity。实际得到的参数：" + obj.getClass());
			return;
		}
		entity = (BaseEntity) obj;
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(Constants.CURRENT_USER_IN_SESSION);
		if (entity.isNew()) {
			entity.setCreateTime(new Date());
			entity.setCreateUser(user);
		}
		entity.setLastUpdateTime(new Date());
		entity.setLastUpdateUser(user);
	}
}
