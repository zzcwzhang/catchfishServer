/**
 * 
 */
package com.fuyun.server.socket.codec;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;


import com.fuyun.server.socket.AttributeKeys;
import com.fuyun.server.socket.Dispatcher;
import com.fuyun.server.socket.msg.Request;
import com.fuyun.server.world.FishWorld;



/**
 * @author lushouzhi
 *
 */
public class MsgHandler implements IoHandler{

	@Override
	public void exceptionCaught(IoSession session, Throwable throwable)
			throws Exception {
		//wll IoHandler 异常抛出
		System.out.println("wll IoHandler 异常抛出 exceptionCaught");
		throwable.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		System.out.println("wll IoHandler 收消息 session id = "+session.getId());
		//wll IoHandler 接收到的消息
		if(message instanceof Request){
			Request req=(Request)message;
			System.out.println("recv msg from client:"+req.toString());
			Dispatcher.getInstance().dispatch(session, req);
		}
	}

	@Override
	public void messageSent(IoSession session, Object object) throws Exception {
		
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("wll IoHandler Closed 关闭会话 sessionID = "+session.getId());
		int tag_closed = (Integer) session.getAttribute(
				AttributeKeys.KEY_TAG_CLOSED, 0);
		FishWorld.getInstance().leaveHall(session,tag_closed);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("wll IoHandler Created 创建会话");
		
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		//System.out.println("wll IoHandler Idle 会话状态切换");
		if(status == IdleStatus.BOTH_IDLE){
			
			//System.out.println("BOTH_IDLE");
		}else if (status == IdleStatus.READER_IDLE){
			//System.out.println("READER_IDLE");
		}else if(status == IdleStatus.WRITER_IDLE){
			//System.out.println("WRITER_IDLE");
		}
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("wll IoHandler sessionOpened 打开会话");
	}

	@Override
	public void inputClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("wll IoHandler inputClosed 输入关闭");
	}
}
