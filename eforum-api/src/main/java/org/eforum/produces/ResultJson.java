package org.eforum.produces;

import org.eforum.constant.StatusCode;

public class ResultJson {
	private boolean success;
	private Object message;
	private int code;
	
	public ResultJson() {
	}
	
	public ResultJson(boolean success, Object message) {
		this.success = success;
		this.message = message;
		this.code = StatusCode.SC_OK;
	}
	
	public ResultJson(boolean success, Object message, int code) {
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
	
	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}