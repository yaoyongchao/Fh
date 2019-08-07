package com.lottchina.xdbao.protocol;

import java.util.HashMap;
import java.util.Map;


public enum UserType {
	
	USER_GUEST(100,"guest","游客"),
	USER_NORMAL(200,"normal","普通用户"),
	USER_COMPANY(300,"company","公司"),
	USER_CENTER(400,"center","出票中心"),
	USER_TERMINAL(500,"terminal","终端机"),
	USER_STATION(600,"station","投注站"),
	USER_CUSTOMER(700,"customer","彩民"),
	USER_ADMIN(900,"admin","管理员"),
	USER_SALE_CHANNEL(1100,"sale_channel","销售渠道");
	
	
	private int type;
	private String value;
	private String desc;
	
	private UserType(int type,String value,String desc) {
		this.type = type;
		this.value = value;
		this.desc = desc;
	    Cache.map.put(value, this);
	}




	public String getValue() {
		return value;
	}




	public void setValue(String value) {
		this.value = value;
	}




	public int getType() {
		return type;
	}




	public void setType(int type) {
		this.type = type;
	}




	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static UserType getFileType(String type) {
		return Cache.map.get(type);
	}
	
	public static class Cache{
		private static final Map<String, UserType> map = new HashMap<>();
	}
}
