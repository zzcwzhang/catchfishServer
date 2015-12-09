/**
 * 
 */
package com.fuyun.server.socket.msg;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 1.大厅数据 
 * 2.快速进入
 * 3.下注
 * 4.看牌
 * 5.跟注
 * 6.开牌
 * 7.离开桌子
 * 8.任务领取
 * 9.添加好友
 * 
 * 客户端请求类
 * @author lushouzhi
 *
 */
public class Request extends Message{
	
	
	public static Request valueOf(short moduleId,short cmdId,JSONObject body){
		Request request=new Request();
		request.setModuleId(moduleId);
		request.setCmd(cmdId);
		request.setBody(body);
		return request;
	}
	
	public static Request valueOf(short moduleId,short cmdId,String body){
		return valueOf(moduleId, cmdId, JSONObject.fromObject(body));
	}
	
	public int getValueInt(String key){
		return body.getInt(key);
	}
	
	public String getValueString(String key){
		return body.getString(key);
	}
	
	public Double getValueDouble(String key){
		return body.getDouble(key);
	}
	
	public long getValueLong(String key){
		return body.getLong(key);
	}
	
	public JSONArray getValueJSONArray(String key){
		return body.getJSONArray(key);
	}
	
	public JSONObject getValueJSONObject(String key){
		return body.getJSONObject(key);
	}
	
	public boolean isBodyNull(){
		return body==null;
	}
}
