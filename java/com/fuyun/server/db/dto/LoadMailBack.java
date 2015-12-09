package com.fuyun.server.db.dto;


import java.util.Date;

public class LoadMailBack {

	private int result;
	private String sender;
	private Date sendTime;
	private String title;
	private String content;
	private int atmType;
	private int atmCount;
	private int isReceivced;
	
	public LoadMailBack() {
		super();
	}

	

	public LoadMailBack(String sender, Date sendTime, String title, String content) {
		super();
		result = 1;
		this.sender = sender;
		this.sendTime = sendTime;
		this.title = title;
		this.content = content;
	}



	public LoadMailBack(String sender, Date sendTime, String title, String content, int atmType, int atmCount) {
		super();
		this.sender = sender;
		this.sendTime = sendTime;
		this.title = title;
		this.content = content;
		this.atmType = atmType;
		this.atmCount = atmCount;
	}



	public int getResult() {
		return result;
	}



	public void setResult(int result) {
		this.result = result;
	}



	public int getIsReceivced() {
		return isReceivced;
	}



	public void setIsReceivced(int isReceivced) {
		this.isReceivced = isReceivced;
	}



	public String getSender() {
		return sender;
	}



	public void setSender(String sender) {
		this.sender = sender;
	}



	public Date getSendTime() {
		return sendTime;
	}



	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
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
		return "LoadMailBack [sender=" + sender + ", sendTime=" + sendTime + ", title=" + title + ", content=" + content
				+ ", atmType=" + atmType + ", atmCount=" + atmCount + "]";
	}
	
	
	
	
}
