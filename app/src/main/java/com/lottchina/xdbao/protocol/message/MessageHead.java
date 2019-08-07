package com.lottchina.xdbao.protocol.message;

public class MessageHead {

	private String cmd;
	
	private String digest;
	
	private String digestType;
	
	private String timeStamp;
	
	private String userId;
	
	private String userType;

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getDigestType() {
		return digestType;
	}

	public void setDigestType(String digestType) {
		this.digestType = digestType;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "MessageHead [cmd=" + cmd + ", digest=" + digest + ", digestType=" + digestType + ", timeStamp="
				+ timeStamp + ", userId=" + userId + ", userType=" + userType + "]";
	}
	
	
}
