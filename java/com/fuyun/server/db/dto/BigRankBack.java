package com.fuyun.server.db.dto;


import java.math.BigInteger;

/**
 * 名次(rank) int
 * Vip等级（vip）int
 * 昵称（nickname）string
 * 充值积分（chargeScore）int
 * 操作（toLike）boolean
 * @author Administrator
 *
 */
public class BigRankBack {

	private int rank;
	private byte vip;
	private int id;
	private String nickName;
	private int chargeScore;
	private boolean toLike;
	
	
	
	public BigRankBack() {
		super();
	}




	public BigRankBack(byte vip, int id, String nickName, int chargeScore) {
		super();
		this.vip = vip;
		this.id = id;
		this.nickName = nickName;
		this.chargeScore = chargeScore;
		this.toLike = false;
		this.rank = -1;
	}




	


	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(byte vip) {
		this.vip = vip;
	}
	
	public String getNickName() {
		return nickName;
	}




	public void setNickName(String nickName) {
		this.nickName = nickName;
	}




	public int getChargeScore() {
		return chargeScore;
	}
	public void setChargeScore(int chargeScore) {
		this.chargeScore = chargeScore;
	}
	public boolean isToLike() {
		return toLike;
	}
	public void setToLike(boolean toLike) {
		this.toLike = toLike;
	}




	@Override
	public String toString() {
		return "BigRankBack [rank=" + rank + ", vip=" + vip + ", id=" + id + ", nickName=" + nickName + ", chargeScore="
				+ chargeScore + ", toLike=" + toLike + "]";
	}
	
	
	
	
	
}
