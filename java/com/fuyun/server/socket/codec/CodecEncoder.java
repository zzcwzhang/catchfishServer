/**
 * 
 */
package com.fuyun.server.socket.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.fuyun.server.socket.msg.Message;



/**
 * @author lushouzhi
 *
 */
public class CodecEncoder implements ProtocolEncoder{

	public void dispose(IoSession session) throws Exception {
		
	}

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
			throws Exception {
		if(message instanceof Message){
			Message msg=(Message)message;
			System.out.println("send msg to client:"+msg.toString());
			out.write(doEncode(msg));
			out.flush();
		}else if(message instanceof IoBuffer){
			System.out.println("send IoBuffer to client:"+message.toString());
			out.write(message);
			out.flush();
		}else{
//			Log.e("can not encode message");
		}
	
	}

	
	public static IoBuffer doEncode(Message message){
		short len=8;
		byte []bytes=null;
		if(message.getBody()!=null){
			bytes=message.getBody().toString().getBytes(Charset.forName("utf-8"));
			len+=bytes.length;
		}
		IoBuffer buf=IoBuffer.allocate(len);
		buf.putShort(len);
		buf.putShort(message.getModuleId());
		buf.putShort(message.getCmd());
		if(bytes !=null){
			buf.putShort((short) bytes.length);
			buf.put(bytes);
		}
		buf.flip();//网络大小头
		return buf;
	}
}
