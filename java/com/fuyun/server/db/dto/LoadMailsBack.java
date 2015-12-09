package com.fuyun.server.db.dto;


import java.util.Date;

public class LoadMailsBack {
	
	private int mailId;
	private String sender;
	private Date sendTime;
	private int isRead;
	
	private String title;
	
	
	
	public LoadMailsBack() {
		super();
	}



	public LoadMailsBack(String sender, Date sendTime, int isRead, String title) {
		super();
		this.sender = sender;
		this.sendTime = sendTime;
		this.isRead = isRead;
		this.title = title;
	}



	public int getMailId() {
		return mailId;
	}



	public void setMailId(int mailId) {
		this.mailId = mailId;
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



	public int getIsRead() {
		return isRead;
	}



	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}


	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	@Override
	public String toString() {
		return "LoadMailsBack [sender=" + sender + ", sendTime=" + sendTime + ", isRead=" + isRead + ", isReceivced="
				 + ", title=" + title + "]";
	}
	
	
	
	
}
