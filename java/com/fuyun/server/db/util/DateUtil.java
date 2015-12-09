package com.fuyun.server.db.util;
import java.util.Date; 
import java.text.SimpleDateFormat; 
import java.text.DateFormat; 

/** 
* 日期工具类 
* File: DateUtil.java 
* User: leizhimin 
* Date: 2008-2-14 15:17:20 
*/ 
public class DateUtil { 
    /** 
     * 获取日期中的年 
     * @param date 日期 
     * @return 年份 
     */ 
    public static String getYear(Date date){ 
       DateFormat f_year = new SimpleDateFormat("yyyy"); 
       return f_year.format(date).toString(); 
    } 

    /** 
     * 获取日期中的月 
     * @param date 日期 
     * @return 月份 
     */ 
    public static String getMonth(Date date){ 
        DateFormat f_month=new SimpleDateFormat("MM"); 
        return f_month.format(date).toString(); 
    } 

    /** 
     * 获取日期中天 
     * @param date 日期 
     * @return 天 
     */ 
    public static String getDay(Date date){ 
        DateFormat f_day=new SimpleDateFormat("dd"); 
        return f_day.format(date).toString(); 
    } 

    /** 
     * 获取日期中的星期 
     * @param date 日期 
     * @return 星期 
     */ 
    public static String getWeek(Date date){ 
        DateFormat f_week=new SimpleDateFormat("EEEEEEE"); 
        return f_week.format(date).toString(); 
    } 

    /** 
     * 获取日期中的时间 
     * @param date 日期 
     * @return 时间 
     */ 
    public static String getTime(Date date){ 
        DateFormat f_time=new SimpleDateFormat("HH时mm分 ss秒"); 
        return f_time.format(date).toString(); 
    } 
    
    /** 
     * 比较2个签到日期是否相等
     * @param date 日期 1
     * @param date 日期 2
     * @return 是否相等
     */ 
    public static boolean signEqual(Date d1,Date d2){
    	int day1 = Integer.parseInt(getDay(d1));
    	int day2 = Integer.parseInt(getDay(d2));
    	if(day1 == day2)
    	{
    		int month1 = Integer.parseInt(getMonth(d1));
        	int month2 = Integer.parseInt(getMonth(d2));
        	if(month1 == month2)
        	{
        		int year1 = Integer.parseInt(getYear(d1));
            	int year2 = Integer.parseInt(getYear(d2));
            	if(year1 == year2)
            	{
            		return true;
            	}
        	}
    	}
    
    	
    	
    	return false;
    }
    
    /** 
     * 获取今日
     * @return 天
     */
    public static int newDay(){
    	return Integer.parseInt(getDay(new Date()));
    }
    
    /** 
     * 获取今月
     * @return 月份
     */
    public static int newMonth(){
    	return Integer.parseInt(getMonth(new Date()));
    }
    
    /** 
     * 获取年月
     * @return 年份
     */
    public static int newYear(){
    	return Integer.parseInt(getYear(new Date()));
    }
}