package com.lottchina.xdbao.protocol.comm;

public class PEvent {

	public PEvent(int type, PLayer source, Object data) {
		this.type = type;
		this.source = source;
		this.data = data;
	}

	public PLayer getSource() {
		return source;
	}

	public int getType() {
		return type;
	}

	public Object getData() {
		return data;
	}

	public static final int CREATED = 1;

	public static final int CHILD_CREATED = 2;

	public static final int DELETED = 4;

	public static final int MESSAGE_SEND_SUCCESS = 8;

	public static final int MESSAGE_SEND_FAIL = 16;

	public static final int MESSAGE_DISPATCH_SUCCESS = 32;

	public static final int MESSAGE_DISPATCH_FAIL = 64;


	protected PLayer source;

	protected int type;

	protected Object data;

}
