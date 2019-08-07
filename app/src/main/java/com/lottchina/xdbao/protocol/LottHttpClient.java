/*
package com.lottchina.xdbao.protocol;

import android.content.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lottchina.baselib.utils.GsonUtil;
import com.lottchina.xdbao.protocol.message.MessageBody;
import com.lottchina.xdbao.protocol.message.MessageHead;
import com.lottchina.xdbao.protocol.util.MD5Util;
import com.lottchina.xdbao.utils.log.Logger;
import com.lottchina.xdbao.utils.log.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class LottHttpClient {

	private static final Logger log = LoggerFactory.getLogger(LottHttpClient.class);

	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");

	public static <T extends MessageBody> void post(Context context, String cmd, String userId, String pwd, MessageBody body, final OnHttpPostResultListener<T> listener, final Class<T> clz){
		post(context,cmd,userId,pwd,body,listener,clz,30000);
	}
	public static <T extends MessageBody> void post(Context context,String cmd, String userId,String pwd,MessageBody body, final OnHttpPostResultListener<T> listener, final Class<T> clz,int timeOut) {
		post(context,Configuration.serverUrl,cmd,userId,pwd,body,listener,clz,timeOut);
	}
	public static <T extends MessageBody> void post(Context context,String url, final String cmd, String userId,String pwd,MessageBody body, final OnHttpPostResultListener<T> listener, final Class<T> clz,int timeOut){
		try {
			if(pwd == null){
				pwd = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
			}

			body.setUuid(UUID.randomUUID().toString());
			String msgBody = JSON.toJSONString(body);

			String timeStamp = getTimeStamp(new Date());
			String digest = MD5Util.md5((pwd + msgBody + timeStamp).getBytes("UTF-8"));

			MessageHead messageHead = new MessageHead();
			messageHead.setCmd(cmd);
			messageHead.setDigest(digest);
			messageHead.setDigestType("md5");
			messageHead.setUserId(userId);
			messageHead.setTimeStamp(timeStamp);
			messageHead.setUserType("station");

			String head = GsonUtil.gson.toJson(messageHead);

			boolean islog = cmd.equals(Command.CMD_STATION_GET_MEMBER_LIST);
			if(!islog) {
				log.info("Http 请求：head=" + head + ",   body=" + msgBody);
			}
			else{
				log.info("Http 请求：获取彩民列表");
			}
			Ion.with(context)
					.load(url)
					.setTimeout(timeOut)
					.setBodyParameter("head", head)
					.setBodyParameter("body", msgBody)
					.asJsonObject()
					.setCallback(new FutureCallback<JsonObject>() {
						@Override
						public void onCompleted(Exception e, JsonObject result) {
							try {
								boolean islog = cmd.equals(Command.CMD_STATION_GET_MEMBER_LIST);
								if(!islog) {
									log.info("Http 响应：" + GsonUtil.gson.toJson(result));
								}
								else{
									log.info("Http 响应：获取彩民列表返回");
								}
								JsonElement jsonElement = result.get("body");
								T messageBody = GsonUtil.gson.fromJson(jsonElement.getAsString(), clz);
								listener.onSuccess(messageBody);
							}
							catch(Exception ex){
								ex.printStackTrace();
								listener.onFailed("网络不给力");
							}
						}
					});
		}
		catch(Exception e){
			e.printStackTrace();
			listener.onFailed("网络不给力");
		}
	}
	public static <T extends MessageBody> T post(Context context, String cmd, String userId,String pwd,MessageBody body, Class<T> clz){
		return post(context,Configuration.serverUrl,cmd, userId,pwd,body,clz);
	}

	public static <T extends MessageBody> T post(Context context,String url, String cmd, String userId,String pwd,MessageBody body, Class<T> clz){
		try {
			if(pwd == null){
				pwd = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
			}
			body.setUuid(UUID.randomUUID().toString());
			String msgBody = JSON.toJSONString(body);

			String timeStamp = getTimeStamp(new Date());
			String digest = MD5Util.md5((pwd + msgBody + timeStamp).getBytes("UTF-8"));

			MessageHead messageHead = new MessageHead();
			messageHead.setCmd(cmd);
			messageHead.setDigest(digest);
			messageHead.setDigestType("md5");
			messageHead.setUserId(userId);
			messageHead.setTimeStamp(timeStamp);
			messageHead.setUserType("station");

			String head = GsonUtil.gson.toJson(messageHead);
			log.info("Http 请求：head="+head+",   body="+msgBody);
			JsonObject jsonObject = Ion.with(context)
					.load(url)
					.setBodyParameter("head", head)
					.setBodyParameter("body", msgBody)
					.asJsonObject().get();
			log.info("Http 响应："+jsonObject);
			JsonElement jsonElement = jsonObject.get("body");
			T messageBody = GsonUtil.gson.fromJson(jsonElement.getAsString(), clz);
			return messageBody;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static void uploadLog(Context context,String phone,String... logMsgs){
		List<String> logs = new ArrayList<String>();
		for(String logMsg: logMsgs) {
			logs.add(logMsg);
		}
		UploadLogMessageBody request = new UploadLogMessageBody(logs, phone);
		post(context, Configuration.jServerUlr, "LOG", null,null, request, new OnHttpPostResultListener<UploadLogRepMessageBody>() {
			@Override
			public void onSuccess(UploadLogRepMessageBody body) {

			}
			@Override
			public void onFailed(String exc) {
			}
		}, UploadLogRepMessageBody.class,30000);
	}

	public static String getTimeStamp(Date date){
		return simpleDateFormat.format(date);
	}


	public static interface OnHttpPostResultListener<T extends MessageBody>{
		public void onSuccess(T body);
		public void onFailed(String exc);
	}
}
*/
