package com.fuyun.server.socket;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

import com.fuyun.server.socket.msg.Message;


public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
	private static final short HEART_BEAT_MODULE_ID = -100;
	private static final short HEART_BEAT_CMD_ID = -99;
	/** 心跳包内容 */
	private static final String HEARTBEATREQUEST = "0x11";
	private static final String HEARTBEATRESPONSE = "0x12";
	private static Message heart_beat_request;// 心跳包
	static {
		// 构建心跳请求包 等待客户端应答
		heart_beat_request = new Message();
		heart_beat_request.setModuleId(HEART_BEAT_MODULE_ID);
		heart_beat_request.setCmd(HEART_BEAT_CMD_ID);
	}

	@Override
	public boolean isRequest(IoSession session, Object message) {
		// LOG.info("请求心跳包信息: " + message);
		if (message.equals(HEARTBEATREQUEST))
			return true;
		return false;
	}

	@Override
	public boolean isResponse(IoSession session, Object message) {
		// LOG.info("响应心跳包信息: " + message);
		// if(message.equals(HEARTBEATRESPONSE))
		// return true;
		return false;
	}

	@Override
	public Object getRequest(IoSession session) {
		// LOG.info("请求预设信息: " + HEARTBEATREQUEST);
		/** 返回预设语句 */
		return HEARTBEATREQUEST;
	}

	@Override
	public Object getResponse(IoSession session, Object request) {
		// LOG.info("响应预设信息: " + HEARTBEATRESPONSE);
		/** 返回预设语句 */
		return HEARTBEATRESPONSE;
		// return null;
	}

}

/**
 * 对应上面的注释
 * KeepAliveFilter(heartBeatFactory,IdleStatus.BOTH_IDLE,heartBeatHandler)
 * 心跳超时处理 KeepAliveFilter 在没有收到心跳消息的响应时，会报告给的KeepAliveRequestTimeoutHandler。
 * 默认的处理是 KeepAliveRequestTimeoutHandler.CLOSE
 * （即如果不给handler参数，则会使用默认的从而Close这个Session）
 * 
 * @author cruise
 * 
 */
class KeepAliveRequestTimeoutHandlerImpl implements
		KeepAliveRequestTimeoutHandler {

	@Override
	public void keepAliveRequestTimedOut(KeepAliveFilter filter,IoSession session) throws Exception {
		
	}
}
