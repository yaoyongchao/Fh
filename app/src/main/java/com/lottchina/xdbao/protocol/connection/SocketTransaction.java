package com.lottchina.xdbao.protocol.connection;


import com.lottchina.xdbao.protocol.comm.PLayer;
import com.lottchina.xdbao.protocol.comm.PMessage;
import com.lottchina.xdbao.protocol.message.Message;

public class SocketTransaction extends PLayer {
	private PMessage message = null;
//	private static final Logger log = LoggerFactory.getLogger(SocketTransaction.class);
	
	private Message receive;

	private String seqId;
	
	public SocketTransaction(PLayer connection) {
		super(connection);
		seqId = super.uuid;
	}

	public synchronized void onReceive(PMessage msg) {
		this.message = msg;
		receive = (Message) msg;
		seqId = receive.getUuId();
		notifyAll();
	}


	public void sendResponse(Message message) throws Exception{
		super.parent.send(message);
		this.close();
	}

	public void send(PMessage message) throws Exception {
		this.message = message;
		message.setUuId(seqId);
		message.setToken(getParent().getToken());
		message.setUserId(getParent().getUserId());
		super.parent.send(message);
	}

	public Message getResponse() {
		return receive;
	}

	public synchronized void waitResponse() {
		if (receive == null)
			try {
				wait(((SocketConnection) super.parent).getTransactionTimeout());
			} catch (InterruptedException interruptedexception) {
			}
	}

	public PMessage getMessage() {
		return message;
	}

	public void setMessage(PMessage message) {
		this.message = message;
	}
}
