package com.fuyun.server.db.util;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


//@SuppressWarnings("deprecation")//表示不检测过期的方法
public class HibernateSessionFactory {

	
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	public static SessionFactory sessionFactory;

	static {
		try{			
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
					.configure() // configures settings from hibernate.cfg.xml
					.build();
			
			 sessionFactory  = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
			 
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private HibernateSessionFactory() {

	}

	public static Session getSession() {
//		Session session = threadLocal.get();
//		if (session == null) {
//			session = (sessionFactory != null) ? sessionFactory.openSession() : null;
//			threadLocal.set(session);
//		}
//		return session;
		return sessionFactory.openSession();
	}
	
	/**
	 * 提交Session
	 * @param session
	 * @author Feng
	 */
	public static void commitSession(Session session) {
        Transaction trans = session.beginTransaction();
        trans.commit();
        session.close();
	}
	
	
	public static void close(Session session){
		if(session.isOpen()){
			session.close();
		}
	}
}
