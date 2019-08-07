package com.lottchina.xdbao.protocol.message;

import java.util.HashMap;
import java.util.Map;

public enum MessageCmd {
	
	CMD_DATE_REPORT(100,"查询日报表"),
	CMD_REFRSH_BALANCE(101,"查询余额"),
	CMD_CLOSE_TERMINAL(102,"关机"),
	CMD_UPLOAD_LOG(103,"上传日志");
		
	private int cmd;
	private String desc;
	
	private MessageCmd(int cmd,String desc) {
		this.cmd = cmd;
		this.desc = desc;
	    Cache.map.put(cmd, this);
	}

	public int getCmd() {
		return cmd;
	}


	public void setCmd(int cmd) {
		this.cmd = cmd;
	}


	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static MessageCmd getMessageCmd(String cmd) {
		return Cache.map.get(cmd);
	}
	
	public static class Cache{
		private static final Map<Integer, MessageCmd> map = new HashMap<>();
	}
}
