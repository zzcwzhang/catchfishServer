/**
 * 
 */
package com.fuyun.server.reward;

import java.util.HashMap;

import com.fuyun.server.db.dto.ErrorBack;
import com.fuyun.server.db.model.User;
import com.fuyun.server.handler.user.UserCmd;
import com.fuyun.server.module.ErrorCode;
import com.fuyun.server.module.Module;
import com.fuyun.server.socket.msg.Request;
import com.fuyun.server.world.FishWorld;

import net.sf.json.JSONObject;

/**
 * @author lushouzhi
 *
 */
public class SignReward {
	
	
	public SignReward() {
		super();
	}

	private Request req = null;
	
	private static SignReward instance = null;
	
	public static SignReward getInstance(){
		if(instance == null){
			instance = new SignReward();
		}
		return instance;
	}
	
	
	public void setReward(int id ,int type){
		
		if(req == null){
			req = new Request();
			req.setModuleId((short)Module.USER);
			req.setCmd((short)UserCmd.FISH_SIGN);
			req.setBody(null);
		}
		
		User u = FishWorld.getInstance().getUser(id);
		switch(type){
			case SignType.FAILED:
				failed(u);
				break;
			case SignType.SIGNED:
				signed(u,type);
				toClient(u,type);
				break;
			case SignType.NORMAL:
				normal(u,type);
				toClient(u,type);
				break;
			case SignType.SEQ2:
				signGift2(u,type);
				toClient(u,type);
				break;
			case SignType.SEQ3:
				signGift3(u,type);
				toClient(u,type);
				break;
			case SignType.SEQ4:
				signGift4(u,type);
				toClient(u,type);
				break;
			case SignType.SEQ5:
				signGift5(u,type);
				toClient(u,type);
				break;
			case SignType.SEQ6:
				signGift6(u,type);
				toClient(u,type);
				break;
			case SignType.SEQ7:
				signGift7(u,type);
				toClient(u,type);
				break;
			case SignType.SEQ15:
				signGift15(u,type);
				toClient(u,type);
				break;
			case SignType.SEQ25:
				signGift25(u,type);
				toClient(u,type);
				break;
		}
	}
	
	
	public void toClient(User u,int type){
		HashMap<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("result", 1);
		map.put("sign", type);
		req.setBody(JSONObject.fromObject(map));
		u.mgetSession().write(req);
	}
	
	
	public  void failed(User u){
		System.out.println("签到失败");
		ErrorBack error  = new ErrorBack(0, ErrorCode.SAMENICKNAME);
		System.out.println(error);
		req.setBody(JSONObject.fromObject(error));
		u.mgetSession().write(req);
		
	}
	
	public  void signed(User u,int type){
		System.out.println("今天已签过");
		
	}
	
	public  void normal(User u,int type){
		System.out.println("签到成功  但无 奖励");
	}
	
	
	public  void signGift2(User u,int type){
		System.out.println("连续签到2天 奖励");
	}
	
	public  void signGift3(User u,int type){
		System.out.println("连续签到3天 奖励");
	}
	
	public  void signGift4(User u,int type){
		System.out.println("连续签到4天 奖励");
	}
	
	public  void signGift5(User u,int type){
		System.out.println("连续签到5天 奖励");
	}
	
	public  void signGift6(User u,int type){
		System.out.println("连续签到6天 奖励");
	}
	
	public  void signGift7(User u,int type){
		System.out.println("连续签到7天 奖励");
	}
	
	public  void signGift15(User u,int type){
		System.out.println("签到15天 奖励");
	}
	
	public  void signGift25(User u,int type){
		System.out.println("连续签到25天 奖励");
	}
}
