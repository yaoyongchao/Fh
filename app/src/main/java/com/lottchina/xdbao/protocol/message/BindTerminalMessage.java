package com.lottchina.xdbao.protocol.message;


import com.lottchina.xdbao.protocol.Command;

public class BindTerminalMessage extends Message {
	
	public BindTerminalMessage(long terminalId){
		this.messageBody = new BindTerminalMessageBody(terminalId);
	}
	

	@Override
	public String getCommandId() {
		return Command.CMD_STATION_BIND_TERMINAL;
	}

	public MessageHead getMessageHead() {
		return messageHead;
	}

	public void setMessageHead(MessageHead messageHead) {
		this.messageHead = messageHead;
	}
	
	public BindTerminalMessageBody getMessageBody(){
		return (BindTerminalMessageBody)messageBody;
	}
	
	public void setMessageBody(BindTerminalMessageBody messageBody){
		this.messageBody = messageBody;
	}

	@Override
	public String toString() {
		return "BindTerminalMessage [messageHead=" + messageHead + ", messageBody=" + messageBody + "]";
	}
	
	
}
