package com.fuyun.server.statistical.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 * 
 * 1，获得排行榜数据
 * 2，记录前三名 发放奖励
 * 4，排行榜清零
 * 
 *<土豪榜>每天8点更新，并结算发放排行奖励，直接发送到玩家邮箱
 *<土豪榜>排行榜为玩家每日充值积分总和，充值一元获得一积分
 *<土豪榜>排行榜中，每位玩家可对5名玩家“点赞”，每次可获得200金币奖励
 *<土豪榜>排行榜奖励：第一名：88万金币；第二名：50万金币；第三名：20万金币；第4-10名：5万金币；第11-20名：2万金币
 * @author Administrator
 *
 */
public class BigRankJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		//排行榜 
		
	}

}
