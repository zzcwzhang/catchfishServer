package com.fuyun.server.handler.user;


import java.util.Date;
import java.util.HashMap;

import org.apache.mina.core.session.IoSession;

import com.fuyun.server.db.DBManager;
import com.fuyun.server.db.dao.IUserDao;
import com.fuyun.server.db.dao.IUserModuleDao;
import com.fuyun.server.db.dto.ErrorBack;
import com.fuyun.server.db.model.User;
import com.fuyun.server.db.util.DateJsonValueProcessor;
import com.fuyun.server.debug.DebugWll;
import com.fuyun.server.module.ErrorCode;
import com.fuyun.server.module.Module;
import com.fuyun.server.socket.BaseHandler;
import com.fuyun.server.socket.Invoker;
import com.fuyun.server.socket.msg.Request;
import com.fuyun.server.world.FishWorld;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


public class UserHandler extends BaseHandler{

	IUserDao useDao = DBManager.getInstance().getUserDAO();
	IUserModuleDao userModuleDao = DBManager.getInstance().getUserModuleDAO();
	
	
	@Override
	public int getModule() {
		// TODO Auto-generated method stub
		return Module.USER;
	}

	@Override
	public void inititialize() {
		// TODO Auto-generated method stub
		putInvoker(UserCmd.REGISTER, new Invoker() {
					
			public void invoke(IoSession session,Request req) {
				register(session,req);
			}
		});
		
		putInvoker(UserCmd.LOGIN, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				login(session,req);
			}
		});
		
		putInvoker(UserCmd.SIGN, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				sign(session,req);
			}
		});
		
		putInvoker(UserCmd.SAVE, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				save(session,req);
			}
		});
		
		
		
		putInvoker(UserCmd.FISH_REGISTER, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				fish_register(session,req);
			}
		});
		putInvoker(UserCmd.FISH_LOGIN, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				fish_login(session,req);
			}
		});
		
		putInvoker(UserCmd.FISH_NICKNAME, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				fish_nickname(session,req);
			}
		});
		
		putInvoker(UserCmd.FISH_RANDOM, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				fish_random(session,req);
			}
		});
		
		putInvoker(UserCmd.FISH_SIGN, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				fish_sign(session,req);
			}
		});

		putInvoker(UserCmd.FISH_SAVE, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				fish_save(session,req);
			}
		});
		
		
		
	}
	
	
	protected void register(IoSession session,Request req){
		 System.out.println("注册处理 ");
		 System.out.println(req.toString());
		 JSONObject json = req.getBody();

		 String nickName =json.getString("userId");
		 User u = new User(nickName);
			
		 int result = -1;
		 synchronized (DBManager.getInstance().getUserDAO()) {
			 result = userModuleDao.register(u);
		 }
		System.out.println(result);
		
		if(result>-1){
			HashMap<String, Comparable> map = new HashMap<String, Comparable>();
			map.put("result", 1);
			req.setBody(JSONObject.fromObject(map));
		}else{
			ErrorBack error  = new ErrorBack(0, ErrorCode.USEREXIST);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		
		session.write(req);

	} 
	
	protected void login(IoSession session,Request req){
		System.out.println("登录处理 ");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
		String nickName =json.getString("userId");
		User u = null;
		synchronized (DBManager.getInstance().getUserDAO()) {
			if(DebugWll.Multi){
				u = userModuleDao.login_yali(id);
			}else{
				u = userModuleDao.login(id, nickName);
			}
			
		}
		int result = 0;
		if(u != null){
			FishWorld.getInstance().enterHall(session, u);
			result = 1;
		}
		
		HashMap<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("result", 1);
		req.setBody(JSONObject.fromObject(map));
		
		session.write(req);
	}
	
	protected void sign(IoSession session,Request req){
		System.out.println("签到处理 ");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
		
		synchronized (DBManager.getInstance().getUserDAO()) {
			
			if(!FishWorld.getInstance().isExist(id)){
				User u = useDao.load(id);
				FishWorld.getInstance().enterHall(session, u);
			}
			
			userModuleDao.sign(id);
		}
		
		
		
	}
	
	protected void save(IoSession session,Request req){
		System.out.println("上传数据处理 ");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
		
		int chargeScore = json.getInt("chargeScore");
		int fishScore = json.getInt("fishScore");
		int liked = json.getInt("liked");
		
		if(!FishWorld.getInstance().isExist(id)){
			synchronized (DBManager.getInstance().getUserDAO()) {
				User u = useDao.load(id);
				FishWorld.getInstance().enterHall(session, u);
				
				u.setChargeScore(chargeScore);
				u.setFishScore(fishScore);
				u.setLiked(liked);
				
				useDao.update(u);
			}
			
			
		}
		
		User u = FishWorld.getInstance().getUser(id);
		System.out.print(u.getChargeScore());
		
		
		
	}
	
	
	protected void fish_register(IoSession session,Request req){
		 System.out.println("fish注册处理 ");
		 System.out.println(req.toString());
		 JSONObject json = req.getBody();

		 String userName =json.getString("userName");
		 String password =json.getString("password");
		 User u = new User(userName,password);
		 //默认1000金币
		 u.setCoin(100000);
		 int result = -1;
		 synchronized (DBManager.getInstance().getUserDAO()) {
			 result = userModuleDao.register(u);
		 }
		System.out.println(result);
		
		if(result>-1){
			HashMap<String, Comparable> map = new HashMap<String, Comparable>();
			map.put("result", 1);
			req.setBody(JSONObject.fromObject(map));
		}else{
			ErrorBack error  = new ErrorBack(0, ErrorCode.USEREXIST);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		
		session.write(req);

	} 
	
	
	protected void fish_login(IoSession session,Request req){
		System.out.println("fish登录处理 ");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		String userName =json.getString("userName");
	    String password =json.getString("password");
		User u = userModuleDao.login(userName, password);
		
		
		
		
		if(u != null){
			FishWorld.getInstance().enterHall(session, u);
			HashMap<String, Comparable> map = new HashMap<String, Comparable>();
			map.put("result", 1);
			
			map.put("userId", u.getId());
			map.put("nickname", u.getNickname());
			
			map.put("vip", u.getVip());
			map.put("level", u.getLevel());
			map.put("sex", u.getSex());
			
			map.put("coin", u.getCoin());
			map.put("propPause", u.getPropPause());
			map.put("propTopspeed", u.getPropTopspeed());
			map.put("propRage", u.getPropRage());
			map.put("propLock", u.getPropLock());
			map.put("propThousand", u.getPropThousand());
			
			map.put("chargeScore", u.getChargeScore());
			map.put("fishScore", u.getFishScore());
			map.put("liked", u.getLiked());
			map.put("onlineTimes", u.getOnlineTimes());
			map.put("ticket", u.getTicket());
			
			map.put("signData", u.getSignMask());
			map.put("nicknameSign", u.getNicknameSign());
			
			////新增数据
			 //int gameCheck;//0 关卡
			 //int playerRank;//0 军衔
			 //char cannonInf;	//炮台信息，8位  1
			 //int goldRate;//倍率 0
			map.put("gameCheck", u.getGameCheck());
			map.put("playerRank", u.getPlayerRank());
			map.put("cannonInf", u.getCannonInf());
			map.put("goldRate", u.getGoldRate());
			
			
			map.put("Date", new Date());
			JsonConfig config = new JsonConfig(); 
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
			
			req.setBody(JSONObject.fromObject(map,config));
			
			
		}else{
			
			boolean result = userModuleDao.checkUserName(userName);
			
			if(!result){
				ErrorBack error  = new ErrorBack(0, ErrorCode.PSDERR);
				System.out.println(error);
				req.setBody(JSONObject.fromObject(error));
			}else{
				ErrorBack error  = new ErrorBack(0, ErrorCode.USERNOTEXIST);
				System.out.println(error);
				req.setBody(JSONObject.fromObject(error));
			}
			
			
		}
		
		session.write(req);
	}
	
	
	protected void fish_nickname(IoSession session,Request req){
		System.out.println("fish修改昵称处理 ");
		System.out.println(req.toString());
		JSONObject json = req.getBody();
		
		int id =json.getInt("id");
	        
	        
        if(FishWorld.getInstance().isExist(id)){
			
			FishWorld.getInstance().enterHall(session, id);
			
		}else{
			User u = useDao.load(id);
			FishWorld.getInstance().enterHall(session, u);
		}

		String nickname =json.getString("nickname");
	    boolean result = userModuleDao.isSame(nickname);
	    if(result){
	    	
	    	
	    	ErrorBack error  = new ErrorBack(0, ErrorCode.SAMENICKNAME);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
	    }else{
	    	
	    	//
	    	User u = FishWorld.getInstance().getUser(id);
	    	u.setNickname(nickname);
	    	u.setNicknameSign((byte)1);
	    	
	    	HashMap<String, Comparable> map = new HashMap<String, Comparable>();
			map.put("result", 1);
			req.setBody(JSONObject.fromObject(map));
	    }
		
		session.write(req);
	}
	
	
	
	protected void fish_random(IoSession session,Request req){
		System.out.println("fish随机昵称处理 ");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		String nickname = userModuleDao.randomNickname();
		if(nickname != ""){
			HashMap<String, Comparable> map = new HashMap<String, Comparable>();
			map.put("result", 1);
			map.put("nickname", nickname);
			req.setBody(JSONObject.fromObject(map));
		}else{
			ErrorBack error  = new ErrorBack(0, ErrorCode.RANDOMNICKNAME);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		session.write(req);
	}
	
	
	protected void fish_sign(IoSession session,Request req){
		System.out.println("fish签到处理 ");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
		
		
			
		if(FishWorld.getInstance().isExist(id)){
			
			FishWorld.getInstance().enterHall(session, id);
			
		}else{
			User u = useDao.load(id);
			FishWorld.getInstance().enterHall(session, u);
		}
		
		userModuleDao.SignBin(id);
		
	}
	
	protected void fish_save(IoSession session,Request req){
		System.out.println("fish上传处理 ");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

	
        int id =json.getInt("id");
        
        
        if(FishWorld.getInstance().isExist(id)){
			
			FishWorld.getInstance().enterHall(session, id);
			
		}else{
			User u = useDao.load(id);
			FishWorld.getInstance().enterHall(session, u);
		}
        
        
      
		try {
			User u = FishWorld.getInstance().getUser(id);
			
//			游戏数据
//			 * 	等级（level）byte
			if(json.has("level")){
				int level = json.getInt("level");
				u.setLevel((byte)level);
			}
			
//			 * 	Vip等级（vip）byte
			if(json.has("vip")){
				int vip = json.getInt("vip");
				u.setVip((byte)vip);
			}
//			 * 	金币（coin）int
			if(json.has("coin")){
				int coin = json.getInt("coin");
				u.setCoin(coin);
			}
//			 * 	充值积分（chargeScore）int
			if(json.has("chargeScore")){
				int chargeScore = json.getInt("chargeScore");
				u.setChargeScore(chargeScore);
			}
//			 * 	鱼分（fishScore）int
			if(json.has("fishScore")){
				int fishScore = json.getInt("fishScore");
				u.setFishScore(fishScore);
			}
//			 * 	获赞次数（liked）int
//			 * 	在线时长单位为秒（onlineTimes）int
			if(json.has("onlineTimes")){
				int onlineTimes = json.getInt("onlineTimes");
				u.setOnlineTimes(onlineTimes);
			}
//			 * 	定屏  (propPause)  int
			if(json.has("propPause")){
				int propPause = json.getInt("propPause");
				u.setPropPause(propPause);
			}
//			 * 	极速（propTopspeed）int
			if(json.has("propTopspeed")){
				int propTopspeed = json.getInt("propTopspeed");
				u.setPropTopspeed(propTopspeed);
			}
//			 * 	狂暴（propRage）int
			if(json.has("propRage")){
				int propRage = json.getInt("propRage");
				u.setPropRage(propRage);
			}
//			 * 	锁定（propLock）int
			if(json.has("propLock")){
				int propLock = json.getInt("propLock");
				u.setPropLock(propLock);
			}
//			 * 	10000炮（propThousand）int
			if(json.has("propThousand")){
				int propThousand = json.getInt("propThousand");
				u.setPropThousand(propThousand);
			}
//			奖券 int ticket
			if(json.has("ticket")){
				int ticket = json.getInt("ticket");
				u.setTicket(ticket);
			}
//			*新增数据
//			 * int gameCheck;//0 关卡
			if(json.has("gameCheck")){
				int gameCheck = json.getInt("gameCheck");
				u.setGameCheck(gameCheck);
			}
//			 * int playerRank;//0 军衔
			if(json.has("playerRank")){
				int playerRank = json.getInt("playerRank");
				u.setPlayerRank(playerRank);
			}
//			   int cannonInf;	//炮台信息，8位  1
			if(json.has("cannonInf")){
				int cannonInf = json.getInt("cannonInf");
				u.setCannonInf(cannonInf);
			}
//			   int goldRate;//倍率 0
			if(json.has("goldRate")){
				int goldRate = json.getInt("goldRate");
				u.setGoldRate(goldRate);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorBack error  = new ErrorBack(0, ErrorCode.SAVEERR);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		
		
		
		
		
		
		
		session.write(req);
		
		
	}

}
