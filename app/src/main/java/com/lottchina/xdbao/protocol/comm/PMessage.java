package com.lottchina.xdbao.protocol.comm;

public abstract class PMessage {
	
	protected String userId,token;
	
	public abstract String getUuId();
	
	public abstract void setUuId(String uuId); 
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
