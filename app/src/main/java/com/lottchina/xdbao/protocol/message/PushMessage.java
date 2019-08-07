package com.lottchina.xdbao.protocol.message;


import com.lottchina.baselib.utils.GsonUtil;

public class PushMessage extends Message{

	private MessageHead messageHead;

	private PushMessageBody messageBody;

	public PushMessage(MessageHead messageHead, String bodyMsg){
		this.messageHead = messageHead;
//		this.messageBody = JSON.parseObject(bodyMsg, PushMessageBody.class);
		this.messageBody = GsonUtil.GsonToBean(bodyMsg,PushMessageBody.class);
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

	public PushMessageBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(PushMessageBody messageBody) {
		this.messageBody = messageBody;
	}


	@Override
	public String toString() {
		return "PushMessage [messageHead=" + messageHead + ", messageBody=" + messageBody + "]";
	}


	@Override
	public String getUuId() {
		return messageBody.getUuid();
	}

}
