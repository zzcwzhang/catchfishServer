package com.fuyun.server;
import com.fuyun.server.GameWorld;
import com.fuyun.server.db.DBManager;
import com.fuyun.server.statistical.QuartzManager;

/**
 * 
 */




/**
 * @author lushouzhi
 *
 */
public class Bootstrap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//初始化数据库连接
		DBManager.getInstance().init();
		//启动游戏世界
		GameWorld.start();
		//添加钩子
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				//关闭游戏世界
				GameWorld.shutdown();
			}
		}));
		
		//System.out.println("test");
//		try {
//			QuartzManager.getInstance().run();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("定时任务启动异常");
//			e.printStackTrace();
//		}
	}
}
