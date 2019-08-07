package com.lottchina.xdbao.protocol;

public enum SocketStatus {
	SOCKET_UNCONNECTED(-1,"服务器未连接"),
	SOCKET_CONNECTED(0,"服务器接成功"), ;

	private int status;

	private String desc;


	private SocketStatus(int status, String desc){
		this.status = status;
		this.desc = desc;
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
	
	
}
