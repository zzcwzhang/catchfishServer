package com.fuyun.server.db.dao;

import java.util.List;

import org.hibernate.Session;

import com.fuyun.server.db.model.User;


/**
 * 基本用户操作接口
 * 1，增add
 * 2，删delete
 * 3，改update
 * 4，查select
 * 5，判断 isExist
 * @author Administrator
 *
 */
public interface IUserDao {

	public boolean add(User user);
	public boolean update(User user);
	public boolean delete(int id);
	public User load(int id);
	public User load(int id,String userName);
	public List<User> list(String hql);
	public List<User> list(String hql,int count);
	public List<User> listSQL(String sql);
	public boolean isExist(int id);
	public boolean isExist(int id,String userName);
	
	public int getMaxId();
	
	
	
	
}
