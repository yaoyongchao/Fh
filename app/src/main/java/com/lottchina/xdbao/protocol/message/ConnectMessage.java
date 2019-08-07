package com.lottchina.xdbao.protocol.message;

import com.lottchina.xdbao.protocol.Command;

public class ConnectMessage extends Message {

	
	public ConnectMessage(String userId,String token){
		this.messageBody = new ConnectMessageBody();
	}

	@Override
	public String getCommandId() {
		return Command.CMD_STATION_CONNET;
	}

	public MessageHead getMessageHead() {
		return messageHead;
	}

	public void setMessageHead(MessageHead messageHead) {
		this.messageHead = messageHead;
	}
	
	public ConnectMessageBody getMessageBody(){
		return (ConnectMessageBody)messageBody;
	}
	
	public void setMessageBody(ConnectMessageBody messageBody){
		this.messageBody = messageBody;
	}

	@Override
	public String toString() {
		return "ConnectMessage [messageHead=" + messageHead + ", messageBody=" + messageBody + "]";
	}
	
}
