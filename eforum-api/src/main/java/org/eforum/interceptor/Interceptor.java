package org.eforum.interceptor;

/**
 * 拦截器接口，需要实现该接口才可以向InterceptorInvoker注册拦截器
 * 
 * @author huxiansheng
 *
 */
public interface Interceptor {
	/**
	 * 调用实际的拦截方法
	 * 
	 * @param args
	 */
	public void invoke(Object[] args);
}
