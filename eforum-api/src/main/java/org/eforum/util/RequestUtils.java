package org.eforum.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
	/**
	 * 判断是否是ajax请求
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
	}
}