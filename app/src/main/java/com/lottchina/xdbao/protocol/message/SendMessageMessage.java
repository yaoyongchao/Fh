package com.lottchina.xdbao.protocol.message;


import com.lottchina.xdbao.protocol.Command;

public class SendMessageMessage extends Message {

	public SendMessageMessage(int memberId, String content){
		this.messageBody = new SendMessageMessageBody(memberId,content);
	}
	
	@Override
	public String getCommandId() {
		return Command.CMD_STATION_SEND_MSG;
	}

	public MessageHead getMessageHead() {
		return messageHead;
	}

	public void setMessageHead(MessageHead messageHead) {
		this.messageHead = messageHead;
	}
	
	public SendMessageMessageBody getMessageBody(){
		return (SendMessageMessageBody)messageBody;
	}
	
	public void setMessageBody(SendMessageMessageBody messageBody){
		this.messageBody = messageBody;
	}

	@Override
	public String toString() {
		return "SendMessageMessage [messageHead=" + messageHead + ", messageBody=" + messageBody + "]";
	}
	
}
