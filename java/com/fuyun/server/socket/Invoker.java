/**
 * 
 */
package com.fuyun.server.socket;

import org.apache.mina.core.session.IoSession;

import com.fuyun.server.socket.msg.Request;



/**
 * @author lushouzhi
 *
 */
public interface Invoker {
	/**
	 * 
	 * @param session
	 * @param request
	 * @param response
	 */
	public void invoke(IoSession session,Request request);
}
