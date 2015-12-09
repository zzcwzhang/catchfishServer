package com.fuyun.server.module.rank;

import java.util.HashSet;
import java.util.Set;
/**
 * 点赞记录
 * 1，谁
 * 2，类型
 * 3，点赞次数
 * 4，被点赞的id集合
 * @author Administrator
 *
 */
public class RankLiked {

	private int id;
	private int type;
	private Set<Integer> likedIds;
	
	
	
	public RankLiked() {
		super();
	}

	public RankLiked(int id, int type) {
		super();
		this.id = id;
		this.type = type;
		if(likedIds == null){
			likedIds = new HashSet<Integer>();
		}
	}

	public void addLike(int id){
		likedIds.add(id);
	}
	
	public int size(){
		return likedIds.size();
	}
	
	public int left(){
		return 5 - likedIds.size();
	}
	
	public boolean checkFull(){
		int count = likedIds.size();
		return (count>=5) ? true:false;
	}
	
	public boolean checkExist(int id){
		return likedIds.contains(id);
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	public Set<Integer> getLikedIds() {
		return likedIds;
	}

	public void setLikedIds(Set<Integer> likedIds) {
		this.likedIds = likedIds;
	}

	@Override
	public String toString() {
		return "RankLiked [id=" + id + ", type=" + type + ", likedIds=" + likedIds + "]";
	}

	
	
	
}
