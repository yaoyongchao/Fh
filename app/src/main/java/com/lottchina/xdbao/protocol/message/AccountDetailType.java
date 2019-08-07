package com.lottchina.xdbao.protocol.message;

import java.util.HashMap;
import java.util.Map;

public enum AccountDetailType {
	get_node1(1000, "充值"),
	get_node1_1(1010, "在线充值"),
	get_node2(1100, "投注"),
	get_node3(1200, "出票"),
	get_node4(1300,"退款"),
	get_node5(1400, "返奖"),
	get_node6(1500, "兑奖"),
	get_node7(1600, "矫正"),
	get_node8(1700, "返佣"),
	get_node9(1800, "拿票"),
	get_node10(1900, "提现");
	
	private int type;
	private String desc;
	
	private AccountDetailType(int type,String desc) {
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
	
	public static AccountDetailType getAccountDetailType(int type) {
		return Cache.map.get(type);
	}
	
	public static class Cache{
		private static final Map<Integer, AccountDetailType> map = new HashMap<>();
	}
}
