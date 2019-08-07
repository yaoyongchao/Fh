package com.lottchina.xdbao.protocol.message;

import com.lottchina.baselib.utils.GsonUtil;

public class ConnectRepMessage extends Message {

	private MessageHead messageHead;
	
	private ConnectRepMessageBody messageBody;
	
	public ConnectRepMessage(MessageHead messageHead,String bodyMsg){
		this.messageHead = messageHead;
//		this.messageBody = JSON.parseObject(bodyMsg, ConnectRepMessageBody.class);
		this.messageBody = GsonUtil.GsonToBean(bodyMsg,ConnectRepMessageBody.class);
	}
	

	@Override
	public String getCommandId() {
		return messageHead.getCmd();
	}

	public MessageHead getMessageHead() {
		return messageHead;
	}

	public void setMessageHead(MessageHead messageHead) {
		this.messageHead = messageHead;
	}

	public ConnectRepMessageBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(ConnectRepMessageBody messageBody) {
		this.messageBody = messageBody;
	}


	@Override
	public String toString() {
		return "ConnectRepMessage [messageHead=" + messageHead + ", messageBody=" + messageBody + "]";
	}
	
	
	@Override
	public String getUuId() {
		return messageBody.getUuid();
	}

}
