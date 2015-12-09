package com.fuyun.server.world;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

import com.fuyun.server.db.DBManager;
import com.fuyun.server.db.model.User;
import com.fuyun.server.socket.AttributeKeys;

public class FishWorld {

	private static FishWorld instance = null;
	private Map<Integer, User> peoples_l; // User id -->User u
	private Map<Integer, Integer> sessons_l;// session id -->User id

	private FishWorld() {

		if (peoples_l == null) {
			peoples_l = new ConcurrentHashMap<Integer, User>();
			sessons_l = new ConcurrentHashMap<Integer, Integer>();
		}
	}

	public static FishWorld getInstance() {

		if (instance == null) {
			instance = new FishWorld();
		}
		return instance;
	}

	public int size() {
		if (peoples_l == null) {
			return 0;
		}
		return peoples_l.size();
	}

	// 查询在线用户 根据id
	public User getUser(int id) {

		return peoples_l.get(id);
	}

	// 查询在线用户  根据session
	public User getUser(IoSession session) {

		int id = (int) session.getId();
		return peoples_l.get(sessons_l.get(id));
	}
	
	
	//判断 是否存在（已登录）
	public boolean isExist(int id) {

		return peoples_l.containsKey(id);
	}
	//判断 是否存在（重复登录）
	public boolean isExist(IoSession session, int id) {

		if(!isExist(id)){
			return false;
		}
		
		User player = getUser(id);

		if (player.mgetSession().getId() == session.getId()) {
			return true;
		}
		return false;
	}

	// 离开大厅
	public void leaveHall(IoSession session) {
		leaveHall(session, 0);
	}

	public void leaveHall(IoSession session, int type) {
		if (peoples_l == null) {
			return;
		}
		int tag_enable = (Integer) session.getAttribute(AttributeKeys.KEY_TAG_ENABLE, 0);
		if (tag_enable == 0) {
			return;
		}

		User move_p = peoples_l.get(sessons_l.get((int) session.getId()));
		if (move_p != null) {

			sessons_l.remove(move_p.mgetSession().getId());

			System.out.println("在线人数：" + peoples_l.size());
			if (type == 0) {
				peoples_l.remove(move_p.getId());
				synchronized (DBManager.getInstance().getUserDAO()) {
					DBManager.getInstance().getUserDAO().update(move_p);

				}
				System.out.println("玩家：id:" + move_p.getId() + "  name" + move_p.getName() + "客户端sessionID="
						+ session.getId() + "  退出游戏");
			} else {
				System.out.println("玩家：id:" + move_p.getId() + "  name" + move_p.getName() + "客户端sessionID="
						+ session.getId() + "  被强制退出");
			}

		}

	}
	
	
	 public void enterHall(IoSession session,int  id){
		 
		 User player = getUser(id);
		 enterHall(session,player);
	 }
	
	//进入大厅
	  public void enterHall(IoSession session,User player){
	    
	    
	    
	    /**
	     * 同一账号 多次登录 最后登录有效
	     */
	    if(player.mgetSession()==null){
	      
	      
	      
	      peoples_l.put(player.getId(), player);
	      sessons_l.put((int)session.getId(), player.getId());
	      player.msetSession(session);
	      session.setAttribute(AttributeKeys.KEY_TAG_ENABLE, 1);
	      session.setAttribute(AttributeKeys.KEY_TAG_CLOSED, 0);
	      System.out.println("玩家：id:"+player.getId()+"  name: "+player.getName()+"  nickname: "+player.getName()+"客户端sessionID="+session.getId()+"  登录游戏");
	      System.out.println("在线人数："+peoples_l.size());
	      
	    }else{
	      
	      /**
	       * 过滤掉相同的 一样的 重复的
	       */
	      if(player.mgetSession().getId() == session.getId()){
	        return;
	      }
	      
	      IoSession se = player.mgetSession();
	      //leaveHall(se);
	      player.msetSession(null);
	      se.setAttribute(AttributeKeys.KEY_TAG_CLOSED, 1);
	      se.setAttribute(AttributeKeys.KEY_TAG_ENABLE, 0);
	      se.close(true);
	      enterHall(session,player);
	    }
	    
	    
	  }


}
