package com.fuyun.server.db.dao;

public interface QuartzDao {

	//重置排行榜
	public boolean resetBigRank();
	public boolean resetSuperRank();
	public boolean resetTimeRank();
	public boolean resetTicketRank();
	public boolean resetLikedRank();
	
	//发放奖励  金币 数量 
	public boolean giveGift(int type,int count);
	
	
}
