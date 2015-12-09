package com.fuyun.server.handler.mail;

import java.util.HashMap;

import org.apache.mina.core.session.IoSession;

import com.fuyun.server.db.DBManager;
import com.fuyun.server.db.dao.IMailDao;
import com.fuyun.server.db.dto.ErrorBack;
import com.fuyun.server.module.ErrorCode;
import com.fuyun.server.module.Module;
import com.fuyun.server.socket.BaseHandler;
import com.fuyun.server.socket.Invoker;
import com.fuyun.server.socket.msg.Request;

import net.sf.json.JSONObject;

public class MailHandler  extends BaseHandler{

	IMailDao mailDao = DBManager.getInstance().getMailDao();
	
	@Override
	public int getModule() {
		// TODO Auto-generated method stub
		return Module.MAIL;
	}

	@Override
	public void inititialize() {
		// TODO Auto-generated method stub
		putInvoker(MailCmd.LOADMAILS, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				loadMails(session,req);
			}
		});
		
		
		putInvoker(MailCmd.LOADMAIL, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				loadMail(session,req);
			}
		});
		
		putInvoker(MailCmd.DELETEMAIL, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				deleteMail(session,req);
			}
		});
		
		putInvoker(MailCmd.DELETEMAILS, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				deleteMails(session,req);
			}
		});
		
		putInvoker(MailCmd.ACQUIREATM, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				acquireAtm(session,req);
			}
		});
		
		putInvoker(MailCmd.ACQUIREATMS, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				acquireAtms(session,req);
			}
		});
	}
	
	protected void loadMails(IoSession session,Request req){
		 System.out.println("加载邮件列表 ");
		 System.out.println(req.toString());
		 JSONObject json = req.getBody();

		 int id =json.getInt("id");
		 boolean result = mailDao.getMails(session,req, id);
		 if(!result){
			 System.out.println("加载邮件列表  失败 userId = "+id);
			 ErrorBack error  = new ErrorBack(0, ErrorCode.LOADMAIL);
			 req.setBody(JSONObject.fromObject(error));
			 session.write(req);
		 }
		
	} 
	
	
	protected void loadMail(IoSession session,Request req){
		 System.out.println("读取邮件 ");
		 
		 System.out.println(req.toString());
		 JSONObject json = req.getBody();

		 int id =json.getInt("id");
		 int mid =json.getInt("mailId");
		 boolean result = mailDao.getMail(session,req, id,mid);
		 if(!result){
			 System.out.println("读取邮件  失败   userId = "+id+" mailId = "+mid);
			 ErrorBack error  = new ErrorBack(0, ErrorCode.LOADMAIL);
			 req.setBody(JSONObject.fromObject(error));
			 session.write(req);
		 }
		
	} 
	
	protected void deleteMail(IoSession session,Request req){
		 System.out.println("删除单个邮件 ");
		 
		 System.out.println(req.toString());
		 JSONObject json = req.getBody();

		 int id =json.getInt("id");
		 int mid =json.getInt("mailId");
		 boolean result = mailDao.deleteMail(session,req, id,mid);
		 if(!result){
			 System.out.println("删除单个邮件  失败   userId = "+id+" mailId = "+mid);
			 ErrorBack error  = new ErrorBack(0, ErrorCode.DELETEMAIL);
			 req.setBody(JSONObject.fromObject(error));
			 session.write(req);
		 }else{
			  HashMap<String, Comparable> map = new HashMap<String, Comparable>();
			  map.put("result", 1);
			  req.setBody(JSONObject.fromObject(map));
			  session.write(req);
		 }
		
	} 
	
	protected void deleteMails(IoSession session,Request req){
		 System.out.println("批量删除邮件 ");
		 
		 System.out.println(req.toString());
		 JSONObject json = req.getBody();

		 int id =json.getInt("id");
		 boolean result = mailDao.deleteMailAll(session,req, id);
		 if(!result){
			 System.out.println("批量删除邮件  失败 userId = "+id);
			 ErrorBack error  = new ErrorBack(0, ErrorCode.DELETEMAIL);
			 req.setBody(JSONObject.fromObject(error));
			 session.write(req);
		 }else{
			  HashMap<String, Comparable> map = new HashMap<String, Comparable>();
			  map.put("result", 1);
			  req.setBody(JSONObject.fromObject(map));
			  session.write(req);
		 }
		
	} 
	
	protected void acquireAtm(IoSession session,Request req){
		 System.out.println("领取附件奖励 ");
		 
		 System.out.println(req.toString());
		 JSONObject json = req.getBody();

		 int id =json.getInt("id");
		 int mid =json.getInt("mailId");
		 boolean result = mailDao.acquireAtmSign(session,req, id,mid);
		 if(!result){
			 System.out.println("领取单个附件奖励     userId = "+id+" mailId = "+mid);
			 ErrorBack error  = new ErrorBack(0, ErrorCode.HAVEATM);
			 req.setBody(JSONObject.fromObject(error));
			 session.write(req);
			 
		 }
		
	} 
	
	protected void acquireAtms(IoSession session,Request req){
		 System.out.println("领取所有为领取附件奖励 ");
		 
		 System.out.println(req.toString());
		 JSONObject json = req.getBody();

		 int id =json.getInt("id");
		 boolean result = mailDao.acquireAtmAllSign(session,req, id);
		 if(!result){
			 System.out.println("领取所有为领取附件奖励  失败 userId = "+id);
			 ErrorBack error  = new ErrorBack(0, ErrorCode.ATMALL);
			 req.setBody(JSONObject.fromObject(error));
			 session.write(req);
		 }
		
	} 
	
	
	

}
