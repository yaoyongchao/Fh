package com.lottchina.xdbao.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ParseTimeUtil {

	public enum ParseType{
		DateTime,
		Date,
	}

	static SimpleDateFormat fullDateTimeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);

	static SimpleDateFormat dateTimeformat = new SimpleDateFormat("yyyy/MM/dd HH:mm",Locale.US);
	
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.US);

	static SimpleDateFormat todaySNameTimeFormat = new SimpleDateFormat("HH:mm:ss",Locale.US);

	static SimpleDateFormat todayNameTimeFormat = new SimpleDateFormat("今天 HH:mm",Locale.US);

	static SimpleDateFormat yestordayNameTimeFormat = new SimpleDateFormat("昨天 HH:mm",Locale.US);

	static SimpleDateFormat preyesNameTimeFormat = new SimpleDateFormat("前天 HH:mm",Locale.US);

	static SimpleDateFormat fullDateTimeformatAll = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS",Locale.US);

	static SimpleDateFormat simdateFormat = new SimpleDateFormat("yyyyMMdd",Locale.US);

	public static String parseDate(long date,ParseType type){
		return parseDate(new Date(date),type);
	}
	public static String parseDate(Date date,ParseType type){
		
		Calendar target = Calendar.getInstance();
		target.set(Calendar.HOUR_OF_DAY,0);
		target.set(Calendar.MINUTE,0);
		target.set(Calendar.SECOND, 0);

		Calendar paramDate = Calendar.getInstance();
		paramDate.setTime(date);

		if(paramDate.after(target)){
			return todayNameTimeFormat.format(date);
		}
		else{
			target.add(Calendar.DATE,-1);
			if(paramDate.after(target)){
				return yestordayNameTimeFormat.format(date);
			}
			else{
				target.add(Calendar.DATE,-1);
				if(paramDate.after(target)){
					return preyesNameTimeFormat.format(date);
				}
			}
		}
		if(type==ParseType.DateTime){
			return dateTimeformat.format(date);
		}
		return dateFormat.format(date);

	}


	public static String parseDate(long date){
		return dateFormat.format(new Date(date));
	}

	public static String parseFullDate(long date){
		return fullDateTimeformat.format(new Date(date));
	}

	public static String getHourMinuteSencond(long date){
		return todaySNameTimeFormat.format(new Date(date));
	}

	public static String getAllDate(long date){
		return fullDateTimeformatAll.format(new Date(date));
	}

	public static String parseDate(Date date,String format){
		SimpleDateFormat dateFormat = new SimpleDateFormat(format,Locale.US);
		return dateFormat.format(date);
	}

	public static String getWeekDate(String date){
		try {
			String[] weeks = {"周日","周一","周二","周三","周四","周五","周六"};
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(simdateFormat.parse(date));
			int week_index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			if(week_index<0){
				week_index = 0;
			}
			return weeks[week_index];
		}
		catch(Exception e){
			return date;
		}
	}
}
