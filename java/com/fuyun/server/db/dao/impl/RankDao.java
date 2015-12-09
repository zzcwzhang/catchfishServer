package com.fuyun.server.db.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fuyun.server.db.dao.IRankDao;
import com.fuyun.server.db.dto.BigRankBack;
import com.fuyun.server.db.dto.LikedRankBack;
import com.fuyun.server.db.dto.SuperRankBack;
import com.fuyun.server.db.dto.TicketRankBack;
import com.fuyun.server.db.dto.TimeRankBack;
import com.fuyun.server.db.model.User;
import com.fuyun.server.db.util.HibernateSessionFactory;
import com.fuyun.server.handler.rank.RankCmd;
import com.fuyun.server.module.rank.RankManager;
import com.fuyun.server.world.FishWorld;

@Component("rankDao")
public class RankDao implements IRankDao{

	
	@Override
	public List<BigRankBack> getBigRank(int id, int count) {
		
		Session session = null;
		List<BigRankBack> ranks = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      ranks = session.createQuery("select new com.fuyun.server.db.dto.BigRankBack"+
	    		  					  "(u.vip as vip,u.id as id,u.nickname as nickName,u.chargeScore as chargeScore)"+
	    		  					  "from User u  order by u.chargeScore desc")
													               .setCacheable(true)
													               .setMaxResults(count)
													               .list();
	    session.getTransaction().commit();
	    int rank_index = 1;
	    for (BigRankBack rank : ranks) {
	    	if(RankManager.getInstance().checkExist(id, rank.getId(), RankCmd.BIGRANK)){
	    		rank.setToLike(true);
	    	}
			rank.setRank(rank_index);
			rank_index++;
			//System.out.println(rank);
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
		    
		return ranks;
	}

	@Override
	public int getBigRankSelf(int id) {
		
		int result = -1;
		String sql ="";
		sql ="select rank from "
				+"(SELECT @counter\\:=@counter+1 AS rank,id,vip,username,chargeScore FROM user_fish ,(Select @counter\\:=0) as t ORDER BY chargeScore desc )"
				+" as d where d.id = '"
				+id
				+"' ";
		Session session = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      Object obj = (Object) session.createSQLQuery(sql)
	    		  //.setCacheable(true)
	    		  .uniqueResult();
		  

	      result = ((Double)obj).intValue();
	     
	    
	  
	      session.getTransaction().commit();
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	      result = -1;
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
	    
	    return result;
	}

	@Override
	public List<SuperRankBack> getSuperRank(int id, int count) {
		Session session = null;
		List<SuperRankBack> ranks = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      ranks = session.createQuery("select new com.fuyun.server.db.dto.SuperRankBack"+
	    		  					  "(u.vip as vip,u.id as id,u.nickname as nickName,u.fishScore as fishScore)"+
	    		  					  "from User u  order by u.fishScore desc")
													               .setCacheable(true)
													               .setMaxResults(count)
													               .list();
	     int rank_index = 1;
	    for (SuperRankBack rank : ranks) {
	    	if(RankManager.getInstance().checkExist(id, rank.getId(), RankCmd.SUPERRANK)){
	    		rank.setToLike(true);
	    	}
			rank.setRank(rank_index);
			rank_index++;
			//System.out.println(rank);
		}
	      
	    session.getTransaction().commit();
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
		    
		return ranks;
	}

	@Override
	public int getSuperRankSelf(int id) {
		int result = -1;
		String sql ="";
		sql ="select rank from "
				+"(SELECT @counter\\:=@counter+1 AS rank,id,vip,username,fishScore FROM user_fish ,(Select @counter\\:=0) as t ORDER BY fishScore desc )"
				+" as d where d.id = '"
				+id
				+"' ";
		Session session = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      Object obj = (Object) session.createSQLQuery(sql)
	    		  //.setCacheable(true)
	    		  .uniqueResult();
		  

	      result = ((Double)obj).intValue();
	     
	    
	      session.getTransaction().commit();
	 
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	      result = -1;
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
	    
	    return result;
	}

	@Override
	public List<TimeRankBack> getTimeRank(int id, int count) {
		Session session = null;
		List<TimeRankBack> ranks = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      ranks = session.createQuery("select new com.fuyun.server.db.dto.TimeRankBack"+
	    		  					  "(u.vip as vip,u.id as id,u.nickname as nickName,u.onlineTimes as onlineTimes)"+
	    		  					  "from User u  order by u.onlineTimes desc")
													               .setCacheable(true)
													               .setMaxResults(count)
													               .list();
	     int rank_index = 1;
	    for (TimeRankBack rank : ranks) {
	    	if(RankManager.getInstance().checkExist(id, rank.getId(), RankCmd.TIMERANK)){
	    		rank.setToLike(true);
	    	}
			rank.setRank(rank_index);
			rank_index++;
			//System.out.println(rank);
		}
	      
	    session.getTransaction().commit();
	      
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
		    
		return ranks;
	}

	@Override
	public int getTimeRankSelf(int id) {
		int result = -1;
		String sql ="";
		sql ="select rank from "
				+"(SELECT @counter\\:=@counter+1 AS rank,id,vip,username,onlineTimes FROM user_fish ,(Select @counter\\:=0) as t ORDER BY onlineTimes desc )"
				+" as d where d.id = '"
				+id
				+"' ";
		Session session = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      Object obj = (Object) session.createSQLQuery(sql)
	    		  //.setCacheable(true)
	    		  .uniqueResult();
		  

	      result = ((Double)obj).intValue();
	     
	    
	      session.getTransaction().commit();
	 
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	      result = -1;
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
	    
	    return result;
	}

	@Override
	public List<LikedRankBack> getLikedRank(int id, int count) {
		Session session = null;
		List<LikedRankBack> ranks = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      ranks = session.createQuery("select new com.fuyun.server.db.dto.LikedRankBack"+
	    		  					  "(u.vip as vip,u.id as id,u.nickname as nickName,u.liked as liked)"+
	    		  					  "from User u  order by u.liked desc")
													               .setCacheable(true)
													               .setMaxResults(count)
													               .list();
	    session.getTransaction().commit();
	     int rank_index = 1;
	    for (LikedRankBack rank : ranks) {
	    	if(RankManager.getInstance().checkExist(id, rank.getId(), RankCmd.LIKEDRANK)){
	    		rank.setToLike(true);
	    	}
			rank.setRank(rank_index);
			rank_index++;
			//System.out.println(rank);
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
		    
		return ranks;
	}

	@Override
	public int getLikedRankSelf(int id) {
		int result = -1;
		String sql ="";
		sql ="select rank from "
				+"(SELECT @counter\\:=@counter+1 AS rank,id,vip,username,liked FROM user_fish ,(Select @counter\\:=0) as t ORDER BY liked desc )"
				+" as d where d.id = '"
				+id
				+"' ";
		Session session = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      Object obj = (Object) session.createSQLQuery(sql)
	    		  //.setCacheable(true)
	    		  .uniqueResult();
		  

	      result = ((Double)obj).intValue();
	     
	      session.clear();
	      session.getTransaction().commit();
	 
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	      result = -1;
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
	    
	    return result;
	}

	@Override
	public List<TicketRankBack> getTicketRank(int id, int count) {
		
		Session session = null;
		List<TicketRankBack> ranks = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      ranks = session.createQuery("select new com.fuyun.server.db.dto.TicketRankBack"+
	    		  					  "(u.vip as vip,u.id as id,u.nickname as nickName,u.ticket as ticket)"+
	    		  					  "from User u  order by u.ticket desc")
													               .setCacheable(true)
													               .setMaxResults(count)
													               .list();
	      
	     int rank_index = 1;
	    for (TicketRankBack rank : ranks) {
	    	if(RankManager.getInstance().checkExist(id, rank.getId(), RankCmd.TICKETRANK)){
	    		rank.setToLike(true);
	    	}
			rank.setRank(rank_index);
			rank_index++;
			//System.out.println(rank);
		}
	      
	      
	    session.getTransaction().commit();
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
		    
		return ranks;
	}

	@Override
	public int getTicketRankSelf(int id) {
		
		int result = -1;
		String sql ="";
		sql ="select rank from "
				+"(SELECT @counter\\:=@counter+1 AS rank,id,vip,username,ticket FROM user_fish ,(Select @counter\\:=0) as t ORDER BY ticket desc )"
				+" as d where d.id = '"
				+id
				+"' ";
		Session session = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      Object obj = (Object) session.createSQLQuery(sql)
	    		  //.setCacheable(true)
	    		  .uniqueResult();
		  

	      result = ((Double)obj).intValue();
	     
	      session.getTransaction().commit();
	  
	 
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      session.getTransaction().rollback();
	      result = -1;
	    }finally{
	    	if(session.isOpen()){
	    		session.close();
	    	}
	    	
	    }
	    
	    return result;
	}
	
	@Override
	public boolean giveLike(int id,int likedId, int type) {
		
		if(FishWorld.getInstance().isExist(likedId)){
			User u = FishWorld.getInstance().getUser(likedId);
			u.setLiked(u.getLiked()+1);
		}
		
		boolean result = false;
		String sql ="";
		sql ="update user_fish set liked = liked+1 where id = '"
				+likedId
				+"' ";
		Session session = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      session.createSQLQuery(sql).executeUpdate();
	  	  session.getTransaction().commit();
	  	  session.clear();
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
	    
	    return result;
	}

}
