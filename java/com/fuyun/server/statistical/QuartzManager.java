package com.fuyun.server.statistical;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class QuartzManager {

	private static QuartzManager instance= null;
	
	private static Logger log = LoggerFactory.getLogger(QuartzManager.class);
	
	private Scheduler sched;
	
	private QuartzManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static QuartzManager getInstance(){
		if(instance == null){
			instance = new QuartzManager();
		}
		
		return instance;
	}
	
	
	 public void run() throws Exception{
		 
		 
		 
		 SchedulerFactory sf = new StdSchedulerFactory();
		 sched = sf.getScheduler();
		 
		 JobDetail job = null;
		 CronTrigger trigger = null;
		 Date ft = null;
		 
		 
		//cronSchedule("0/1 * * * * ?") 每秒调用一次
		//cronSchedule("5 0/1 * * * ?") 每分钟的第5秒触发一次
		//cronSchedule("40 0/1 8-17 * * ?") 每天早上8点 到下午5点 内 每分钟触发一次
		//cronSchedule("0 0/3 17-23 * * ?") 下午5点 到 晚上11点 内 每3分钟 触发一次
		 
		 
	    // job 1 will run every 20 seconds
	    job = newJob(OnlinePlayerLevelJob.class).withIdentity("job1", "group1").build();
	
	    trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/1 * * * * ?"))
	        .build();
	
	    ft = sched.scheduleJob(job, trigger);
	    log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
		             + trigger.getCronExpression());
	    
	    
	    
	    log.info("------- Starting Scheduler ----------------");
	    //
        // All of the jobs have been added to the scheduler, but none of the jobs
        // will run until the scheduler has been started
        sched.start();

        log.info("------- Started Scheduler -----------------");
		    
		    
	 }
	 
	 public void close() throws Exception{
		 
		 
		 if(sched == null){
			 
			 return;
		 }
		log.info("------- Shutting Down ---------------------");

	    sched.shutdown(true);

	    log.info("------- Shutdown Complete -----------------");

	    // display some stats about the schedule that just ran
	    SchedulerMetaData metaData = sched.getMetaData();
	    log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
	 }
	 
	 
	
}
