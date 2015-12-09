/**
 * 
 */
package com.fuyun.server;


import com.fuyun.server.socket.SocketServerStart;
import com.fuyun.server.statistical.QuartzManager;

/**
 * 
 * 游戏世界
 * @author lushouzhi
 *
 */
public class GameWorld {
	
	/**
	 * 开启游戏世界
	 */
	public static void start(){
		//TODO 启动服务
		
		
		
		
		
		SocketServerStart socketServerStart=new SocketServerStart();
		socketServerStart.start();
	}
	
	
	/**
	 * 关闭游戏世界
	 */
	public static void shutdown(){
		
		System.out.println("关闭服务器");
		
		
		try {
			QuartzManager.getInstance().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
