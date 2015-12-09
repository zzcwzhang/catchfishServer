package com.fuyun.server.db.dto;


public class ErrorBack {

	private int result;
	private int errorCode;
	
	public ErrorBack(int result, int errorCode) {
		super();
		this.result = result;
		this.errorCode = errorCode;
	}
	
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
	@Override
	public String toString() {
		return "ErrorBack [result=" + result + ", errorCode=" + errorCode + "]";
	}
	
	
}
