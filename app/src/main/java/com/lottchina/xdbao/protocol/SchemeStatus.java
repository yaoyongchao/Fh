package com.lottchina.xdbao.protocol;

import java.util.HashMap;
import java.util.Map;


public enum SchemeStatus {
	UNPAIED(1,"未支付"),
	INIT(10,"等待生成票据"),
	JOINTING(15, "合买中"),
	JOINT_FULL(17, "合买满员"),
	CHECKING(20,"正在生成票据"),
	CHECK_FAIELD(25,"处理文件失败"),
	PRINTING(30,"等待出票"),
	PARPRINTED(35,"出票完成"),
	PRINTED(40,"出票完成"),
	RUNNING(50,"追号中"),
	CANCELED(60,"已取消"),
	JOINT_FAILED(61,"合买失败"),
	UNPAY_CANCELED(62,"未支付"),
	FINISHED(100,"已经完成"),
	UNKNOWN(-1000,"未知状态");
	private int status;
	private String desc;
	
	private SchemeStatus(int status,String desc) {
		this.status = status;
		this.desc = desc;
	    Cache.map.put(status, this);
	}






	public int getStatus() {
		return status;
	}






	public void setStatus(int status) {
		this.status = status;
	}






	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static SchemeStatus getSchemeStatus(int status) {
		SchemeStatus schemeStatus = Cache.map.get(status);
		if(schemeStatus==null){
			return UNKNOWN;
		}
		return schemeStatus;
	}
	
	public static class Cache{
		private static final Map<Integer, SchemeStatus> map = new HashMap<>();
	}
}
