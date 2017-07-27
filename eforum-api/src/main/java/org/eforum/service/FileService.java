package org.eforum.service;

import java.io.File;
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

	/**
	 * 获取文件
	 * 
	 * @param fileType
	 *            文件存储类型
	 * @param fileName
	 * @return
	 */
	public File getFile(String fileType, String fileName);

	/**
	 * 获取文件的存储路径
	 * 
	 * @param key
	 * @return
	 */
	public String getFileSavePath(String key);

	/**
	 * 如果文件存在则删除
	 * 
	 * @param path
	 *            绝对路径
	 */
	public boolean deleteFileIfExist(String path);

	/**
	 * 保存base64图片文件
	 * 
	 * @param base64Str
	 * @param path
	 * @return
	 */
	public boolean saveBase64ImageFile(String base64Str, String path);

	/**
	 * 创建文件夹
	 * 
	 * @param dirPath
	 * @return
	 */
	public boolean mkDirIfNoExist(String dirType);
}
