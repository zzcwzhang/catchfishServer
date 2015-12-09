package com.fuyun.server.db.dao.impl;

import java.util.Date;
import java.util.Random;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fuyun.server.db.dao.IUserDao;
import com.fuyun.server.db.dao.IUserModuleDao;
import com.fuyun.server.db.model.User;
import com.fuyun.server.db.util.ChineseName;
import com.fuyun.server.db.util.DateUtil;
import com.fuyun.server.db.util.HibernateSessionFactory;
import com.fuyun.server.reward.SignReward;
import com.fuyun.server.reward.SignType;
import com.fuyun.server.world.FishWorld;

@Component("userModuleDao")
public class UserModuleDao implements IUserModuleDao{

	@Autowired
	private IUserDao userDao;
	@Override
	public int register(User user) {
		
		int result = -1;
		
	    if(userDao.add(user)){
	       result = 1;
	    }
	    return result;
	}

	@Override
	public User login(int id, String userName) {
		
		
		User u = userDao.load(id, userName);
		if(u == null){
			System.out.println("登录失败");
			
		}else{
			System.out.println(u.getName()+": 登录成功");
			
		}
		
		return u;
	}

	@Override
	public void sign(int id) {
		
	}

	@Override
	public int save(User user) {
		
		int result = -1;
		
		
		return result;
	}

	@Override
	public User login_yali(int id) {
		
		User u = userDao.load(id);
		if(u == null){
			System.out.println("登录失败");
			
		}else{
			System.out.println(u.getName()+": 登录成功");
			
		}
		
		return u;
		
	}

	@Override
	public User login(String userName, String password) {
		
		
		Session session = null;
		User u = null;
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      
	      u = (User) session.createQuery("from User u where name=:name and password=:password")
					.setCacheable(true)
					.setParameter("name", userName)
					.setParameter("password", password)
					.uniqueResult();
	    
	      
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
	    
	    return u;
	}

	@Override
	public boolean isSame(String nickname) {
		
		
		boolean result = false;
		
		Session session = null;
		
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      User  u = (User) session.createQuery("from User u where nickname=:nickname")
					.setCacheable(true)
					.setParameter("nickname", nickname)
					.uniqueResult();
	     
	      if(u != null){
	    	  result = true;
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
	public String randomNickname() {
		String nickname = "";
		Random ra =new Random();
		nickname = ChineseName.getInstance().getNames(ra.nextInt(2)+2);
		if(isSame(nickname)){
			nickname = randomNickname();
		}
		return nickname;
	}

	@Override
	public void SignBin(int id) {
		// TODO Auto-generated method stub
		User u = FishWorld.getInstance().getUser(id);

		
		int signMask = u.getSignMask();
		int tMonth = u.getSignMonth();
		
		//新签到;

		
		//判断月份是否是本月;
		int BMonth =new Integer(DateUtil.getMonth(new Date()));//本月月份
//		System.out.println("月"+BMonth);
		
		if(tMonth != BMonth){
//			System.out.println("刷新本月签到并写入新月");
			signMask = 0;
			//u.setSignMonth(BMonth);
		}
		
		//判断今日签到
		
		int Btoday =new Integer(DateUtil.getDay(new Date()));//今天日期
//		System.out.println("新签到"+Btoday);
		
		int Btday = 1<<(Btoday-1); //今天掩码
//		System.out.println("After sign = "+Integer.toBinaryString(Btday));
		
		
		
		//第一次签到
		if(signMask == 0){
			signMask |= Btday;
			//获得第一次连签奖励
			SignReward.getInstance().setReward(id,SignType.NORMAL);
		}
		
		//判断是否重复签到
		if((signMask&Btday)>=1){
			//签到重复
			SignReward.getInstance().setReward(id,SignType.SIGNED);
		}
		else{ //新鲜签到,判断奖励和连续签到
			
			signMask |= Btday;//今日签到
//			System.out.println("今日签到");
			
			int signAllCount = 0;
			int signLian = 0;
			int signHistory = 0;
			
			for(int i = 0;i<Btoday;i++){
				int cut = 1<<i;//读取的游标
				int tday = i+1;//游标的日期
				
				if((signMask&cut) >= 1){
					signAllCount++;
					signLian++;
					
					
					if(signLian>signHistory){
						//刷新连续签到记录
						signHistory++;
						System.out.println("连续签到"+signHistory+"次");
					}
					
//					System.out.println("The "+tday+" days is sign");
					
				}else{
					signLian = 0;
				}
				
			}//循环判断结束
			if(signAllCount == 15){
//				System.out.println("连续15次");
				SignReward.getInstance().setReward(id,SignType.SEQ15);
			}
			
			if(signAllCount == 25){
//				System.out.println("连续25次");
				SignReward.getInstance().setReward(id,SignType.SEQ25);
			}	
			if(signLian==signHistory){
				SignReward.getInstance().setReward(id,signHistory);
			}
			
			System.out.println("ALL :" + signAllCount + "  Lian : "+signLian+"  Histry : "+ signHistory);
			System.out.println("After sign = "+Integer.toBinaryString(signMask));
		}
		
		
		u.setSignMonth(BMonth);
		u.setSignMask(signMask); 
	}

	@Override
	public boolean checkUserName(String userNmae) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		Session session = null;
		
	    try {
	      session = HibernateSessionFactory.getSession();
	      session.beginTransaction();
	      
	      User  u = (User) session.createQuery("from User u where name=:name")
					.setCacheable(true)
					.setParameter("name", userNmae)
					.uniqueResult();
	     
	      if(u != null){
	    	  result = true;
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
