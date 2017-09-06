package org.eforum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 文件信息类，用来描述用户上传的文件信息。
 * 
 * @author huxiansheng
 *
 */
@Entity(name = "FileInfo")
@Table(name = "FILE_INFO")
public class FileInfo extends BaseEntity {
	/** 原始文件的名字 */
	@Column(name = "ORIGINAL_FILE_NAME")
	private String originalFileName;
	/** 文件存储名 */
	@Column(name = "FILE_NAME")
	private String fileName;
	/** 文件类型 */
	@Column(name = "FILE_TYPE")
	private String fileType;
	/** 文件扩展名 */
	@Column(name = "FILE_EXTENSION_NAME")
	private String fileExtensionName;

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileExtensionName() {
		return fileExtensionName;
	}

	public void setFileExtensionName(String fileExtensionName) {
		this.fileExtensionName = fileExtensionName;
	}

}
