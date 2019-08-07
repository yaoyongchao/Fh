package com.lottchina.xdbao.protocol.message;

import java.util.HashMap;
import java.util.Map;


public enum SchemeFileFrom {
	FROM_OKOOO(10,"澳客"),
	FROM_500(20,"500万"),
	FROM_DYJ(30,"大赢家"),
	FROM_ZQW(40,"中国足彩网"),
	FROM_OTHER(1000,"其它");
	private int type;
	private String desc;

	private SchemeFileFrom(int type,String desc) {
		this.type = type;
		this.desc = desc;
		Cache.map.put(type, this);
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

	public static SchemeFileFrom getSchemeFileFrom(int type) {
		return Cache.map.get(type);
	}

	public static class Cache{
		private static final Map<Integer, SchemeFileFrom> map = new HashMap<>();
	}
}
