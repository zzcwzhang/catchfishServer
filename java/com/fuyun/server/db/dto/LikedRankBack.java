package com.fuyun.server.db.dto;


/**
 * 名次(rank) int
 * Vip等级（vip）byte
 * 昵称（nickname）string
 * 获赞次数（liked）int
 * 操作（toLike）boolean
 * @author Administrator
 *
 */

public class LikedRankBack {

	private int rank;
	private byte vip;
	private int id;
	private String nickName;
	private int liked;
	private boolean toLike;
	
	
	public LikedRankBack(byte vip, int id, String nickName, int liked) {
		super();
		this.vip = vip;
		this.id = id;
		this.nickName = nickName;
		this.liked = liked;
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


	public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	public boolean isToLike() {
		return toLike;
	}
	public void setToLike(boolean toLike) {
		this.toLike = toLike;
	}
	
	
	@Override
	public String toString() {
		return "LikedRankBack [rank=" + rank + ", vip=" + vip + ", id=" + id + ", nickName=" + nickName + ", liked=" + liked
				+ ", toLike=" + toLike + "]";
	}
	
	
	
}
