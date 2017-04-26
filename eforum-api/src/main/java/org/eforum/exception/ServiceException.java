package org.eforum.exception;

/**
 * 服务异常
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int code;
	
	public ServiceException() {
	}
	
	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable e) {
		super(message, e);
	}
	
	public ServiceException(String message, int code) {
		super(message);
		this.code = code;
	}
	
	public ServiceException(String message, int code, Throwable e) {
		super(message, e);
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
}