package com.lottchina.xdbao.protocol.connection;


import com.lottchina.baselib.utils.GsonUtil;
import com.lottchina.xdbao.protocol.Command;
import com.lottchina.xdbao.protocol.comm.PMessage;
import com.lottchina.xdbao.protocol.comm.PWriter;
import com.lottchina.xdbao.protocol.message.Message;
import com.lottchina.xdbao.utils.log.Logger;
import com.lottchina.xdbao.utils.log.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;

public class SocketWriter extends PWriter {
	private static final Logger log = LoggerFactory.getLogger(SocketWriter.class);
	
	protected OutputStream out;

	public SocketWriter(OutputStream out) {
		this.out = out;
	}

	public synchronized void write(PMessage message) throws IOException {
		Message msg = (Message) message;
		out.write(msg.getBytes());
		out.flush();
		if(!msg.getCommandId().equals(Command.CMD_STATION_CONNECT_ACTIVE)){
			log.info("发送数据：head={},body={}", GsonUtil.GsonString(msg.getMessageHead()),GsonUtil.GsonString(msg.getMessageBody()));
		}
	}

	public void writeHeartbeat() throws IOException {
	}

}