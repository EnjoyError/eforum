package org.eforum.constant;

/**
 * 该类用来定义一些常量
 * 
 * @author huxiansheng
 *
 */
public interface Constants {
	/** 图片存储路径key,用该key来查询数据库存储的值 */
	public static final String IMAGE_DIR = "IMAGE_DIR";
	/** 头像存储路径 */
	public static final String HEAD_PORTRAIT_DIR = "HEAD_PORTRAIT_DIR";
	/** 图片请求路径 */
	public static final String IMAGE_REQUEST_PATH = "article/image/";
	/** 默认文件扩展名 */
	public static final String DEFAULT_FILE_EXTENSION_NAME = "";
	/** 当前登入用户 */
	public static final String CURRENT_USER_IN_SESSION = "currentUser";
	/** 匿名头像 */
	public static final String ANONYMOUS_HEAD_PORTRAIT_FILE_NAME = "anonymous.png";
	/** 禁言角色名 */
	public static final String USER_IS_JY = "USER_IS_JY";
}
