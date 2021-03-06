package org.eforum.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eforum.constant.Constants;
import org.eforum.constant.FileType;
import org.eforum.entity.FileInfo;
import org.eforum.entity.GlobalParam;
import org.eforum.exception.ServiceException;
import org.eforum.repository.CommonDao;
import org.eforum.service.FileService;
import org.eforum.util.CommonUtils;
import org.eforum.util.FileUtil;
import org.eforum.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private CommonDao commonDao;

	@Override
	public List<String> saveImages(MultipartFile[] images) {
		if (null == images || images.length < 1) {
			throw new ServiceException("要保存的图片文件为空");
		}
		List<String> fileNames = new ArrayList<String>();
		for (MultipartFile image : images) {
			String fileName = saveImage(image);
			fileNames.add(fileName);
		}

		return fileNames;
	}

	@Override
	public String getFileSavePath(String key) {
		String hql = "FROM GlobalParam gp WHERE gp.key = :key";
		GlobalParam gp = (GlobalParam) commonDao.findUniqueByHql(hql, "key", key);
		String imagePath = gp.getValue();
		imagePath = imagePath.replace("/", File.separator);
		return imagePath;
	}

	/**
	 * 保存图片文件
	 * 
	 * @param image
	 * @return
	 */
	private String saveImage(MultipartFile image) {
		String originalFileName = image.getOriginalFilename();
		String randomStr = CommonUtils.generateRandomStr();// 使用randomStr作为新文件名
		String extensionName = getFileExtensionName(originalFileName);
		String fileName = randomStr + extensionName;
		String imagePath = getFileSavePath(Constants.IMAGE_DIR);
		File imageDir = new File(imagePath);
		if (!imageDir.exists()) {
			imageDir.mkdirs();
		}
		File imageFile = new File(imageDir, fileName);
		try {
			imageFile.createNewFile();
			image.transferTo(imageFile.getAbsoluteFile());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("写入文件出现异常，请联系管理员！");
		}
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName(fileName);
		fileInfo.setFileType(FileType.IMAGE);
		fileInfo.setFileExtensionName(extensionName);
		fileInfo.setOriginalFileName(originalFileName);
		commonDao.save(fileInfo);
		return fileName;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param originalFileName
	 * @return
	 */
	private String getFileExtensionName(String originalFileName) {
		if (StringUtils.isNullOrEmpty(originalFileName)) {
			throw new ServiceException("文件名不正确");
		}
		if (!originalFileName.contains(".")) {
			return Constants.DEFAULT_FILE_EXTENSION_NAME;
		}
		String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
		if (null == extensionName) {
			throw new ServiceException("文件扩展名不正确");
		}
		return extensionName;
	}

	@Override
	public boolean deleteFileIfExist(String path) {
		File file = new File(path);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	@Override
	public boolean saveBase64ImageFile(String base64Str, String path) {
		return FileUtil.generateImage(base64Str, path);
	}

	@Override
	public File getFile(String fileType, String fileName) {
		String filePath = getFileSavePath(fileType);
		filePath = filePath + File.separator + fileName;
		File file = new File(filePath);
		if (!file.exists()) {
			throw new ServiceException("未找到该文件[" + filePath + "]");
		}
		return file;
	}

	@Override
	public boolean mkDirIfNoExist(String dirType) {
		String dirPath = getFileSavePath(dirType);
		File file = new File(dirPath);
		if (!file.exists()) {
			return file.mkdirs();
		}
		return false;
	}
}
