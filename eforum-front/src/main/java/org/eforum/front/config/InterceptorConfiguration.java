package org.eforum.front.config;

import org.eforum.front.interceptor.DaoInterceptor;
import org.eforum.interceptor.Interceptor;
import org.eforum.interceptor.InterceptorInvoker;
import org.eforum.interceptor.InterceptorType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册拦截器
 * 
 * @author huxiansheng
 *
 */
@Configuration
public class InterceptorConfiguration {
	@Bean
	public Interceptor registerInterceptor(InterceptorInvoker interceptorInveker) {
		Interceptor interceptor = new DaoInterceptor();
		interceptorInveker.registerInterceptor(interceptor, InterceptorType.ENTITY_MANAGER_PERSIST_AND_MERGE);
		return interceptor;
	}
}
