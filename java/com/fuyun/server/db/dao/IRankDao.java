package com.fuyun.server.db.dao;

import java.util.List;

import com.fuyun.server.db.dto.BigRankBack;
import com.fuyun.server.db.dto.LikedRankBack;
import com.fuyun.server.db.dto.SuperRankBack;
import com.fuyun.server.db.dto.TicketRankBack;
import com.fuyun.server.db.dto.TimeRankBack;

public interface IRankDao {

	//土豪排行榜
	public List<BigRankBack> getBigRank(int id, int count);
	public int getBigRankSelf(int id);
	
	//捕鱼达人榜
	public List<SuperRankBack> getSuperRank(int id, int count);
	public int getSuperRankSelf(int id);
	
	//在线时长榜
	public List<TimeRankBack> getTimeRank(int id, int count);
	public int getTimeRankSelf(int id);
	
	//每日之星
	public List<LikedRankBack> getLikedRank(int id, int count);
	public int getLikedRankSelf(int id);
	
	//奖券达人
	public List<TicketRankBack> getTicketRank(int id,int count);
	public int getTicketRankSelf(int id);
	
	//排行榜 点赞
	public boolean giveLike(int id,int likedId,int type);
	
}
