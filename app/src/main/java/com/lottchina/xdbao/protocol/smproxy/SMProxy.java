package com.lottchina.xdbao.protocol.smproxy;


import com.lottchina.xdbao.protocol.comm.SocketStatusListener;
import com.lottchina.xdbao.protocol.connection.SocketConnection;
import com.lottchina.xdbao.protocol.connection.SocketTransaction;
import com.lottchina.xdbao.protocol.message.Message;
import com.lottchina.xdbao.protocol.message.PushMessage;
import com.lottchina.xdbao.protocol.message.SuccessRepMessage;
import com.lottchina.xdbao.protocol.util.Args;
import com.lottchina.xdbao.utils.log.Logger;
import com.lottchina.xdbao.utils.log.LoggerFactory;

import java.util.Map;

public class SMProxy implements SocketStatusListener {
	private static final Logger log = LoggerFactory.getLogger(SMProxy.class);
	
	public interface MessageListener {
		public void onPushMessage(PushMessage msg);
		public void onSocketConnection(boolean isConnected);
	}

	private Object tag;

	public Object getTag() {
		return tag;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}

	private SocketConnection conn;
	private MessageListener messageListener=null;

	private Args args;
	
	public SMProxy(Map args) {
		this(new Args(args));
	}

	public SMProxy(Args args) {
		this.args = args;
		
	}
	public void start(){
		try{
			conn = new SocketConnection(args);
			conn.addEventListener(new EventAdapter(this));
			conn.setClistner(this);
		}catch(Exception e){
			log.info("SMProxy constructor:{}",e);
		}
	}
	public void onConnectStatusChange(boolean isConnected) {
		if (this.messageListener!=null) {
			messageListener.onSocketConnection(isConnected);
		}
	}
	public MessageListener getMessageListener() {
		return messageListener;
	}

	public void setMessageListener(MessageListener messageListener) {
		this.messageListener = messageListener;
	}

	public Message send(Message message) throws Exception {
		if (message == null)
			return null;
		SocketTransaction t = (SocketTransaction) conn.createChild();
		try {
			t.send(message);
			t.waitResponse();
			Message rsp = t.getResponse();
			Message cmppmessage = rsp;
			return cmppmessage;
		} finally {
			t.close();
		}
	}
	public Message send(Message message,int timeOut) throws Exception {
		long time = System.currentTimeMillis();
		try{
			return send(message);
		}
		catch (Exception e){
			throw(e);
		}
		finally {
			long exeTime = System.currentTimeMillis()-time;
			if(exeTime<timeOut){
				Thread.sleep(timeOut-exeTime);
			}
		}
	}
	

	public void onTerminate() {
	}

	public Message onPushMessage(PushMessage msg){
		if (this.messageListener!=null) {
			 messageListener.onPushMessage(msg);
		}
		return new SuccessRepMessage();
	}

	public void stop() {
		conn.stop();
		conn = null;
	}
	
	public SocketConnection getConn(){
		return conn;
	}
	
	public boolean available(){
		return conn!=null && conn.available() && conn.isCanSend();
	}
	
	public String getConnState() {
		return conn.getError()==null?"连接正常":conn.getError();
	}

	public void onSendReadTimeOut(){
		conn.onSendReadTimeOut();
	}
	
	public String getUserId(){
		return conn.getUserId();
	}
	public String getToken(){
		return conn.getToken();
	}
}
