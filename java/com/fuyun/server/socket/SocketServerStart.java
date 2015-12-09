package com.fuyun.server.socket;

import java.util.HashMap;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

import com.fuyun.server.module.HeartCmd;
import com.fuyun.server.module.Module;
import com.fuyun.server.socket.codec.CodecFactory;
import com.fuyun.server.socket.codec.MsgHandler;
import com.fuyun.server.socket.msg.Request;
import com.fuyun.server.socket.msg.Response;

import net.sf.json.JSONObject;




public class SocketServerStart {
	
	/**
	 * 心跳频率  10秒一次 
	 */
	private static final int HEARTBEATRATE = 10;
	/**
	 * 超时3秒
	 */
	private static final int HEART_TIME_OUT = 3;

	/** 心跳包内容 */
	private static final Response HEARTMSG;
	/**
	 * 最大超时次数
	 */
	private static final int HEART_TIME_OUT_TIMES = 2;
	/**
	 * 最大超时时间(毫秒)
	 */
	private static final int MAX_TIME=30000;

	static {
		HEARTMSG = Response
				.defaultResponse((short)Module.HEART, (short) HeartCmd.HEART);
		
		HashMap<String, Comparable> map = new HashMap<String, Comparable>();
		map.put("", 1);
		  
		HEARTMSG.setBody(JSONObject.fromObject(map));
	}

	public void start() {
		SocketServer service = new SocketServer(new CodecFactory(),new MsgHandler());

		KeepAliveRequestTimeoutHandler heartBeatHandler = new KeepAliveRequestTimeoutHandlerImpl();
		KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();
		KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,IdleStatus.READER_IDLE,heartBeatHandler);

		heartBeat.setForwardEvent(true);
		heartBeat.setRequestInterval(HEARTBEATRATE);
		heartBeat.setRequestTimeout(HEART_TIME_OUT);
		try {
			service.start(heartBeat);
			System.out.println("socket启动成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class KeepAliveMessageFactoryImpl implements
			KeepAliveMessageFactory {

		@Override
		public boolean isRequest(IoSession session, Object message) {
			System.out.println("心跳 message isRequest");
			if (message instanceof Request) {
				Request req = (Request) message;
				if (req.getModuleId() == HEARTMSG.getModuleId() && req.getCmd() == HEARTMSG.getCmd()) {
					return false;
					
				}
				
				session.setAttribute(AttributeKeys.KEY_HEART_COUNT,0);
				System.out.println("重置.....空闲心跳次数");
			}
			
			return false;
		}

		@Override
		public boolean isResponse(IoSession session, Object message) {
			System.out.println("心跳 message isResponse");
			if (message instanceof Request) {
				Request req = (Request) message;
				if (req.getModuleId() == HEARTMSG.getModuleId() && req.getCmd() == HEARTMSG.getCmd()) {
					
					//重置超时时间
					session.setAttribute(AttributeKeys.KEY_HEART_BEAT_TIME,
							System.currentTimeMillis());
					int times = (Integer) session.getAttribute(
							AttributeKeys.KEY_HEART_TIME_OUT_TIMES, 0);
					if (times > 0) {
						session.setAttribute(
								AttributeKeys.KEY_HEART_TIME_OUT_TIMES, 0);
					}
					
					//设置心跳 持续多久
					int count = (Integer) session.getAttribute(
							AttributeKeys.KEY_HEART_COUNT, 0);
					
					if(count<=AttributeKeys.MAX_HEART_COUNT*2){
						session.setAttribute(AttributeKeys.KEY_HEART_COUNT,count + 1);
						System.out.println(".....空闲心跳次数【"+(count + 1)+"】");
					}else{
						
						session.close(true);
					}
					
					return true;
				}
			}
			
			
			return false;
		}

		@Override
		public Object getRequest(IoSession session) {
			System.out.println("心跳 message HEARTMSG");
			return HEARTMSG;
		}

		@Override
		public Object getResponse(IoSession session, Object request) {
			return null;
		}

	}

	private static class KeepAliveRequestTimeoutHandlerImpl implements
			KeepAliveRequestTimeoutHandler {
		@Override
		public void keepAliveRequestTimedOut(KeepAliveFilter filter,
				IoSession session) throws Exception {
			
			System.out.println("心跳 超时处理");
			int times = (Integer) session.getAttribute(
					AttributeKeys.KEY_HEART_TIME_OUT_TIMES, 0);
			session.setAttribute(AttributeKeys.KEY_HEART_TIME_OUT_TIMES,times + 1);
			if (times < HEART_TIME_OUT_TIMES) {
				System.out.println("["
						+ session.getRemoteAddress().toString() + "]心跳响应超时["
						+ (times + 1) + "]次...");
				return;
			}
			long lastResponse = (Long) session.getAttribute(
					AttributeKeys.KEY_HEART_BEAT_TIME, 0L);
			if (System.currentTimeMillis() - lastResponse >= MAX_TIME) {
				System.out.println("["
						+ session.getRemoteAddress().toString()
						+ "]心跳响应超时被踢...");
				//SessionManager.playerLeave(session);
				session.close(true);
			}
			
		}

	}
}
