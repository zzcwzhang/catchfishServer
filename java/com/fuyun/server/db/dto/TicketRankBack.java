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
public class TicketRankBack {

	private int rank;
	private byte vip;
	private int id;
	private String nickName;
	private int ticket;
	private boolean toLike;
	
	
	
	public TicketRankBack() {
		super();
	}



	public TicketRankBack(byte vip, int id, String nickName, int ticket) {
		super();
		
		this.vip = vip;
		this.id = id;
		this.nickName = nickName;
		this.ticket = ticket;
		this.toLike = false;
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



	public int getTicket() {
		return ticket;
	}



	public void setTicket(int ticket) {
		this.ticket = ticket;
	}



	public boolean isToLike() {
		return toLike;
	}



	public void setToLike(boolean toLike) {
		this.toLike = toLike;
	}



	@Override
	public String toString() {
		return "TicketRankBack [rank=" + rank + ", vip=" + vip + ", id=" + id + ", nickName=" + nickName + ", ticket="
				+ ticket + ", toLike=" + toLike + "]";
	}

	
}
