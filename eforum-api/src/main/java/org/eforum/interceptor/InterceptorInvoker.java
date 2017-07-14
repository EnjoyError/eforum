package org.eforum.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.eforum.exception.ServiceException;
import org.eforum.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 拦截器调用者，所有实现了Interceptor接口的拦截器都应该注册到该调用者里，由调用者来负责调用实际的拦截器方法
 *
 * @author huxiansheng
 *
 */
@Aspect
@Component
public class InterceptorInvoker {
	private final Logger logger = LoggerFactory.getLogger(InterceptorInvoker.class);
	private List<InterceptorEntry> interceptors = new ArrayList<InterceptorEntry>();

	/**
	 * 注册拦截器
	 * 
	 * @param interceptor
	 */
	public void registerInterceptor(Interceptor interceptor, String interceptorType) {
		if (StringUtils.isNullOrEmpty(interceptorType)) {
			throw new ServiceException("拦截器类型不能为空！");
		}
		if (null == interceptor) {
			throw new ServiceException("拦截器不能为空！");
		}
		InterceptorInvoker.InterceptorEntry entry = new InterceptorEntry();
		entry.setInterceptor(interceptor);
		entry.setInterceptorType(interceptorType);
		interceptors.add(entry);
	}

	/**
	 * 定义切入点
	 */
	// @Pointcut("execution(* javax.persistence.EntityManager+.persist(Object))
	// or execution(* javax.persistence.EntityManager+.merge(Object))")

	@Pointcut("execution(* org.springframework.data.repository.CrudRepository+.save(..))")
	public void pointcut() {
	}

	/**
	 * 调用实际拦截方法
	 * 
	 * @param entity
	 */
	@Before("pointcut()")
	public void invoke(JoinPoint joinPoint) {
		for (InterceptorEntry ie : interceptors) {
			try {
				String interceptorType = ie.getInterceptorType();
				if (InterceptorType.ENTITY_MANAGER_PERSIST_AND_MERGE.equals(interceptorType)) {
					ie.getInterceptor().invoke(joinPoint.getArgs());
				}
			} catch (Exception e) {
				logger.error("执行拦截器出现异常,拦截器类型[" + ie.getInterceptorType() + "]", e);
			}
		}

	}

	/**
	 * 拦截器的封装类
	 * 
	 * @author huxiansheng
	 *
	 */
	private class InterceptorEntry {
		/** 拦截器类型 {@link InterceptorType} */
		private String interceptorType;
		/** 拦截器 */
		private Interceptor interceptor;

		public String getInterceptorType() {
			return interceptorType;
		}

		public void setInterceptorType(String interceptorType) {
			this.interceptorType = interceptorType;
		}

		public Interceptor getInterceptor() {
			return interceptor;
		}

		public void setInterceptor(Interceptor interceptor) {
			this.interceptor = interceptor;
		}
	}
}
