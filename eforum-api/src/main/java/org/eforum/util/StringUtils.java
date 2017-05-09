package org.eforum.util;

/**
 * 字符串帮助类
 * 
 * @author Sackr
 *
 */
public class StringUtils {
	/**
	 * 判断字符串是否为null或空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if (null == str) {
			return true;
		}
		return str.isEmpty();
	}
}
