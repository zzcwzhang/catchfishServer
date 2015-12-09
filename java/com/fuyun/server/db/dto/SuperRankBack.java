package com.fuyun.server.db.dto;


/**
 * 名次(rank) int
 * Vip等级（vip）byte
 * 昵称（nickname）string
 * 鱼分（fishScore）int
 * 操作（toLike）boolean
 * @author Administrator
 *
 */

public class SuperRankBack {

	private int rank;
	private byte vip;
	private int id;
	private String nickName;
	private int fishScore;
	private boolean toLike;
	
	
	
	public SuperRankBack(byte vip, int id, String nickName, int fishScore) {
		super();
		this.vip = vip;
		this.id = id;
		this.nickName = nickName;
		this.fishScore = fishScore;
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



	public int getFishScore() {
		return fishScore;
	}
	public void setFishScore(int fishScore) {
		this.fishScore = fishScore;
	}
	public boolean isToLike() {
		return toLike;
	}
	public void setToLike(boolean toLike) {
		this.toLike = toLike;
	}
	
	
	@Override
	public String toString() {
		return "SuperRankBack [rank=" + rank + ", vip=" + vip + ", id=" + id + ", nickName=" + nickName + ", fishScore="
				+ fishScore + ", toLike=" + toLike + "]";
	}
	
	
	
}
