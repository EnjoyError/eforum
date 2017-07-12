package org.eforum.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 * 
 * @author huxiansheng
 *
 */
public interface FileService {
	/**
	 * 保存图片文件。该方将传递的临时图片文件复制到全局参数定义的目录位置
	 * 
	 * @param file
	 * @return 返回保存文件ID
	 */
	public List<String> saveImages(MultipartFile[] images);
}
