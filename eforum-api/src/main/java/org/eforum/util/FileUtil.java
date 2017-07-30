package org.eforum.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

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

	public static void writeFileToResponse(File file, HttpServletResponse response) {
		try {
			InputStream is = new FileInputStream(file);
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			byte[] data = new byte[1024];
			int length;
			while ((length = is.read(data)) != -1) {
				os.write(data, 0, length);
			}
			is.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
