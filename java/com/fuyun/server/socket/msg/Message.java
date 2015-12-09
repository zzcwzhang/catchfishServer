/**
 * 
 */
package com.fuyun.server.socket.msg;

import net.sf.json.JSONObject;



/**
 * 
 * @author lushouzhi
 *
 */
public class Message{
	protected short moduleId;
	protected short cmd;
	protected JSONObject body;
	public short getModuleId() {
		return moduleId;
	}
	public void setModuleId(short moduleId) {
		this.moduleId = moduleId;
	}
	public short getCmd() {
		return cmd;
	}
	public void setCmd(short cmd) {
		this.cmd = cmd;
	}
	public JSONObject getBody() {
		return body;
	}
	public void setBody(JSONObject body) {
		this.body = body;
	}
	
	public JSONObject getJsonBody(){
		if(body!=null){
			return JSONObject.fromObject(body);
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("{moduleId:").append(moduleId).append(",");
		sb.append("cmd:").append(cmd).append(",");
		sb.append("body:").append(body).append("}");
		return sb.toString();
	}
	
}
