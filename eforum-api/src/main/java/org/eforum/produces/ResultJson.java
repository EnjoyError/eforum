package org.eforum.produces;

public class ResultJson {
	private boolean success;
	private String message;
	private int code;
	
	public ResultJson() {
	}
	
	public ResultJson(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public ResultJson(boolean success, String message, int code) {
		this.success = success;
		this.message = message;
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}