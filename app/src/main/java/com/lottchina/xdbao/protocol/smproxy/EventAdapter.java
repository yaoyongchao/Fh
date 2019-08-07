package com.lottchina.xdbao.protocol.smproxy;

import com.lottchina.xdbao.protocol.Command;
import com.lottchina.xdbao.protocol.comm.PEventAdapter;
import com.lottchina.xdbao.protocol.comm.PLayer;
import com.lottchina.xdbao.protocol.connection.SocketTransaction;
import com.lottchina.xdbao.protocol.message.Message;
import com.lottchina.xdbao.protocol.message.PushMessage;

public class EventAdapter extends PEventAdapter {

	private SMProxy smProxy;


	public EventAdapter(SMProxy smProxy) {
		this.smProxy = null;
		this.smProxy = smProxy;
	}

	public void childCreated(PLayer child) {
		SocketTransaction t = (SocketTransaction) child;
		Message msg = t.getResponse();
		Message resmsg = null;
		if (msg.getCommandId().equals(Command.CMD_STATION_MESSAGE)) {
			PushMessage tmpmes = (PushMessage) msg;
			resmsg = smProxy.onPushMessage(tmpmes);
		}else {
			t.close();
		}
		
		if (resmsg != null) {
			try {
				t.send(resmsg);
			} catch (Exception e) {
				e.printStackTrace();
			}
			t.close();
		}
	}
}
