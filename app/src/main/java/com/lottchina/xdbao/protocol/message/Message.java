package com.lottchina.xdbao.protocol.message;


import com.lottchina.baselib.utils.GsonUtil;
import com.lottchina.xdbao.protocol.comm.PMessage;
import com.lottchina.xdbao.protocol.util.MD5Util;
import com.lottchina.xdbao.protocol.util.TypeConvert;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Message extends PMessage implements Cloneable {
//	private static final Logger log = LoggerFactory.getLogger(Message.class);
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS +08:00");
	
	protected MessageHead messageHead;
	
	protected MessageBody messageBody;
	
	
	
	protected byte buf[];
	
	public Message() {
	}

	public Object clone() {
		try {
			Message m = (Message) super.clone();
			m.buf = (byte[]) buf.clone();
			Message cmppmessage = m;
			return cmppmessage;
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		Object obj = null;
		return obj;
	}

	public abstract String toString();

	public abstract String getCommandId();

	public MessageHead getMessageHead(){
		return this.messageHead;
	}
	
	public MessageBody getMessageBody(){
		return this.messageBody;
	}
	
	public String getTimeStamp(Date date){
		return simpleDateFormat.format(date);
	}

	public byte[] getBytes() {
		try {
			try {
//				String msgBody = JSON.toJSONString(messageBody);
				String msgBody = GsonUtil.GsonString(messageBody);
				String timeStamp = getTimeStamp(new Date());
				String digest = MD5Util.md5((token + msgBody + timeStamp).getBytes("UTF-8"));
				this.messageHead = new MessageHead();
				messageHead.setCmd(getCommandId());
				messageHead.setDigest(digest);
				messageHead.setDigestType("md5");
				messageHead.setTimeStamp(timeStamp);
				messageHead.setUserType("station");
				messageHead.setUserId(userId);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			
//			String head = JSON.toJSONString(messageHead);
//			String body = JSON.toJSONString(messageBody);
			String head = GsonUtil.GsonString(messageHead);
			String body = GsonUtil.GsonString(messageBody);
			//CpItemCheckBox("发送消息：head={},body={}",head,body);
			byte[] headBytes = head.getBytes("UTF-8");
			byte[] bodyBytes = body.getBytes("UTF-8");
			int length = 8 + headBytes.length+bodyBytes.length;
			
			this.buf = new byte[length];
			
			TypeConvert.int2byte(length-4, this.buf, 0);
			TypeConvert.int2byte(headBytes.length, this.buf, 4);
			System.arraycopy(headBytes, 0, this.buf, 8, headBytes.length);
			System.arraycopy(bodyBytes, 0, this.buf, 8+headBytes.length, bodyBytes.length);
			return this.buf;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getUuId() {
		return messageBody.getUuid();
	}

	@Override
	public void setUuId(String uuId) {
		messageBody.setUuid(uuId);
	}


	
	
}
