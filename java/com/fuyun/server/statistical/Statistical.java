/**
 * 
 */
package com.fuyun.server.statistical;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 统计
 * 
 * 1.每5分钟在线人数
 * 2.
 * @author lushouzhi
 *
 */
public class Statistical {
	
	public  class OnlinePlayerNum implements Job{
		
		
		    
		public void execute(JobExecutionContext context) throws JobExecutionException {
			//TODO 在线人数统计
		        
		        
		}
	}
	
	public class OnlinePlayerLevel implements Job{

		public void execute(JobExecutionContext context) throws JobExecutionException {
			// TODO 在线玩家等级统计
			
		}
	}
}
