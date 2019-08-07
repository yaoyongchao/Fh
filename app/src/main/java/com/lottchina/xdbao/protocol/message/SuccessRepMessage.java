package com.lottchina.xdbao.protocol.message;


import com.lottchina.xdbao.protocol.Command;

public class SuccessRepMessage extends Message {

	public SuccessRepMessage(){
		this.messageBody = new SuccessRepMessageBody();
	}


	@Override
	public String getCommandId() {
		return Command.CMD_STATION_MESSAGE;
	}

	public MessageHead getMessageHead() {
		return messageHead;
	}

	public void setMessageHead(MessageHead messageHead) {
		this.messageHead = messageHead;
	}

	public SuccessRepMessageBody getMessageBody(){
		return (SuccessRepMessageBody)messageBody;
	}

	public void setMessageBody(SuccessRepMessageBody messageBody){
		this.messageBody = messageBody;
	}

	@Override
	public String toString() {
		return "SuccessRepMessageBody [messageHead=" + messageHead + ", messageBody=" + messageBody + "]";
	}

}
