package com.lottchina.xdbao.protocol.message;


import com.lottchina.xdbao.protocol.Command;

public class ActiveMessage extends Message {

	public ActiveMessage(){
		this.messageBody = new ActiveMessageBody();
	}
	
	@Override
	public String getCommandId() {
		return Command.CMD_STATION_CONNECT_ACTIVE;
	}

	public MessageHead getMessageHead() {
		return messageHead;
	}

	public void setMessageHead(MessageHead messageHead) {
		this.messageHead = messageHead;
	}
	
	public ActiveMessageBody getMessageBody(){
		return (ActiveMessageBody)messageBody;
	}
	
	public void setMessageBody(ActiveMessageBody messageBody){
		this.messageBody = messageBody;
	}

	@Override
	public String toString() {
		return "ActiveMessage [messageHead=" + messageHead + ", messageBody=" + messageBody + "]";
	}
	
	
}
