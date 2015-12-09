package com.fuyun.server.module;

public interface ErrorCode {

	int UNKNOWN = -1;//服务器繁忙 未知错误
	int USEREXIST = 0;//用户名已存在
	int HAVESIGN = 1;//已签到
	int USERNOTEXIST = 2;//用户名不存在
	int PSDERR = 3;//密码错误
	
	int SAMENICKNAME = 10;//昵称已存在
	int RANDOMNICKNAME = 11;//随机昵称失败
	int SIGNERR = 13; //签到失败
	int SAVEERR = 34;//上传数据失败
	
	int LIKED = 14; //点赞失败
	int RENK = 15; //获取排行榜失败
	int RANKSELF = 16;//获取个人排行榜失败
	int LIKEDFULL = 17;//今日点赞次数已用完
	int LIKEDEXIST = 18;//此用户已点赞过
	
	//mail
	int HAVEATM = 30;//附件已领取
	int ATMALL = 31;//附件已全部领取
	int LOADMAIL = 32;//加载邮件失败
	int DELETEMAIL = 33;//删除邮件失败
	
}
