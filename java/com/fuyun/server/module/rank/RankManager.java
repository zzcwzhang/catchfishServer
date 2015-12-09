package com.fuyun.server.module.rank;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fuyun.server.handler.rank.RankCmd;


public class RankManager {

	private static RankManager instance = null;
	
	private Map<Integer, RankLiked> RANK_LIKED; // id Liked
	private Map<Integer, RankLiked> RANK_BIG; // id big
	private Map<Integer, RankLiked> RANK_SUPER; // id super
	private Map<Integer, RankLiked> RANK_TIME; // id time
	private Map<Integer, RankLiked> RANK_TICKET; // id ticket
	
	
	public static RankManager getInstance(){
		if(instance == null){
			instance = new RankManager();
			instance.init();
		}
		return instance;
	}
	
	
	private void init(){
		if(RANK_LIKED == null){
			RANK_LIKED = new ConcurrentHashMap<Integer, RankLiked>();
		}
		
		if(RANK_BIG == null){
			RANK_BIG = new ConcurrentHashMap<Integer, RankLiked>();
		}
		
		if(RANK_SUPER == null){
			RANK_SUPER = new ConcurrentHashMap<Integer, RankLiked>();
		}
		
		if(RANK_TIME == null){
			RANK_TIME = new ConcurrentHashMap<Integer, RankLiked>();
		}
		
		if(RANK_TICKET == null){
			RANK_TICKET = new ConcurrentHashMap<Integer, RankLiked>();
		}
	}
	
	
	
	public void addLiked(int id,int likedId,int type){
		
		if(type == RankCmd.BIGRANK){
			if(RANK_BIG.containsKey(id)){
				RankLiked rank = RANK_BIG.get(id);
				rank.addLike(likedId);
			}else{
				RankLiked newRank = new RankLiked(id,type);
				newRank.addLike(likedId);
				RANK_BIG.put(id, newRank);
			}
			
		}else if(type == RankCmd.SUPERRANK){
			if(RANK_SUPER.containsKey(id)){
				RankLiked rank = RANK_SUPER.get(id);
				rank.addLike(likedId);
			}else{
				RankLiked newRank = new RankLiked(id,type);
				newRank.addLike(likedId);
				RANK_SUPER.put(id, newRank);
			}
			
		}else if(type == RankCmd.LIKEDRANK){
			if(RANK_LIKED.containsKey(id)){
				RankLiked rank = RANK_LIKED.get(id);
				rank.addLike(likedId);
			}else{
				RankLiked newRank = new RankLiked(id,type);
				newRank.addLike(likedId);
				RANK_LIKED.put(id, newRank);
			}
			
		}else if(type == RankCmd.TIMERANK){
			if(RANK_TIME.containsKey(id)){
				RankLiked rank = RANK_TIME.get(id);
				rank.addLike(likedId);
			}else{
				RankLiked newRank = new RankLiked(id,type);
				newRank.addLike(likedId);
				RANK_TIME.put(id, newRank);
			}
			
		}else if(type == RankCmd.TICKETRANK){
			if(RANK_TICKET.containsKey(id)){
				RankLiked rank = RANK_TICKET.get(id);
				rank.addLike(likedId);
			}else{
				RankLiked newRank = new RankLiked(id,type);
				newRank.addLike(likedId);
				RANK_TICKET.put(id, newRank);
			}
		}
		
		
	}

	public Map<Integer, RankLiked> getRANK_LIKED() {
		return RANK_LIKED;
	}
	
	
	
	
	public Map<Integer, RankLiked> getRANK_BIG() {
		return RANK_BIG;
	}


	public Map<Integer, RankLiked> getRANK_SUPER() {
		return RANK_SUPER;
	}


	public Map<Integer, RankLiked> getRANK_TIME() {
		return RANK_TIME;
	}


	public Map<Integer, RankLiked> getRANK_TICKET() {
		return RANK_TICKET;
	}


	public boolean checkFull(int id,int type){
		
		if(type == RankCmd.BIGRANK){
			if(RANK_BIG.containsKey(id)){
				RankLiked rank = RANK_BIG.get(id);
				return rank.checkFull();
			}
			
		}else if(type == RankCmd.SUPERRANK){
			if(RANK_SUPER.containsKey(id)){
				RankLiked rank = RANK_SUPER.get(id);
				return rank.checkFull();
			}
			
		}else if(type == RankCmd.LIKEDRANK){
			if(RANK_LIKED.containsKey(id)){
				RankLiked rank = RANK_LIKED.get(id);
				return rank.checkFull();
			}
			
		}else if(type == RankCmd.TIMERANK){
			if(RANK_TIME.containsKey(id)){
				RankLiked rank = RANK_TIME.get(id);
				return rank.checkFull();
			}
		}else if(type == RankCmd.TICKETRANK){
			if(RANK_TICKET.containsKey(id)){
				RankLiked rank = RANK_TICKET.get(id);
				return rank.checkFull();
			}
		}
		
		return false;
	}
	
	public boolean checkExist(int id,int likedId,int type){
		
		if(type == RankCmd.BIGRANK){
			if(RANK_BIG.containsKey(id)){
				RankLiked rank = RANK_BIG.get(id);
				return rank.checkExist(likedId);
			}
			
		}else if(type == RankCmd.SUPERRANK){
			if(RANK_SUPER.containsKey(id)){
				RankLiked rank = RANK_SUPER.get(id);
				return rank.checkExist(likedId);
			}
			
		}else if(type == RankCmd.LIKEDRANK){
			if(RANK_LIKED.containsKey(id)){
				RankLiked rank = RANK_LIKED.get(id);
				return rank.checkExist(likedId);
			}
			
		}else if(type == RankCmd.TIMERANK){
			
			if(RANK_TIME.containsKey(id)){
				RankLiked rank = RANK_TIME.get(id);
				return rank.checkExist(likedId);
			}
			
		}else if(type == RankCmd.TICKETRANK){
			if(RANK_TICKET.containsKey(id)){
				RankLiked rank = RANK_TICKET.get(id);
				return rank.checkExist(likedId);
			}
		}
		
		return false;
	}
	
	
	public int getCount(int id ,int type){
		int result = 5;
		
		if(type == RankCmd.BIGRANK){
			if(RANK_BIG.containsKey(id)){
				RankLiked rank = RANK_BIG.get(id);
				return rank.left();
			}
			
		}else if(type == RankCmd.SUPERRANK){
			if(RANK_SUPER.containsKey(id)){
				RankLiked rank = RANK_SUPER.get(id);
				return rank.left();
			}
			
		}else if(type == RankCmd.LIKEDRANK){
			if(RANK_LIKED.containsKey(id)){
				RankLiked rank = RANK_LIKED.get(id);
				return rank.left();
			}
			
		}else if(type == RankCmd.TIMERANK){
			
			if(RANK_TIME.containsKey(id)){
				RankLiked rank = RANK_TIME.get(id);
				return rank.left();
			}
			
		}else if(type == RankCmd.TICKETRANK){
			if(RANK_TICKET.containsKey(id)){
				RankLiked rank = RANK_TICKET.get(id);
				return rank.left();
			}
		}
		
		return result;
	}
	
}
