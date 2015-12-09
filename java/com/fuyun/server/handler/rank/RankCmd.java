package com.fuyun.server.handler.rank;

public interface RankCmd {

	int BIGRANK = 1;  //土豪榜
	int SUPERRANK = 2;//捕鱼达人榜
	int TIMERANK = 3;//在线榜
	int LIKEDRANK = 4;//被赞榜
	
	int BIGRANKSELF = 5;//个人排名  土豪榜
	int SUPERRANKSELF = 6;//个人排名  捕鱼达人榜
	int TIMERANKSELF = 7;//个人排名  在线榜
	int LIKEDRANKSELF = 8;//个人排名  被赞榜
	
	int TICKETRANK = 9;//奖券达人榜
	int TICKETRANKSELF = 10;//个人排名 奖券达人榜
	
	int LIKE = 11;//点赞
	
	
}
