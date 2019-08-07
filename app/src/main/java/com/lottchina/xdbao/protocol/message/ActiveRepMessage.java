package com.lottchina.xdbao.protocol.message;


import com.lottchina.baselib.utils.GsonUtil;

public class ActiveRepMessage extends Message {

	private MessageHead messageHead;
	
	private ActiveRepMessageBody messageBody;
	
	public ActiveRepMessage(MessageHead messageHead,String bodyMsg){
		this.messageHead = messageHead;
//		this.messageBody = JSON.parseObject(bodyMsg, ActiveRepMessageBody.class);
		this.messageBody = GsonUtil.GsonToBean(bodyMsg,ActiveRepMessageBody.class);
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

	public ActiveRepMessageBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(ActiveRepMessageBody messageBody) {
		this.messageBody = messageBody;
	}


	@Override
	public String toString() {
		return "ActiveRepMessage [messageHead=" + messageHead + ", messageBody=" + messageBody + "]";
	}
	
	
	@Override
	public String getUuId() {
		return messageBody.getUuid();
	}

}
