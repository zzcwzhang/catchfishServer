/**
 * 
 */
package com.fuyun.server.socket.codec;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.fuyun.server.socket.msg.Request;

import net.sf.json.JSONObject;



/**
 * @author lushouzhi
 * 
 */
public class CodecDecoder extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		System.out.println("msg come");
		
		
		
		
		int start = in.position();
//		byte [] bys=in.array();
//		System.out.println();
//		for(int i=0;i<in.remaining();i++){
//			System.out.print(bys[i]+"\t");
//		}
//		System.out.println();
		if (in.remaining() > 6) { //如果剩余的长度大于6
			short length = in.getShort();
//			System.out.println("protobuf len:" + length);
//			System.out.println("protobuf len2:" + in.remaining());
			if (length < 0) {
				in.position(start);
				return false;
			} else if (in.remaining() < length - 2) {
				// System.out.println("no enough bytes");
				in.position(start);
				return false;
			}
			short moduleId = in.getShort();
			short cmdId = in.getShort();
			short bodyLen=in.getShort();
			byte[] bytes = new byte[length - 6];
//			if(cmdId == 100){
//				bytes = in.getHexDump().getBytes();
//			}else{
//				in.get(bytes);
//			}
			in.get(bytes);
			String body = new String(bytes, Charset.forName("utf-8"));
			System.out.println("body:"+body);
			//new JSONObject(str)
			JSONObject jsonObj=JSONObject.fromObject(body);
			
			Request req = Request.valueOf(moduleId, cmdId, jsonObj);
			if (req == null) {
				
				System.out.println("can not parse SocketMsg");
				return false;
			}
			System.out.println(req.toString());
			
			out.write(req);
		}
		return true;
	}
	
	
	

}
