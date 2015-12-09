package com.fuyun.server.statistical;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class OnlinePlayerLevelJob implements Job{

	private static Logger _log = LoggerFactory.getLogger(OnlinePlayerLevelJob.class);

	private static int count = 0;
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//TODO 在线人数统计
		
		JobKey jobKey = context.getJobDetail().getKey();
	    _log.info("在线人数统计: " + jobKey + " executing at " + new Date()+ "运行了多少"+ ++count +"次");
	    
	}

}
