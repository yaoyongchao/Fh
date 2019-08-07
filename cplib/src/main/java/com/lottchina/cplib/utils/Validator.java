package com.lottchina.cplib.utils;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Austin
 * @Date: 19-3-29
 * @Description: 校验
 */
public class Validator {

	private static Pattern telePattern = Pattern.compile("\\d{3,4}[-－]?\\d{7,8}");
	
	private static Pattern phonePattern = Pattern.compile("1\\d{10}");
	
	public static boolean isValidMobilePhone(String phone){
		Matcher matcher = phonePattern.matcher(phone);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isValidTelePhone(String phone){
		Matcher matcher = telePattern.matcher(phone);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	private static Pattern numberPattern = Pattern.compile("[1-9]+\\d*");
	
	public static boolean isValidNumber(String num){
		Matcher matcher = numberPattern.matcher(num);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isValidNumber(String num,int min,int max){
		if(!isValidNumber(num)){
			return false;
		}
		int number = Integer.parseInt(num);
		if(number>=min&&number<=max)
		{
			return true;
		}
		return false;
		
	}

	/*public static boolean inTime(long start,long end){
		long current = System.currentTimeMillis()- YSApplication.getInstance().timeDiff;
		if(start<current&&end>current){
			return true;
		}
		else
		{
			return false;
		}

	}*/

	final static int[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
	final static int[] POWER_LIST = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
			5, 8, 4, 2};

	/**
	 *
	 * 身份证验证
	 *
	 *@param s
	 *   号码内容
	 *@return 是否有效 null和"" 都是false
	 */
	public static boolean isIdcard(String s){
		if(s == null || (s.length() != 15 && s.length() != 18))
			return false;
		final char[] cs = s.toUpperCase().toCharArray();
		//校验位数
		int power = 0;
		for(int i=0; i<cs.length; i++){
			if(i==cs.length-1 && cs[i] == 'X')
				break;//最后一位可以 是X或x
			if(cs[i]<'0' || cs[i]>'9')
				return false;
			if(i < cs.length -1){
				power += (cs[i] - '0') * POWER_LIST[i];
			}
		}

		//校验年份
		String year = s.length() == 15 ? "19" + s.substring(6,8) :s
				.substring(6, 10);
		final int iyear = Integer.parseInt(year);
		if(iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR))
			return false;//1900年的PASS，超过今年的PASS
		//校验月份
		String month = s.length() == 15 ? s.substring(8, 10) : s.substring(10,12);
		final int imonth = Integer.parseInt(month);
		if(imonth <1 || imonth >12){
			return false;
		}
		//校验天数
		String day = s.length() ==15 ? s.substring(10, 12) : s.substring(12, 14);
		final int iday = Integer.parseInt(day);
		if(iday < 1 || iday > 31)
			return false;
		//校验一个合法的年月日
		//校验"校验码"
		if(s.length() == 15)
			return true;
		return cs[cs.length -1 ] == PARITYBIT[power % 11];
	}
}
