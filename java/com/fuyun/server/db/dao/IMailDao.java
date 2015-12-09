package com.fuyun.server.db.dao;

import java.util.List;
import java.util.Set;

import org.apache.mina.core.session.IoSession;

import com.fuyun.server.db.model.Mail;
import com.fuyun.server.socket.msg.Request;

/**
 * 1,点击邮件icon 获得邮件列表
 * 2，点击单个邮件 获得邮件内容
 * 3，点击邮件内领取 获得附件内奖励
 * 4，删除单个邮件
 * 5，删除多个邮件
 * 6，领取所有未领取的附件内容
 * 
 * 
 * 7，显示未读
 * 8，显示已读
 * 9，显示未领取
 * 10，显示已领取
 * 
 * 11,领取一个标记
 * 12，领取全部标记
 * @author Administrator
 *
 */
public interface IMailDao {

	public boolean  getMails(IoSession ioSession,Request req,int userId);
	public boolean getMail(IoSession ioSession,Request req,int userId,int mId);
	

	public boolean deleteMail(IoSession ioSession,Request req,int userId,int mid);
	public boolean deleteMails(IoSession ioSession,Request req,int userId,Set<Integer> mids); 
	public boolean deleteMailAll(IoSession ioSession,Request req,int userId);
	
	public boolean acquireAtm(IoSession ioSession,Request req,int userId,int mId);
	public boolean acquireAllAtm(IoSession ioSession,Request req,int userId);
	
	public boolean acquireAtmSign(IoSession ioSession,Request req,int userId,int mId);
	public boolean acquireAtmAllSign(IoSession ioSession,Request req,int userId);
	
	
	
	
	
}
