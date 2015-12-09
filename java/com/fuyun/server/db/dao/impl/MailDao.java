package com.fuyun.server.db.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.mina.core.session.IoSession;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.fuyun.server.db.dao.IMailDao;
import com.fuyun.server.db.dto.AtmBack;
import com.fuyun.server.db.dto.LoadMailBack;
import com.fuyun.server.db.dto.LoadMailsBack;
import com.fuyun.server.db.model.Mail;
import com.fuyun.server.db.util.DateJsonValueProcessor;
import com.fuyun.server.db.util.HibernateSessionFactory;
import com.fuyun.server.handler.mail.MailCmd;
import com.fuyun.server.socket.msg.Request;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


@Component("mailDao")
public class MailDao implements IMailDao{

	@Override
	public boolean getMails(IoSession ioSession,Request req,int userId ) {

		
		boolean result = false;
		
		Session session = null;
		
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      List<Mail> mails = session.createQuery("from Mail u where receiverId=:receiverId")
					.setCacheable(true)
					.setParameter("receiverId", userId)
					.list();
	    
	      if(mails.size() > 0){
	    	  List<LoadMailsBack> backs = new ArrayList<LoadMailsBack>();
		      for (Mail mail : mails) {
		    	  LoadMailsBack back = new LoadMailsBack();
		    	  back.setMailId(mail.getId());
		    	  back.setSender("系统");
		    	  back.setSendTime(mail.getCreateTime());
		    	  back.setIsRead(mail.getRead());
		    	  back.setTitle(mail.getTitle());
		    	  backs.add(back);
			  }
		     // session.getTransaction().commit();
		      HashMap<String, Comparable> map = new HashMap<String, Comparable>();
			  map.put("result", 1);
			  JsonConfig config = new JsonConfig(); 
			  config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		      JSONArray jsonArray = JSONArray.fromObject(backs,config);
			  map.put("mails", jsonArray);
			  
			  req.setBody(JSONObject.fromObject(map));
			  ioSession.write(req);
			  
		      result = true;
	      }
	     
		 
	      
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	      result = false;
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
	    return result;
	}

	@Override
	public boolean getMail(IoSession ioSession,Request req,int userId,int mId) {
		
		boolean result = false;
		
		Session session = null;
		
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      List<Mail> mails = session.createQuery("from Mail u where receiverId=:receiverId")
					.setCacheable(true)
					.setParameter("receiverId", userId)
					.list();
	     
	    
	      for (Mail mail : mails) {
	    	  if(mail.getId() == mId){
	    		  System.out.println(mail);
	    		  System.out.println(mail.getAtm());
	    		  
	    		  HashMap<String, Comparable> map = new HashMap<String, Comparable>();
	    		  map.put("result", 1);
	    		  map.put("mailId", mail.getId());
	    		  map.put("isReceivced", mail.getReceived());
	    		  map.put("content", mail.getContent());
	    		  map.put("atmType", mail.getAtm().getAtmType());
	    		  map.put("atmCount", mail.getAtm().getAtmCount());
	    		  map.put("isRead", 1);
	    		  
	    		 
		    	  req.setBody(JSONObject.fromObject(map));
				  ioSession.write(req);
				  result = true;
				  mail.setRead((byte)1);
	    	  }
				
		  }
	      
	      session.getTransaction().commit();
	      
	      
	      
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	      result = false;
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
	    return result;
	}

	@Override
	public boolean deleteMail(IoSession ioSession,Request req,int userId,int mid) {

		boolean result = false;
		
		Session session = null;
		
	    
	    try {
		      session = HibernateSessionFactory.getSession();
		      session.beginTransaction();
		      
		      Mail mail = new Mail();
			  mail.setId(mid);
			  session.delete(mail);
		      
		      result = true;
			  session.getTransaction().commit();
		      
		    } catch (Exception e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		      session.getTransaction().rollback();
		      result = false;
		    }finally{
		    	if(session.isOpen()){
		    		session.close();
		    	}
		    	
		    }
	    
	    return result;
	}
	
	
	@Override
	public boolean deleteMailAll(IoSession ioSession,Request req, int userId) {
		
		boolean result = false;
		List<Mail> mails = null;
		Session session = null;
		
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      mails = session.createQuery("from Mail u where receiverId=:receiverId")
					.setCacheable(true)
					.setParameter("receiverId", userId)
					.list();
	    
	     
	      result = true;
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	      result = false;
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
	    
	    for (Mail mail : mails) {
	    	result = deleteMail(ioSession,req,userId,mail.getId());
		}
	   
	    
	    
	    
	    return result;
	}
	
	
	

	@Override
	public boolean deleteMails(IoSession ioSession,Request req,int userId,Set<Integer> mids) {
		
		boolean result = false;
		 List<Mail> mails = null;
		Session session = null;
		
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      mails = session.createQuery("from Mail u where receiverId=:receiverId")
					.setCacheable(true)
					.setParameter("receiverId", userId)
					.list();
	    
	     
	      result = true;
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	      result = false;
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
	    
	    for (Mail mail : mails) {
	    	
	    	for (int mid : mids) {
	    		if(mail.getId() == mid){
	    			 deleteMail(ioSession,req,userId,mid);
	    		}
	    		
			}
	    	
		}
	   
	    
	    
	    
	    return result;
	}

	@Override
	public boolean acquireAtm(IoSession ioSession,Request req,int userId,int mId) {
		
		boolean result = false;
		
		Session session = null;
		
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      List<Mail> mails = session.createQuery("from Mail u where receiverId=:receiverId")
					.setCacheable(true)
					.setParameter("receiverId", userId)
					.list();
	    
	      for (Mail mail : mails) {
	    	  if(mail.getId() == mId){
	    		  System.out.println(mail.getAtm());
	    	  }
				
		  }
	      
	      
	      session.getTransaction().commit();
	      
	      
	      result = true;
	      
	      acquireAtmSign(ioSession,req,userId,mId);
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	      result = false;
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
	    return result;
	}

	@Override
	public boolean acquireAllAtm(IoSession ioSession,Request req,int userId) {
		
		boolean result = false;
		
		Session session = null;
		
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      

	      List<Mail> mails = session.createQuery("from Mail u where receiverId=:receiverId")
					.setCacheable(true)
					.setParameter("receiverId", userId)
					.list();
	    
	      for (Mail mail : mails) {
				System.out.println(mail.getAtm());
		  }
	      
	      session.getTransaction().commit();
	      
	      
	      result = true;
	      acquireAtmAllSign(ioSession,req,userId);
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	      result = false;
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
	    return result;
	}

	@Override
	public boolean acquireAtmSign(IoSession ioSession,Request req, int userId, int mId) {
		
		boolean result = false;
		
		Session session = null;
		
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      
	      List<Mail> mails = session.createQuery("from Mail u where receiverId=:receiverId")
					.setCacheable(true)
					.setParameter("receiverId", userId)
					.list();
	    
	      for (Mail mail : mails) {
				if(mail.getId() == mId){
					
					if(mail.getReceived() != 1){
						AtmBack back = new AtmBack();
						back.setAtmType(mail.getAtm().getAtmType());
						back.setAtmCount(mail.getAtm().getAtmCount());
						back.setResult(1);
						
						req.setBody(JSONObject.fromObject(back));
						ioSession.write(req);
						
						mail.setRead((byte)1);
						mail.setReceived((byte)1);
						session.update(mail);
						result = true;
					}
					
					 
				}
		  }
	      
	      session.getTransaction().commit();
	      
	      
	     
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	      result = false;
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
	    return result;
	}

	@Override
	public boolean acquireAtmAllSign(IoSession ioSession,Request req, int userId) {
		
		
		boolean result = false;
		
		Session session = null;
		
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      
	      List<Mail> mails = session.createQuery("from Mail u where receiverId=:receiverId")
					.setCacheable(true)
					.setParameter("receiverId", userId)
					.list();
	    
	      for (Mail mail : mails) {
	    	  req.setCmd((short)MailCmd.ACQUIREATM);
	    	  result = acquireAtmSign(ioSession,req,userId,mail.getId());
		  }
	      
	      session.getTransaction().commit();
	      
	      
	      
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	      result = false;
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
	    return result;
	}

	

}
