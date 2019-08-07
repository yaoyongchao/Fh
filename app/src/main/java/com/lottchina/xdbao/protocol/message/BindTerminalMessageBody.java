package com.lottchina.xdbao.protocol.message;

public class BindTerminalMessageBody  extends MessageBody{
	
	private long terminal_id;
	
	public BindTerminalMessageBody(){
		super();
	}
	
	public BindTerminalMessageBody(long terminal_id){
		this();
		this.terminal_id = terminal_id;
	}

	public long getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(long terminal_id) {
		this.terminal_id = terminal_id;
	}

	@Override
	public String toString() {
		return "BindTerminalMessageBody [terminal_id=" + terminal_id + ", uuid=" + uuid + "]";
	}
	
}
