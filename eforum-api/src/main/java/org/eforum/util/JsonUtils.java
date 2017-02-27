package org.eforum.util;

import com.alibaba.fastjson.JSON;

/**
 * json帮助类
 */
public class JsonUtils {
	/**
	 * 将对象转换成json字符串
	 */
	public static String toJson(Object obj) {
		return JSON.toJSONString(obj);
	}
}