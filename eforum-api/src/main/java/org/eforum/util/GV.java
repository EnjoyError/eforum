package org.eforum.util;

import java.math.BigDecimal;

public class GV {
	/**
	 * 字符串
	 */
	public static String string(Object obj) {
		if (obj instanceof String) {
			return (String)obj;
		}
		return obj == null ? null : obj.toString();
	}
	
	public static String string(Object obj, String defaultValue) {
		return obj == null ? defaultValue : string(obj);
	}
	
	/**
	 * boolean类型
	 */
	public static Boolean z(Object obj) {
		if (obj instanceof Boolean) {
			return (Boolean)obj;
		}
		return obj == null ? null : Boolean.parseBoolean(obj.toString());
	}
	
	public static Boolean z(Object obj, Boolean defaultValue) {
		return null;
	}
	
	/**
	 * byte类型
	 */
	public static Byte b(Object obj) {
		return null;
	}
	
	public static Byte b(Object obj, Byte defaultValue) {
		return null;
	}
	
	/**
	 * short类型
	 */
	public static Short s(Object obj) {
		return null;
	}
	
	public static Short s(Object obj, Short defaultValue) {
		return null;
	}
	
	/**
	 * int类型
	 */
	public static Integer i(Object obj) {
		return null;
	}
	
	public static Integer i(Object obj, Integer defaultValue) {
		return null;
	}
	
	/**
	 * long类型
	 */
	public static Long l(Object obj) {
		return null;
	}
	
	public static Long l(Object obj, Long defaultValue) {
		return null;
	}
	
	/**
	 * float类型
	 */
	public static Float f(Object obj) {
		return null;
	}
	
	public static Float f(Object obj, Float defaultValue) {
		return null;
	}
	
	/**
	 * double类型
	 */
	public static Double d(Object obj) {
		return null;
	}
	
	public static Double d(Object obj, Double defaultValue) {
		return null;
	} 
	
	/**
	 * BigDecimal类型
	 */
	public static BigDecimal bd(Object obj) {
		return null;
	}
	
	public static BigDecimal bd(Object obj, BigDecimal defaultValue) {
		return null;
	}
}