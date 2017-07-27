package org.eforum.util;

import java.util.Random;

/**
 * 公共的帮助类，建议将无法划分模块功能又单一的操作放在该类里
 * 
 * @author huxiansheng
 *
 */
public class CommonUtils {
	/**
	 * 随机生成字符串,该方法将系统当前时间和一个随机数拼接成一个字符串，理论上不会产生重复的字符串。
	 * 
	 * @return
	 */
	public static synchronized String generateRandomStr() {
		long currentTime = System.currentTimeMillis();
		Random random = new Random();
		int randomInt = random.nextInt();
		return currentTime + "" + randomInt;
	}
}
