package com.fuyun.server.handler.rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.fuyun.server.db.DBManager;
import com.fuyun.server.db.dao.IRankDao;
import com.fuyun.server.db.dao.IUserDao;
import com.fuyun.server.db.dto.BigRankBack;
import com.fuyun.server.db.dto.ErrorBack;
import com.fuyun.server.db.dto.LikedRankBack;
import com.fuyun.server.db.dto.SuperRankBack;
import com.fuyun.server.db.dto.TicketRankBack;
import com.fuyun.server.db.dto.TimeRankBack;
import com.fuyun.server.db.model.User;
import com.fuyun.server.module.ErrorCode;
import com.fuyun.server.module.Module;
import com.fuyun.server.module.rank.RankLiked;
import com.fuyun.server.module.rank.RankManager;
import com.fuyun.server.socket.BaseHandler;
import com.fuyun.server.socket.Invoker;
import com.fuyun.server.socket.msg.Request;
import com.fuyun.server.world.FishWorld;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RankHandler extends BaseHandler{

	IUserDao useDao = DBManager.getInstance().getUserDAO();
	IRankDao rankDao = DBManager.getInstance().getRankDao();
	
	
	
	
	@Override
	public int getModule() {
		// TODO Auto-generated method stub
		return Module.RANK;
	}

	@Override
	public void inititialize() {
		
		putInvoker(RankCmd.BIGRANK, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				bigRank(session,req);
			}
		});
		
		putInvoker(RankCmd.SUPERRANK, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				superRank(session,req);
			}
		});
		
		putInvoker(RankCmd.TIMERANK, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				timeRank(session,req);
			}
		});
		
		putInvoker(RankCmd.LIKEDRANK, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				likedRank(session,req);
			}
		});
		
		
		putInvoker(RankCmd.BIGRANKSELF, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				bigRankSelf(session, req);
			}
		});
		
		putInvoker(RankCmd.SUPERRANKSELF, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				superRankSelf(session,req);
			}
		});
		
		putInvoker(RankCmd.TIMERANKSELF, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				timeRankSelf(session,req);
			}
		});
		
		putInvoker(RankCmd.LIKEDRANKSELF, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				likedRankSelf(session,req);
			}
		});
		
		putInvoker(RankCmd.TICKETRANK, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				ticketRank(session,req);
			}
		});
		
		putInvoker(RankCmd.TICKETRANKSELF, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				ticketRankSelf(session,req);
			}
		});
		
		putInvoker(RankCmd.LIKE, new Invoker() {
			
			public void invoke(IoSession session,Request req) {
				giveLike(session,req);
			}
		});
		
	}
	
	protected void bigRank(IoSession session,Request req){
		System.out.println("土豪排行榜");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
//		String nickName =json.getString("userId");
		
		List<BigRankBack> us = rankDao.getBigRank(id,50);
		
		HashMap<String, Comparable> map = new HashMap<String, Comparable>();
		int rseult = -1;
		if(us!=null){
			rseult = 1;
			JSONArray jsonArray = JSONArray.fromObject(us);
			map.put("rank", jsonArray);
			map.put("result", rseult);
			req.setBody(JSONObject.fromObject(map));
		}else{
			ErrorBack error  = new ErrorBack(0, ErrorCode.RENK);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		session.write(req);
		
	}
	
	protected void superRank(IoSession session,Request req){
		System.out.println("捕鱼达人排行榜");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
//		String nickName =json.getString("userId");
		
		List<SuperRankBack> us = rankDao.getSuperRank(id,50);
		
		HashMap<String, Comparable> map = new HashMap<String, Comparable>();
		int rseult = -1;
		if(us!=null){
			rseult = 1;
			JSONArray jsonArray = JSONArray.fromObject(us);
			map.put("rank", jsonArray);
			map.put("result", rseult);
			req.setBody(JSONObject.fromObject(map));
		}else{
			ErrorBack error  = new ErrorBack(0, ErrorCode.RENK);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		session.write(req);
	}
	
	protected void timeRank(IoSession session,Request req){
		System.out.println("在线时长排行榜");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
//		String nickName =json.getString("userId");
		
		List<TimeRankBack> us = rankDao.getTimeRank(id,50);
		
		HashMap<String, Comparable> map = new HashMap<String, Comparable>();
		int rseult = -1;
		if(us!=null){
			rseult = 1;
			JSONArray jsonArray = JSONArray.fromObject(us);
			map.put("rank", jsonArray);
			map.put("result", rseult);
			req.setBody(JSONObject.fromObject(map));
		}else{
			ErrorBack error  = new ErrorBack(0, ErrorCode.RENK);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		session.write(req);
	}
	
	protected void likedRank(IoSession session,Request req){
		System.out.println("每日之星排行榜");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
//		String nickName =json.getString("userId");
		
		List<LikedRankBack> us = rankDao.getLikedRank(id,50);
		
		HashMap<String, Comparable> map = new HashMap<String, Comparable>();
		int rseult = -1;
		if(us!=null){
			rseult = 1;
			JSONArray jsonArray = JSONArray.fromObject(us);
			map.put("rank", jsonArray);
			map.put("result", rseult);
			req.setBody(JSONObject.fromObject(map));
		}else{
			ErrorBack error  = new ErrorBack(0, ErrorCode.RENK);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		session.write(req);
	}
	
	
	protected void ticketRank(IoSession session,Request req){
		System.out.println("每日之星排行榜");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
//		String nickName =json.getString("userId");
		
		List<TicketRankBack> us = rankDao.getTicketRank(id,50);
		
		HashMap<String, Comparable> map = new HashMap<String, Comparable>();
		int rseult = -1;
		if(us!=null){
			rseult = 1;
			JSONArray jsonArray = JSONArray.fromObject(us);
			map.put("rank", jsonArray);
			map.put("result", rseult);
			req.setBody(JSONObject.fromObject(map));
		}else{
			ErrorBack error  = new ErrorBack(0, ErrorCode.RENK);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		
		
		session.write(req);
	}
	
	
	protected void bigRankSelf(IoSession session,Request req){
		System.out.println("土豪排行榜个人排名");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
//		String nickName =json.getString("userId");
		
	    if(FishWorld.getInstance().isExist(id)){
			
			FishWorld.getInstance().enterHall(session, id);
			
		}else{
			User u = useDao.load(id);
			FishWorld.getInstance().enterHall(session, u);
		}
        
       
		
		int result = rankDao.getBigRankSelf(id);
		if(result>0){
			User u = FishWorld.getInstance().getUser(id);
			HashMap<String, Comparable> map = new HashMap<String, Comparable>();
			map.put("result", (result>0)?1:0);
			BigRankBack rankself = new BigRankBack(u.getVip(), u.getId(), u.getNickname(), u.getChargeScore());
			rankself.setRank(result);
			map.put("bigRankself", JSONObject.fromObject(rankself));
			int count = RankManager.getInstance().getCount(id, RankCmd.BIGRANK);
			map.put("count", count);
			req.setBody(JSONObject.fromObject(map));
		}else{
			ErrorBack error  = new ErrorBack(0, ErrorCode.RANKSELF);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		
		session.write(req);
		
	}
	
	protected void superRankSelf(IoSession session,Request req){
		System.out.println("捕鱼达人排行榜个人排名");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
//		String nickName =json.getString("userId");
	   if(FishWorld.getInstance().isExist(id)){
			
			FishWorld.getInstance().enterHall(session, id);
			
		}else{
			User u = useDao.load(id);
			FishWorld.getInstance().enterHall(session, u);
		}
		
		int result = rankDao.getSuperRankSelf(id);
		if(result>0){
			User u = FishWorld.getInstance().getUser(id);
			HashMap<String, Comparable> map = new HashMap<String, Comparable>();
			map.put("result", (result>0)?1:0);
			SuperRankBack rankself = new SuperRankBack(u.getVip(), u.getId(), u.getNickname(), u.getFishScore());
			rankself.setRank(result);
			map.put("superRankself", JSONObject.fromObject(rankself));
			int count = RankManager.getInstance().getCount(id, RankCmd.SUPERRANK);
			map.put("count", count);
			req.setBody(JSONObject.fromObject(map));
		}else{
			ErrorBack error  = new ErrorBack(0, ErrorCode.RANKSELF);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		
		session.write(req);
	}
	
	protected void timeRankSelf(IoSession session,Request req){
		System.out.println("在线时长排行榜个人排名");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
//		String nickName =json.getString("userId");
		
	    if(FishWorld.getInstance().isExist(id)){
			
			FishWorld.getInstance().enterHall(session, id);
			
		}else{
			User u = useDao.load(id);
			FishWorld.getInstance().enterHall(session, u);
		}
	   
	   
		int result = rankDao.getTimeRankSelf(id);
		if(result>0){
			User u = FishWorld.getInstance().getUser(id);
			HashMap<String, Comparable> map = new HashMap<String, Comparable>();
			map.put("result", (result>0)?1:0);
			TimeRankBack rankself = new TimeRankBack(u.getVip(), u.getId(), u.getNickname(), u.getOnlineTimes());
			rankself.setRank(result);
			map.put("timeRankself", JSONObject.fromObject(rankself));
			int count = RankManager.getInstance().getCount(id, RankCmd.TIMERANK);
			map.put("count", count);
			req.setBody(JSONObject.fromObject(map));
		}else{
			ErrorBack error  = new ErrorBack(0, ErrorCode.RANKSELF);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		
		
		session.write(req);
	}
	
	protected void likedRankSelf(IoSession session,Request req){
		System.out.println("每日之星排行榜个人排名");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
//		String nickName =json.getString("userId");
	    if(FishWorld.getInstance().isExist(id)){
			
			FishWorld.getInstance().enterHall(session, id);
			
		}else{
			User u = useDao.load(id);
			FishWorld.getInstance().enterHall(session, u);
		}
		
		
		int result = rankDao.getLikedRankSelf(id);
		if(result>0){
			User u = FishWorld.getInstance().getUser(id);
			HashMap<String, Comparable> map = new HashMap<String, Comparable>();
			map.put("result", (result>0)?1:0);
			LikedRankBack rankself = new LikedRankBack(u.getVip(), u.getId(), u.getNickname(), u.getLiked());
			rankself.setRank(result);
			map.put("likedRankself", JSONObject.fromObject(rankself));
			int count = RankManager.getInstance().getCount(id, RankCmd.LIKEDRANK);
			map.put("count", count);
			req.setBody(JSONObject.fromObject(map));
		}else{
			ErrorBack error  = new ErrorBack(0, ErrorCode.RANKSELF);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		
		
		session.write(req);
	}
	
	
	protected void ticketRankSelf(IoSession session,Request req){
		System.out.println("奖券榜个人排名");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
//		String nickName =json.getString("userId");
	    if(FishWorld.getInstance().isExist(id)){
			
			FishWorld.getInstance().enterHall(session, id);
			
		}else{
			User u = useDao.load(id);
			FishWorld.getInstance().enterHall(session, u);
		}		
		
		int result = rankDao.getTicketRankSelf(id);
		if(result>0){
			User u = FishWorld.getInstance().getUser(id);
			HashMap<String, Comparable> map = new HashMap<String, Comparable>();
			map.put("result", (result>0)?1:0);
			TicketRankBack rankself = new TicketRankBack(u.getVip(), u.getId(), u.getNickname(), u.getTicket());
			rankself.setRank(result);
			map.put("ticketRankself", JSONObject.fromObject(rankself));
			int count = RankManager.getInstance().getCount(id, RankCmd.TICKETRANK);
			map.put("count", count);
			req.setBody(JSONObject.fromObject(map));
		}else{
			ErrorBack error  = new ErrorBack(0, ErrorCode.RANKSELF);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}
		
		
		session.write(req);
	}
	
	protected void giveLike(IoSession session,Request req){
		System.out.println("排行榜点赞");
		System.out.println(req.toString());
		JSONObject json = req.getBody();

		int id =json.getInt("id");
		int likedId =json.getInt("likedId");
		int type =json.getInt("type");
		
		if(RankManager.getInstance().checkFull(id, type)){
			ErrorBack error  = new ErrorBack(0, ErrorCode.LIKEDFULL);
			System.out.println(error);
			req.setBody(JSONObject.fromObject(error));
		}else{
			if(RankManager.getInstance().checkExist(id, likedId, type)){
				ErrorBack error  = new ErrorBack(0, ErrorCode.LIKEDEXIST);
				System.out.println(error);
				req.setBody(JSONObject.fromObject(error));
			}else{
				boolean result = rankDao.giveLike(id,likedId,type);
				if(result){
					RankManager.getInstance().addLiked(id, likedId, type);
					HashMap<String, Comparable> map = new HashMap<String, Comparable>();
					map.put("result", 1);
					map.put("type", type);
					
					req.setBody(JSONObject.fromObject(map));
				}else{
					ErrorBack error  = new ErrorBack(0, ErrorCode.LIKED);
					System.out.println(error);
					req.setBody(JSONObject.fromObject(error));
				}
			}
			
			
		}
		
		
		
		
		
		session.write(req);
	}
	

}
