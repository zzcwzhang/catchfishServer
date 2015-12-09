/**
 * 
 */
package com.fuyun.server.socket;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author lushouzhi
 * 
 */
public class ServerConfig {
	public static int serverId = 1;

	public static int worldQueueSize = 5;

	public static int commonQueueSize = 5;

	public static int socketBothIdle = 50;

	public static int workerPoolMin = 8;

	public static int workerPoolMax = 64;

	public static int socketPort = 9999;

	public static int readBufferSize = 2048;

	public static int receiveBufferSize = 40960;

	public static int writeBufferSize = 4096;

	public static boolean tcpNodelay = false;

	public static int workerPoolIdle = 300;

	public static int workerPoolQueued = 2147483647;

	public static int socketWriteTimeout = 60;

	public static int serverMaxBacklog = 5000;

	public static int serverNioProcess = 16;

	public static void load(String filePath) {
		Properties properties = new Properties();
		try {
			File file = new File(filePath);
			properties.load(new FileInputStream(file));

			for (Field f : ServerConfig.class.getDeclaredFields()) {
				String str = properties.getProperty(f.getName());
				if (str == null) {
					continue;
				}
				if (f.getType() == int.class) {
					f.set(null, Integer.parseInt(properties.getProperty(
							f.getName(), f.get(null).toString())));
				} else if (f.getType() == long.class) {
					f.set(null, Long.parseLong(properties.getProperty(
							f.getName(), f.get(null).toString())));
				} else if (f.getType() == boolean.class) {
					f.set(null,
							Boolean.parseBoolean(properties.getProperty(
									f.getName(), f.get(null).toString())));
				} else if (f.getType() == String.class) {
					f.set(null, properties.getProperty(f.getName(), f.get(null)
							.toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getSocketPort() {
		return socketPort;
	}

	public static int getReadBufferSize() {
		return readBufferSize;
	}

	public static int getReceiveBufferSize() {
		return receiveBufferSize;
	}

	public static int getWriteBufferSize() {
		return writeBufferSize;
	}

	public static int getWorkerPoolMin() {
		return workerPoolMin;
	}

	public static int getWorkerPoolMax() {
		return workerPoolMax;
	}

	public static int getSocketWriteTimeout() {
		return socketWriteTimeout;
	}

	public static int getWorkerPoolQueued() {
		return workerPoolQueued;
	}

	public static int getWorkerPoolIdle() {
		return workerPoolIdle;
	}

	public static int getSocketBothIdle() {
		return socketBothIdle;
	}

	public static int getServerId() {
		return serverId;
	}

	public static int getWorldQueueSize() {
		return worldQueueSize;
	}

	public static int getCommonQueueSize() {
		return commonQueueSize;
	}

	public static boolean isTcpNodelay() {
		return tcpNodelay;
	}

	public static int getServerMaxBacklog() {
		return serverMaxBacklog;
	}

	public static int getServerNioProcess() {
		return serverNioProcess;
	}

	public static boolean isAllowMisIp(String ip) {
		return true;
	}

}
