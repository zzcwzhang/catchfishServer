package com.fuyun.server.handler.mail;

public interface MailCmd {

	int LOADMAILS = 1;//加载邮件列表(loadMalis)
	int LOADMAIL = 2;//读取邮件(loadMail)
	int DELETEMAIL = 3;//删除单个(deleteMail)
	int DELETEMAILS = 4;//批量删除(deleteMails)
	int ACQUIREATM = 5;//领取附件奖励（acquireAtm）
	int ACQUIREATMS = 6;//l领取所有为领取附件奖励(acquireAtms)
	
}
