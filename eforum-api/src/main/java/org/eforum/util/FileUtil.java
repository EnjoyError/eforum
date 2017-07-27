package org.eforum.util;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;

/**
 * 文件帮助类
 * 
 * @author huxiansheng
 *
 */
public class FileUtil {

	/**
	 * @Description: 将base64编码字符串转换为图片
	 * @Author:
	 * @CreateTime:
	 * @param imgStr
	 *            base64编码字符串
	 * @param path
	 *            图片路径-具体到文件
	 * @return
	 */
	public static boolean generateImage(String imgStr, String path) {
		if (imgStr == null)
			return false;
		try {
			// 解密
			byte[] b = Base64.decodeBase64(imgStr);
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
