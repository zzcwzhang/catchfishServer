/**
 * 
 */
package com.fuyun.server.socket.msg;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.mina.core.buffer.IoBuffer;


/**
 * 1.下发大厅数据
 * 2.下发好友数据
 * 3.下发任务数据
 * 4.下发桌子信息
 * 5.通知玩家进入信息
 * 6.广播玩家下注数据
 * 7.广播玩家看牌信息
 * 8.广播玩家开牌信息
 * 9.广播玩家离开信息
 * 
 * 
 * @author lushouzhi
 *
 */
public class Response extends Message{
	public IoBuffer toClient(){
		byte []bodyByte = null;
		if(body!=null){
			try {
				bodyByte=body.toString().getBytes("utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		IoBuffer buf = null;
		short bodyLen=0;
		if(bodyByte!=null){
			bodyLen=(short) bodyByte.length;
		}
		buf=IoBuffer.allocate(bodyLen+6);
		buf.putShort((short) (bodyByte.length+6));
		buf.putShort(moduleId);
		buf.putShort(cmd);
		buf.put(bodyByte);
		buf.flip();
		return buf;
	}
	
	
	public static Response defaultResponse(short moduleId,short cmdId){
		Response response=new Response();
		response.setModuleId(moduleId);
		response.setCmd(cmdId);
		return response;
	}
	
	public void addBody(String key,Object value){
		if(body==null){
			body=new JSONObject();
		}
		body.put(key, value);
	}
	
	public void addBodyAll(Map<String,Object> map){
		if(body==null){
			body=new JSONObject();
		}
		body.putAll(map);
	}
}
