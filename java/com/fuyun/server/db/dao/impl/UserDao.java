package com.fuyun.server.db.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.fuyun.server.db.dao.IUserDao;
import com.fuyun.server.db.model.User;
import com.fuyun.server.db.util.HibernateSessionFactory;

@Component("userDao")
public class UserDao implements IUserDao{

	//private Session session = null;
	
	
	
	@Override
	public boolean add(User user) {
		boolean result = false;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			session.save(user);
			
			session.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("用户名已存在");
			session.getTransaction().rollback();
			return false;
		}finally{
			if(session.isOpen()){
				session.close();
			}
		}
		
		return result;
	}

	@Override
	public boolean update(User user) {
		boolean result = false;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			session.update(user);
			
			
			session.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session.isOpen()){
				session.close();
			}
		}
		
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			User u = new User();
			u.setId(id);
			
			session.delete(u);
			
			session.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			if(session.isOpen()){
				session.close();
			}
		}
		
		return result;
	}

	@Override
	public User load(int id) {
		User u = null;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			u = (User)session.createQuery("select u from User u where id=:id")
					.setCacheable(true)
					.setParameter("id", id)
					.uniqueResult();
			
			//System.out.println(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			if(session.isOpen()){
				session.close();
			}
		}
		
		return u;
	}

	@Override
	public User load(int id, String userName) {
		User u = null;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			u = (User)session.createQuery("select u from User u where id=:id and name=:name")
					.setCacheable(true)
					.setParameter("id", id)
					.setParameter("name", userName)
					.uniqueResult();
			
			//System.out.println(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//查询不到不会用异常 所以不会执行
			System.out.println("用户 id:"+id +" name :"+userName+" 不存在");
			session.getTransaction().rollback();
			return null;
		}finally {
			if(session.isOpen()){
				session.close();
			}
		}
		
		return u;
	}
	
	@Override
	public boolean isExist(int id) {
		//3.查询一行任何类型的数据，最后一个参数指定返回结果类型  
	    Session session = null;
	    User u = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      Object obj = session.createQuery("select 1 from User u where id=:id")
	               .setCacheable(true)
	               .setParameter("id", id)
	        
	               .uniqueResult();
	      if(obj == null){
	        return false;
	      }
	      
	      int result = (Integer)obj;
	      if(result == 1){
	        return true;
	      }
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	    }finally{
	    	if(session.isOpen()){
				session.close();
			}
	    }
	    
	    return false;

	}
	
	@Override
	public boolean isExist(int id, String userName) {
		//3.查询一行任何类型的数据，最后一个参数指定返回结果类型  
	    Session session = null;
	    User u = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      Object obj = session.createQuery("select 1 from User u where id=:id and name=:name")
	               .setCacheable(true)
	               .setParameter("id", id)
	               .setParameter("name", userName)
	               .uniqueResult();
	      if(obj == null){
	        return false;
	      }
	      
	      int result = (Integer)obj;
	      if(result == 1){
	        return true;
	      }
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	    }finally {
	    	if(session.isOpen()){
				session.close();
			}
		}
	    
	    return false;
	}
	
	
	
	

	@Override
	public List<User> list(String hql) {
		return list(hql,100);

	}

	@Override
	public List<User> list(String hql, int count) {
		List<User> us = null;
		Session session = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      
	      
	      us = session.createQuery(hql)
	          .setCacheable(true)
	          .setMaxResults(count)
	          .list();
//	      for (ZombieUser u : us) {
//	        System.out.println(u.getNickname());
//	      }
	      
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	    }finally {
	    	if(session.isOpen()){
				session.close();
			}
		}
	    
	    return us;
	}

	@Override
	public List<User> listSQL(String sql) {
		List<User> us = null;
		Session session = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      
	      
	      us = session.createSQLQuery(sql).addEntity(User.class)
	    		  .setCacheable(true)
	    		  .list();
//		      for (ZombieUser u : us) {
//		        System.out.println(u.getNickname());
//		      }
	      
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	    }finally {
	    	if(session.isOpen()){
				session.close();
			}
		}
	    
	    return us;

	}

	@Override
	public int getMaxId() {
		int result = -1;
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			result = (Integer) session.createSQLQuery("select max(id) from user_fish").uniqueResult();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("用户名已存在");
			session.getTransaction().rollback();
		}finally{
			if(session.isOpen()){
				session.close();
			}
		}
		
		return result;
	}

	

	

	
	

}
