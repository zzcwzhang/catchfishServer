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
public interface Handler {
	/**
	 * 
	 * @param session
	 * @param request
	 */
	void dispatch(IoSession session,Request request);
}
