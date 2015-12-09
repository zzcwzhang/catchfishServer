package com.fuyun.server.db.dto;


/**
 * 名次(rank) int
 * Vip等级（vip）int
 * 昵称（nickname）string
 * 在线时长（onlineTimes）long
 * 操作（toLike）boolean
 * @author Administrator
 *
 */

public class TimeRankBack {

	private int rank;
	private byte vip;
	private int id;
	private String nickName;
	private int onlineTimes;
	private boolean toLike;
	
	
	
	public TimeRankBack(byte vip, int id, String nickName, int onlineTimes) {
		super();
		this.vip = vip;
		this.id = id;
		this.nickName = nickName;
		this.onlineTimes = onlineTimes;
	}
	
	
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public byte getVip() {
		return vip;
	}
	public void setVip(byte vip) {
		this.vip = vip;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNickName() {
		return nickName;
	}



	public void setNickName(String nickName) {
		this.nickName = nickName;
	}



	public int getOnlineTimes() {
		return onlineTimes;
	}
	public void setOnlineTimes(int onlineTimes) {
		this.onlineTimes = onlineTimes;
	}
	public boolean isToLike() {
		return toLike;
	}
	public void setToLike(boolean toLike) {
		this.toLike = toLike;
	}
	
	
	@Override
	public String toString() {
		return "TimeRankBack [rank=" + rank + ", vip=" + vip + ", id=" + id + ", nickName=" + nickName + ", onlineTimes="
				+ onlineTimes + ", toLike=" + toLike + "]";
	}
	
	
}
