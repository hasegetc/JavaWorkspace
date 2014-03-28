package com.sinaapp.sanrenxing.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static long time = 60*60*1000;
	
	public static String format(Date nowDate,Date date) {//将毫秒数换算成x天x时x分x秒x毫秒
		
		Long ms = nowDate.getTime() - date.getTime();
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;

		long day = ms / dd;
		long hour = (ms - day * dd) / hh;
		long minute = (ms - day * dd - hour * hh) / mi;
		
        if(day != 0){
        	return day + "天" + hour + "小时" + minute +"分";
        }else if(hour != 0){
        	return hour + "小时" + minute +"分";
        }else{
        	return minute +"分";
        }
	}
	
	public static String formatHour(Date date1,Date date2){
		long mh = 1000 * 60 * 60;
		Long ms = date1.getTime() - date2.getTime();
		return (ms/mh)+"小时";
	}
	
   public static int formattt(Date nowDate,Date date) {//将毫秒数换算成x天
		
		Long ms = nowDate.getTime() - date.getTime();
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;

		Long day = ms / dd;
		
		return Integer.valueOf(day.toString());
	}
   
   public static String formaHoure(Date nowDate,Date date) {//将毫秒数换算成小时
		
	   Long ms = nowDate.getTime() - date.getTime();
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;

		long hour = ms / hh;
		long minute = (ms -  hour * hh) / mi;
		
		String result = "";
		
       if(hour != 0){
    	   result = result+ hour + "小时";
       }
       if(minute != 0){
    	   result = result+ minute +"分";
       }
       return result;
	}
	
   public static String formatt(Long time1,Long time2) {//将毫秒数换算成x天x时x分x秒x毫秒
		
		Long ms = time1 - time2;
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;

		long day = ms / dd;
		long hour = (ms - day * dd) / hh;
		long minute = (ms - day * dd - hour * hh) / mi;
		
		String str = "";
		
        if(day != 0){
        	str = day + "天" ;
        }
        if(hour != 0){
        	str = str + hour + "小时" ;
        }
        if(minute != 0){
        	str = str+ minute +"分";
        }
        return str;
	}
	
	public static Date stringToDate(String date) throws ParseException{
		if(null==date || "".equals(date)){
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(date);
	}
	/**
	 * 字符串转日期
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date stringToDate2(String strDate) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		try {
			return simpleDateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date strToDate(String date) throws ParseException{
		if(null==date || "".equals(date)){
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(date);
	}

	public static String timeFormat(Date date) {
		if(null==date)
		return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
	}
	public static String timeFormat2(Date date) {
		if(null==date)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	
	public static boolean isWeekSix(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		switch (week) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			return false;
		case 6:
		default:
			return true;
		}
	}
}
