package com.lottchina.xdbao.protocol.connection;

import com.lottchina.xdbao.protocol.Command;
import com.lottchina.xdbao.protocol.comm.PLayer;
import com.lottchina.xdbao.protocol.comm.PMessage;
import com.lottchina.xdbao.protocol.comm.PReader;
import com.lottchina.xdbao.protocol.comm.PSocketConnection;
import com.lottchina.xdbao.protocol.comm.PWriter;
import com.lottchina.xdbao.protocol.message.ActiveMessage;
import com.lottchina.xdbao.protocol.message.ActiveRepMessage;
import com.lottchina.xdbao.protocol.message.ConnectMessage;
import com.lottchina.xdbao.protocol.message.ConnectRepMessage;
import com.lottchina.xdbao.protocol.message.Message;
import com.lottchina.xdbao.protocol.util.Args;
import com.lottchina.xdbao.utils.log.Logger;
import com.lottchina.xdbao.utils.log.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SocketConnection extends PSocketConnection {
	private static final Logger log = LoggerFactory.getLogger(SocketConnection.class);

	public SocketConnection(Args args) {
		degree = 0;
		hbnoResponseOut = 3;
		hbnoResponseOut = args.get("heartbeat-noresponseout", 3);
		init(args);
	}

	protected PWriter getWriter(OutputStream out) {
		return new SocketWriter(out);
	}

	protected PReader getReader(InputStream in) {
		return new SocketReader(in);
	}

	public String getChildId(PMessage message) {
		Message msg = (Message)message;
		String commandId = msg.getCommandId();
		if (commandId.equals(Command.CMD_STATION_MESSAGE) ) {
			return null;
		}
		else {
			return msg.getUuId();
		}
	}

	public PLayer createChild() {
		return new SocketTransaction(this);
	}

	public int getTransactionTimeout() {
		return super.transactionTimeout;
	}

	public synchronized void waitAvailable() {
		try {
			if (getError() == PSocketConnection.NOT_INIT){
				wait(super.transactionTimeout);
			}
		} catch (InterruptedException interruptedexception) {
		}
	}

	public void close_d() {
		super.close();
	}
	public void stop(){
		isManStop = true;
		if (heartbeatThread != null){
			heartbeatThread.kill();
		}
		if(receiveThread != null){
			receiveThread.kill();
		}

		super.close();
	}

	protected void heartbeat() throws Exception {
		ActiveMessage hbmes = new ActiveMessage();
		SocketTransaction t = (SocketTransaction) createChild();
		t.send(hbmes);
		t.waitResponse();
		ActiveRepMessage rsp = (ActiveRepMessage) t.getResponse();
		if (rsp == null) {
			degree++;
			if (degree == hbnoResponseOut) {
				degree = 0;
				throw new IOException("心跳异常(心跳发出多次，没有返回值或返回的值为不成功)");
			}
		} else {
			degree = 0;
		}
		t.close();
	}

	protected synchronized void connect() {
		canSend = false;
		super.connect();
		if (!available())
			return;

		if(getUserId()==null || getUserId().length()==0 || "0".equals(getUserId())){
			stop();
			return;
		}
		ConnectMessage request = null;
		ConnectRepMessage rsp = null;
		try {
			request = new ConnectMessage(getUserId(), getToken());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			close();
			notifyAll();
			return;
		}
		SocketTransaction t = (SocketTransaction) createChild();
		try {
			t.send(request);
			PMessage m = super.in.read();
			onReceive(m);
		} catch (Exception e) {
			e.printStackTrace();
			close();
			setError(String.valueOf("登录异常")
					+ String.valueOf(explain(e)));
			notifyAll();
			return;
		}
		rsp = (ConnectRepMessage) t.getResponse();
		if (rsp == null) {
			close();
			setError("登录异常");
		}
		t.close();
		if (rsp == null) {
			close();
		}
		else{
			canSend = true;
		}
		if(canSend){
			fireConnectStatusChange(true);
		}
		notifyAll();
	}

	private int degree;

	private int hbnoResponseOut;
}
