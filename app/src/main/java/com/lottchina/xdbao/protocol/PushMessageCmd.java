package com.lottchina.xdbao.protocol;

import java.util.HashMap;
import java.util.Map;

public enum PushMessageCmd {
	CMD_USER_REGIST(10000,"用户注册"),
	CMD_USER_CHAT(11000,"聊天"),
	CMD_USER_WITHDORW(12000,"提现"),
	CMD_NEW_ORDER(13000,"新订单"),
	CMD_BALANCE_REMIND(14000,"余额不足"),
	CMD_RECHARGE_REMIND(15000,"提醒充值"),
	CMD_DATE_REPORT(16000,"查询报表"),
	CMD_CLOSE_TERMINAL(17000,"关机"),
	CMD_UPLOAD_LOG(18000,"上传日志"),
	CMD_PRINT_TIKCET(19000,"打印票"),
	CMD_RESET_PRINT_CENTER_IP(20000,"修改连接出票中心Ip"),
	CMD_RESET_AUTO_PRINT(21000,"自动设置"),
	UPDATE_BOX_DRIVER(22000,"更新盒子驱动"),
	UPDATE_APP_NEW(23000,"更新app"),
	QUERY_11X5_OPEN_NUMBER(24000,"获取11选5开奖号码");
	private int cmd;
	private String desc;
	
	private PushMessageCmd(int cmd,String desc) {
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
	
	public static PushMessageCmd getPushMessageCmd(String cmd) {
		return Cache.map.get(cmd);
	}
	
	public static class Cache{
		private static final Map<Integer, PushMessageCmd> map = new HashMap<>();
	}
}
