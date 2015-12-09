/**
 * 
 */
package com.fuyun.server.socket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;

import com.fuyun.server.socket.msg.Request;


/**
 * @author lushouzhi
 * 单例模式  分发器 
 */
public final class Dispatcher {
	//字典映射 模块id 模块对应的具体处理  一一对应
	private final Map<Integer, Handler> DISPATCHER=new ConcurrentHashMap<Integer, Handler>(); 
	private static Dispatcher instance=new Dispatcher();
	public static Dispatcher getInstance(){
		return instance;
	}
	
	public void dispatch(IoSession session,Request req){
		int moduleId=req.getModuleId();
		Handler handler=getHandler(moduleId);
		if(handler==null){
//			GameLog.infoConsole("can't find a handler for module_id:"+moduleId);
		}else{
			handler.dispatch(session, req);
		}
	}
	
	public void put(int moduleId,Handler handler){
		if(DISPATCHER.containsKey(moduleId)){
//			GameLog.infoConsole("DISPATCHER has already exsited module_id:"+moduleId);
		}
		DISPATCHER.put(moduleId, handler);
		
	}
	
	public Handler getHandler(int moduleId){
		return DISPATCHER.get(moduleId);
	}
}
