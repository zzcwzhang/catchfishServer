package com.fuyun.server.reward;

public interface SignType {

	int FAILED = -1;//签到失败
	int SIGNED = 0;//当天已签过
	int NORMAL = 1;//签到成功无奖励
	int SEQ2 = 2;//连续2天
	int SEQ3 = 3;//连续3天
	int SEQ4 = 4;//连续4天
	int SEQ5 = 5;//连续5天
	int SEQ6 = 6;//连续6天
	int SEQ7 = 7;//连续7天
	int SEQ15 = 15;//签满15天
	int SEQ25 = 25;//签满25天
	
}
