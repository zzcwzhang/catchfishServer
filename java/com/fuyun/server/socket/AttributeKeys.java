package com.fuyun.server.socket;

public class AttributeKeys {

	//session 心跳属性
	public static final String KEY_HEART_TIME_OUT_TIMES = "heart_timeout"; //心跳超时 key
	public static final String KEY_HEART_BEAT_TIME = "heart_beat"; //心跳节拍  key
	
	public static final String KEY_HEART_COUNT = "heart_count";//心跳次数
	public static final int MAX_HEART_COUNT = 3; //心跳次数 空闲心跳3关闭连接  10s一次
	
	//session 关闭设置
	public static final String KEY_TAG_CLOSED = "key_tag_closed";
	
	//session 有效标记
	public static final String KEY_TAG_ENABLE = "key_tag_enable";

}
