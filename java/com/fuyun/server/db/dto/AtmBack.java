package com.fuyun.server.db.dto;

public class AtmBack {

	private int result;
	private int atmType;
	private int atmCount;
	
	
	public AtmBack() {
		super();
	}


	public AtmBack(int atmType, int atmCount) {
		super();
		result = 1;
		this.atmType = atmType;
		this.atmCount = atmCount;
	}


	
	
	public int getResult() {
		return result;
	}


	public void setResult(int result) {
		this.result = result;
	}


	public int getAtmType() {
		return atmType;
	}


	public void setAtmType(int atmType) {
		this.atmType = atmType;
	}


	public int getAtmCount() {
		return atmCount;
	}


	public void setAtmCount(int atmCount) {
		this.atmCount = atmCount;
	}


	@Override
	public String toString() {
		return "AtmBack [atmType=" + atmType + ", atmCount=" + atmCount + "]";
	}
	
	
}
