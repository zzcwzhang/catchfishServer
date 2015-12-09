/**
 * 
 */
package com.fuyun.server.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fuyun.server.db.dao.IMailDao;
import com.fuyun.server.db.dao.IRankDao;
import com.fuyun.server.db.dao.IUserDao;
import com.fuyun.server.db.dao.IUserModuleDao;
import com.fuyun.server.db.dao.impl.MailDao;
import com.fuyun.server.db.dao.impl.RankDao;
import com.fuyun.server.db.dao.impl.UserDao;
import com.fuyun.server.db.dao.impl.UserModuleDao;
import com.fuyun.server.handler.mail.MailHandler;
import com.fuyun.server.handler.rank.RankHandler;
import com.fuyun.server.handler.user.UserHandler;


/**
 * @author lushouzhi
 *
 */
public class DBManager {
	private static DBManager dbManager = null;
	
	private ApplicationContext ctx = null;
    private  IUserDao userDao = null;
    private  IUserModuleDao userModuleDao = null;
    private  IRankDao rankDao = null;
    private  IMailDao mailDao = null;
    
    
	public static DBManager getInstance() {
		if (dbManager == null) {
			dbManager = new DBManager();
			dbManager.init();
			dbManager.initHandler();
		}
		return dbManager;
	}
	public void init() {
		if(ctx == null){
			String[] cfigPath = { "applicationContext.xml" };
			ctx = new ClassPathXmlApplicationContext(cfigPath);
			initDao();
		}
		  
	}
	
	private void initDao(){
		
		userDao = ctx.getBean("userDao",UserDao.class);
		userModuleDao = ctx.getBean("userModuleDao",UserModuleDao.class);
		rankDao = ctx.getBean("rankDao",RankDao.class);
		mailDao = ctx.getBean("mailDao",MailDao.class);
	}
	
	
	private void initHandler(){
		
		//userHandler
		UserHandler userhandler = new UserHandler();
		userhandler.initHandler();
		
		RankHandler rankhandler = new RankHandler();
		rankhandler.initHandler();
		
		MailHandler mailHandler = new MailHandler();
		mailHandler.initHandler();
	}
	
	public IUserDao getUserDAO() {
		return  userDao;
	}
	
	public IUserModuleDao getUserModuleDAO() {
		return  userModuleDao;
	}
	
	public IRankDao getRankDao() {
		return rankDao;
	}
	public IMailDao getMailDao() {
		return mailDao;
	}
	
	
}
