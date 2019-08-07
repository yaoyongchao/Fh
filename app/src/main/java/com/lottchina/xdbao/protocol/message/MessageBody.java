package com.lottchina.xdbao.protocol.message;

public class MessageBody {

    protected String uuid;

    public MessageBody(){
        super();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

	@Override
	public String toString() {
		return "MessageBody [uuid=" + uuid + "]";
	}
    
    
}
