package org.eforum.exception;

/**
 * 服务异常
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String code;
	
	public ServiceException() {
	}
	
	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable e) {
		super(message, e);
	}
	
	public ServiceException(String message, String code) {
		super(message);
		setCode(code);
	}
	
	public ServiceException(String message, String code, Throwable e) {
		super(message, e);
		setCode(code);
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}