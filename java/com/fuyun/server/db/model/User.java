package com.fuyun.server.db.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.mina.core.session.IoSession;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 用户表
 * 
 * 用户基本信息
 * 	表（id）int
 * 	用户唯一标示（name）string
 * 	用户昵称（nickname）string
 * 	用户密码（password）string
 * 
 * 游戏数据
 * 	等级（level）byte
 * 	Vip等级（vip）byte
 * 	金币（coin）int
 * 	充值积分（chargeScore）int
 * 	鱼分（fishScore）int
 * 	获赞次数（liked）int
 * 	在线时长单位为秒（onlineTimes）int
 * 	定屏  (propPause)  int
 * 	极速（propTopspeed）int //导弹
 * 	狂暴（propRage）int
 * 	锁定（propLock）int
 * 	10000炮（propThousand）int // 能量炮
 * 
 * 奖券 int ticket
 * 签到数据
 * 	签到日期（signDate）Date
 * 	月累计签到(signAll) byte
 * 	月连续签到（signSequence）byte
 * 	月连续签到完成标记（signInSeq）byte
 * 
 * 设置昵称标记 nicknameSign byte
 * 
 * 
 *新增数据
 * int gameCheck;//0 关卡
 * int playerRank;//0 军衔
   int cannonInf;	//炮台信息，8位  1
   int goldRate;//倍率 0
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name="user_fish")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {

	
	private int id;
	private String name;
	private String nickname;
	private String password;
	
	//游戏数据
	
	//等级标示
	private byte vip;
	private byte level;
	private byte sex;
	
	
	//道具数据
	private int coin;
	private int propPause;
	private int propTopspeed;
	private int propRage;
	private int propLock;
	private int propThousand;
	
	//排行榜数据
	private int chargeScore;
	private int fishScore;
	private int liked;
	private int onlineTimes;
	private int ticket;
	
	
	//签到数据
	private int signMask;
	private int signMonth;
	
	//设置昵称标记 nicknameSign byte
	private byte nicknameSign;
	
	//新增数据
	 //int gameCheck;//0 关卡
	 //int playerRank;//0 军衔
	 //char cannonInf;	//炮台信息，8位  1
	 //int goldRate;//倍率 0
	private int gameCheck;
	private int playerRank;
	private int cannonInf;
	private int goldRate;
	
	//mina
	private IoSession session;
	
	
	public IoSession mgetSession() {
		return session;
	}

	public void msetSession(IoSession session) {
		this.session = session;
	}



	public User() {
		super();
	}
	
	
	
	@Column(name="gameCheck",columnDefinition = "int(2) NOT NULL DEFAULT 0 COMMENT '军衔'")
	public int getGameCheck() {
		return gameCheck;
	}

	public void setGameCheck(int gameCheck) {
		this.gameCheck = gameCheck;
	}
	@Column(name="playerRank",columnDefinition = "int(2) NOT NULL DEFAULT 0 COMMENT '关卡'")
	public int getPlayerRank() {
		return playerRank;
	}

	public void setPlayerRank(int playerRank) {
		this.playerRank = playerRank;
	}
	@Column(name="cannonInf",columnDefinition = "int(2) NOT NULL DEFAULT 1 COMMENT '炮台信息'")
	public int getCannonInf() {
		return cannonInf;
	}

	public void setCannonInf(int cannonInf) {
		this.cannonInf = cannonInf;
	}
	@Column(name="goldRate",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '倍率'")
	public int getGoldRate() {
		return goldRate;
	}

	public void setGoldRate(int goldRate) {
		this.goldRate = goldRate;
	}

	@Column(name="nicknameSign",columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '昵称标记'")
	public byte getNicknameSign() {
		return nicknameSign;
	}

	public void setNicknameSign(byte nicknameSign) {
		this.nicknameSign = nicknameSign;
	}

	public User(String name) {
		this(name,"123456");
	}
	
	public User(String name, byte sex) {
		this(name);
		this.sex = sex;
	}


	public User(String name, String password) {
		super();
		this.name = name;
		this.nickname = name;
		this.password = password;
		this.nicknameSign = -1;
		this.cannonInf = 1;
		
	}


	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="sex",columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '性别 0男1女'")
	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}
	
	@Column(name="nickname",columnDefinition = "char(20) NOT  NULL UNIQUE COMMENT '昵称 不重复'")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name="username",columnDefinition = "char(20) NOT  NULL UNIQUE  COMMENT '用户唯一标示'")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="password",columnDefinition = "char(20) NOT NULL DEFAULT '123456' COMMENT '密码'")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="vip",columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT 'VIP等级'")
	public byte getVip() {
		return vip;
	}
	public void setVip(byte vip) {
		this.vip = vip;
	}
	
	@Column(name="chargeScore",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '充值积分'")
	public int getChargeScore() {
		return chargeScore;
	}
	public void setChargeScore(int chargeScore) {
		this.chargeScore = chargeScore;
	}
	@Column(name="fishScore",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '鱼分'")
	public int getFishScore() {
		return fishScore;
	}
	public void setFishScore(int fishScore) {
		this.fishScore = fishScore;
	}
	@Column(name="liked",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '被赞次数'")
	public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	@Column(name="onlineTimes",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '今日在线时长'")
	public int getOnlineTimes() {
		return onlineTimes;
	}
	public void setOnlineTimes(int onlineTimes) {
		this.onlineTimes = onlineTimes;
	}
	
	
	
	@Column(name="level",columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '等级'")
	public byte getLevel() {
		return level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}
	@Column(name="coin",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '金币'")
	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}
	@Column(name="propPause",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '定屏'")
	public int getPropPause() {
		return propPause;
	}

	public void setPropPause(int propPause) {
		this.propPause = propPause;
	}
	@Column(name="propTopspeed",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '极速'")
	public int getPropTopspeed() {
		return propTopspeed;
	}

	public void setPropTopspeed(int propTopspeed) {
		this.propTopspeed = propTopspeed;
	}
	@Column(name="propRage",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '狂暴'")
	public int getPropRage() {
		return propRage;
	}

	public void setPropRage(int propRage) {
		this.propRage = propRage;
	}
	@Column(name="propLock",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '锁定'")
	public int getPropLock() {
		return propLock;
	}

	public void setPropLock(int propLock) {
		this.propLock = propLock;
	}
	@Column(name="propThousand",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '10000炮'")
	public int getPropThousand() {
		return propThousand;
	}

	public void setPropThousand(int propThousand) {
		this.propThousand = propThousand;
	}
	@Column(name="ticket",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '奖券'")
	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	
	
	
	
	@Column(name="signMask",columnDefinition = "int(11) NOT NULL DEFAULT 0 COMMENT '月签到日期标记'")
	public int getSignMask() {
		return signMask;
	}

	public void setSignMask(int signMask) {
		this.signMask = signMask;
	}

	@Column(name="signMonth",columnDefinition = "int(2) NOT NULL DEFAULT 0 COMMENT '签到月份'")
	public int getSignMonth() {
		return signMonth;
	}

	public void setSignMonth(int signMonth) {
		this.signMonth = signMonth;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", nickname=" + nickname + ", password=" + password + ", vip="
				+ vip + ", level=" + level + ", sex=" + sex + ", coin=" + coin + ", propPause=" + propPause
				+ ", propTopspeed=" + propTopspeed + ", propRage=" + propRage + ", propLock=" + propLock
				+ ", propThousand=" + propThousand + ", chargeScore=" + chargeScore + ", fishScore=" + fishScore
				+ ", liked=" + liked + ", onlineTimes=" + onlineTimes + ", ticket=" + ticket + ", signMask=" + signMask
				+ ", signMonth=" + signMonth + ", session=" + session + "]";
	}

	
	
	
	
	
	
}
