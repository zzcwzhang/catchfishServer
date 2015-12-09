/**
 * 
 */
package com.fuyun.server.socket;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.fuyun.server.socket.msg.Request;
import com.fuyun.server.socket.msg.Response;


/**
 * @author lushouzhi
 *
 */
public abstract class BaseHandler implements Handler{
	//
	protected final Map<Integer, Invoker> CMD_INVOKERS=new HashMap<Integer, Invoker>();
	protected Request request;
	protected BaseHandler(){
		
	}
	
	//CMD_INVOKERS 与 DISPATCHER 已模块id 建立关联
	public void initHandler(){
		Dispatcher.getInstance().put(getModule(), this);
		inititialize();
	}
	
	public abstract int getModule();
	
	public abstract void inititialize();
	
	public void putInvoker(int cmd,Invoker invoker){
		if(CMD_INVOKERS.containsKey(cmd)){
//			GameLog.infoConsole("CMD_INVOKERS have already exsited cmd:["+cmd+"] in module ["+getModule()+"]");
		}else{ 
			CMD_INVOKERS.put(cmd, invoker);
		}
	}
	
	private Invoker getInvoker(int cmd){
		return CMD_INVOKERS.get(cmd);
	}
	/**
	 * 请求包发送至INVOKER层
	 * @param session
	 * @param req
	 */
	public void dispatch(IoSession session,Request req){
		short cmd=req.getCmd();
		Invoker invoker=getInvoker(cmd);
		if(invoker==null){
			System.err.println("can not find invoker for cmd:"+cmd);
		}else{
			Response resp=new Response();
			resp.setModuleId(req.getModuleId());
			resp.setCmd(req.getCmd());
			try {
				invoker.invoke(session, req);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
