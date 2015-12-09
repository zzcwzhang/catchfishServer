package com.fuyun.server.db.util;

import java.util.UUID;

import com.fasterxml.uuid.Generators;

public class Utils {

	//生成唯一标示字符串
	public static String getUserID() {
		
		//return getUserIDForTime();
		return getUserIDForJUG();
	}
	
	//根据时间  生成唯一标示字符串
	public static String getUserIDForTime(){
		System.out.println("唯一标示");
		String userID = "" + System.currentTimeMillis();// Long.toHexString(System.currentTimeMillis());
		//userID = userID.substring(1);
		char array[] = userID.toCharArray();
		char temp = array[0];
		array[0] = array[array.length - 1];
		array[array.length - 1] = temp;

		temp = array[array.length - 1];
		array[array.length - 1] = array[array.length - 2];
		array[array.length - 2] = temp;
		return String.valueOf(array);
	}
	
	//根据Java Uuid Generator (JUG) 生成唯一标示字符串
	public static String getUserIDForJUG(){
		
		UUID uuid = null;
		while(uuid == null){
			uuid = Generators.randomBasedGenerator().generate();
		}
		String str = uuid.toString();
    	str = str.replaceAll("-", "");
    	
    	return str;
	}
}
