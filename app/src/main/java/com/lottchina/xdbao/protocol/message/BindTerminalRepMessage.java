package com.lottchina.xdbao.protocol.message;


import com.lottchina.baselib.utils.GsonUtil;

public class BindTerminalRepMessage extends Message {

	private MessageHead messageHead;
	
	private BindTerminalRepMessageBody messageBody;
	
	public BindTerminalRepMessage(MessageHead messageHead,String bodyMsg){
		this.messageHead = messageHead;
//		this.messageBody = JSON.parseObject(bodyMsg, BindTerminalRepMessageBody.class);
		this.messageBody = GsonUtil.GsonToBean(bodyMsg,BindTerminalRepMessageBody.class);
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

	public BindTerminalRepMessageBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(BindTerminalRepMessageBody messageBody) {
		this.messageBody = messageBody;
	}


	@Override
	public String toString() {
		return "BindTerminalRepMessage [messageHead=" + messageHead + ", messageBody=" + messageBody + "]";
	}
	
	
	@Override
	public String getUuId() {
		return messageBody.getUuid();
	}

}
