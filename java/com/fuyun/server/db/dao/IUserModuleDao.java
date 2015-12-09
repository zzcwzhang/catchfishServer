package com.fuyun.server.db.dao;

import com.fuyun.server.db.model.User;

public interface IUserModuleDao {

	//注册
	public int register(User user);
    //登录
	public User login(int id,String userName);
	public User login(String userName,String password);
	//签到
	public void sign(int id);
	//上传数据
	public int save(User user);
	
	//压力测试
	
	//登录
    public User login_yali(int id);
    
    
    public boolean isSame(String nickname);
    
    public String randomNickname();
    
    public void SignBin(int id);
    
    //
    public boolean checkUserName(String userNmae);
}
