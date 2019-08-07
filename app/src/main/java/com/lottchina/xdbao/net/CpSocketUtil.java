package com.lottchina.xdbao.net;

import android.content.Context;
import android.util.Log;

import com.lottchina.baselib.utils.L;
import com.lottchina.cplib.data.bean.StatusEvent;
import com.lottchina.cplib.data.bean.Terminal;
import com.lottchina.xdbao.protocol.PushMessageCmd;
import com.lottchina.xdbao.protocol.SocketStatus;
import com.lottchina.xdbao.protocol.message.BindTerminalMessage;
import com.lottchina.xdbao.protocol.message.BindTerminalRepMessage;
import com.lottchina.xdbao.protocol.message.PushMessage;
import com.lottchina.xdbao.protocol.message.StationMessage;
import com.lottchina.xdbao.protocol.smproxy.SMProxy;
import com.lottchina.xdbao.protocol.util.Args;
import com.lottchina.xdbao.ui.common.MyRxStatus;
import com.lottchina.xdbao.utils.CommonUtil;
import com.lottchina.xdbao.utils.ParseTimeUtil;
import com.lottchina.xdbao.utils.log.Logger;
import com.lottchina.xdbao.utils.log.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gorden.rxbus2.RxBus;

/**
 * Created by chao on 19-2-25.
 * Sokcet 工具类   CP == 出票大师。
 */

public class CpSocketUtil {
    private static final Logger log = LoggerFactory.getLogger(CpSocketUtil.class);
    private SMProxy client;
    private MessageListener messageListener;
    private boolean socketConnected = false;
    private static CpSocketUtil robotBox = null;
    private Context context;
    private List<String> logs = new ArrayList<String>();
    private static Terminal terminal;

    public synchronized static CpSocketUtil getInstance(){
        if(robotBox==null){
            robotBox = new CpSocketUtil();
        }
        return robotBox;
    }

    public void init(final Context context) {
        this.context = context;
    }

    public static void setTerminal(Terminal ter) {
        terminal = ter;
    }

    //启动Socket连接
    public void startSocketComm(int userId,String token,MessageListener listener){
        this.messageListener = listener;
        if(client!=null && client.available()){
            return;
        }
        Args argstr = new Args();
        argstr.set("host", CpApi.INSTANCE.getCP_SERVER_IP());
        argstr.set("port", CpApi.INSTANCE.getCP_SERVER_PORT());
        argstr.set("userId",userId);
        argstr.set("token", token);
        argstr.set("read-timeout", 60);
        argstr.set("heartbeat-interval", 10);
        argstr.set("reconnect-interval", 5);
        argstr.set("transaction-timeout", 30);
        client=new SMProxy(argstr);
        client.setMessageListener(new SMProxy.MessageListener() {
            @Override
            public void onPushMessage(PushMessage msg) {
                log.info("接收到推送消息",msg.getMessageBody().toString());
                if (msg.getMessageBody().getType() == 20) {//20表示被踢了
                    log.info("您被踢了!");
//                    EventBus.getDefault().post("20");
                    CpSocketUtil.getInstance().stop();
                } else {
                    log.info("收到了Type 为10 新的消息!");
                    List<StationMessage> otherList = new ArrayList<StationMessage>();
                    List<StationMessage> stationMessageList = msg.getMessageBody().getData();
                    if(stationMessageList==null){
                        return;
                    }
                    Set<String> cmdSet = new HashSet<String>();
                    for(StationMessage stationMessage : stationMessageList){
                        int type = stationMessage.getType();
                        if(type== PushMessageCmd.CMD_CLOSE_TERMINAL.getCmd()){
                            /*String detail = stationMessage.getDetail();
                            log.info("接收到关机命令 {}",detail);
                            CloseTerminalBody closeTerminalBody = JSON.parseObject(detail,CloseTerminalBody.class);
                            if(terminal==null||closeTerminalBody.getTerminal_id()!=terminal.getId()){
                                continue;
                            }
                            log.info("检查关机");
                            long time = closeTerminalBody.getTime();
                            long currentTime = System.currentTimeMillis()+getSysTimeDiffValue();
                            long diffTime = Math.abs(currentTime-time);
                            log.info("检查关机时间差 {}",diffTime);
                            if(diffTime>60000){
                                continue;
                            }
                            String cmd = String.valueOf(type);
                            if(cmdSet.contains(cmd)){
                                continue;
                            }
                            cmdSet.add(cmd);
//							closeTerminal();
                            continue;*/
                        }
                        /*else if(type==PushMessageCmd.CMD_DATE_REPORT.getCmd()){
                            String detail = stationMessage.getDetail();
                            log.info("接收到查询报表命令 {}",detail);
                            ReportQueryBody reportQueryBody = JSON.parseObject(detail,ReportQueryBody.class);
                            if(terminal==null||reportQueryBody.getTerminal_id()!=terminal.getId()){
                                continue;
                            }
                            long time = reportQueryBody.getTime();
                            long currentTime = System.currentTimeMillis()+getSysTimeDiffValue();
                            long diffTime = Math.abs(currentTime-time);
                            if(diffTime>60000){
                                continue;
                            }
                            String cmd = String.format("%s-%s-%s-%s",type,reportQueryBody.getDate(),reportQueryBody.getStart_date(),reportQueryBody.getEnd_date());
                            if(cmdSet.contains(cmd)){
                                continue;
                            }
                            cmdSet.add(cmd);
//							queryReport(reportQueryBody);
                            continue;
                        }*/
                        else if(type== PushMessageCmd.CMD_PRINT_TIKCET.getCmd()){
							/*String detail = stationMessage.getDetail();
							log.info("接收到打票命令 {}",detail);
							PrintTicketBody printTicketBody = JSON.parseObject(detail,PrintTicketBody.class);
							if(terminal==null||printTicketBody.getTerminal_id()!=terminal.getId()){
								continue;
							}
							if(printTicketBody.isEncrypt()) {
								byte[] bytes = FuncUtils.hexString2Bytes(printTicketBody.getCrypto());
								byte[] decryptBytes = Encrypt.decrypt(bytes);
								printDataToPrinter(decryptBytes);
							}
							else{
								byte[] bytes = FuncUtils.hexString2Bytes(printTicketBody.getCrypto());
								printDataToPrinter(bytes);
							}*/
                        }
                        else if(type== PushMessageCmd.CMD_RESET_PRINT_CENTER_IP.getCmd()){
                            String detail = stationMessage.getDetail();
                            log.info("接收到修改出票中心Ip命令 {}",detail);
//                            ResetPrintCenterIpBody resetPrintCenterIpBody = JSON.parseObject(detail,ResetPrintCenterIpBody.class);
//                            Configuration.setPrintCenterServerIp(resetPrintCenterIpBody.getPrintCenterIp());
                        }
                        /*else if(type==PushMessageCmd.UPDATE_BOX_DRIVER.getCmd()){
                            String detail = stationMessage.getDetail();
                            log.info("接收到更新盒子驱动命令 {}",detail);
                            UpdateBoxDriverBody updateBoxDriverBody = JSON.parseObject(detail,UpdateBoxDriverBody.class);
                            if(terminal==null||updateBoxDriverBody.getTerminal_id()!=terminal.getId()){
                                continue;
                            }
                            EventBus.getDefault().post(new UpdateBoxDriverEvent(updateBoxDriverBody.getFileId()));
                        }*/
                        else if(type== PushMessageCmd.UPDATE_APP_NEW.getCmd()){
                            String detail = stationMessage.getDetail();
                            log.info("接收到更新App版本命令 {}",detail);
//                            UpdateAppVersionBody updateAppVersionBody = JSON.parseObject(detail,UpdateAppVersionBody.class);
//                            EventBus.getDefault().post(new NewAppVersionEvent(updateAppVersionBody.getMsg(),updateAppVersionBody.getUrl()));
                        }
                        else if(type== PushMessageCmd.QUERY_11X5_OPEN_NUMBER.getCmd()){
                            String detail = stationMessage.getDetail();
                            log.info("接收到是否获取11选5开奖号码命令 {}",detail);
//                            Query11x5OpenNumberBody query11x5OpenNumberBody = JSON.parseObject(detail,Query11x5OpenNumberBody.class);
//                            EventBus.getDefault().post(new OpenNumberEvent(query11x5OpenNumberBody.isQuery()));
                        }
                        otherList.add(stationMessage);
                    }
                    if(messageListener!=null) {
                        messageListener.onMessage(otherList);
                    }
                }
            }
            @Override
            public void onSocketConnection(final boolean isConnected) {
                Log.e("aa","Socket链接状态----" + isConnected);
                log.info("Socket连接状态：{}",isConnected);
                socketConnected = isConnected;
                if(!socketConnected){
                    try {
//                        if (XDBApplication.getInstance().getIntValue("UserId",0) !=0)
//                            YSApplication.getInstance().play(YSApplication.AUDIO_FILE_ERROR);
                    } catch (Exception e) {
                        Log.e("CpSocketUtil","--" + e.getMessage().toString());
                    }
                }


//                EventBus.getDefault().post(new StatusEvent(StatusEvent.SOCKET_STATUS,isConnected? SocketStatus.SOCKET_CONNECTED.getStatus() : SocketStatus.SOCKET_UNCONNECTED.getStatus()));
                RxBus.get().send(MyRxStatus.SOCKET_LINK,new StatusEvent(StatusEvent.SOCKET_STATUS,isConnected? SocketStatus.SOCKET_CONNECTED.getStatus() : SocketStatus.SOCKET_UNCONNECTED.getStatus()));
                if (socketConnected) {
                    bindTerminal();
                }

                if (client!=null)
                    client.setTag(null);

            }
        });
        client.start();
    }

    public void stop(){
        if(client!=null){
            client.stop();
            client = null;
        }
        terminal = null;
    }

    public boolean isSocketConnected() {
        return socketConnected;
    }

    public SMProxy getClient() {
        return client;
    }


    public void appendLog(String msg){
        synchronized (logs) {
            if (logs.size() > 200) {
                logs.remove(0);
            }
            logs.add(ParseTimeUtil.getHourMinuteSencond(System.currentTimeMillis()) + " " + msg);
        }
    }

    public interface MessageListener{
        public void onMessage(List<StationMessage> msgList);
    }

    private void bindTerminal() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                L.Companion.i("开始绑定终端");
                Terminal terminal = CommonUtil.getTerminal();
                BindTerminalMessage bindTerminalMessage = new BindTerminalMessage(terminal.getId());

                try {
                    BindTerminalRepMessage bindTerminalRepMessage = (BindTerminalRepMessage) CpSocketUtil.getInstance().getClient().send(bindTerminalMessage);
                    L.Companion.i("绑定终端成功!"+bindTerminalRepMessage.getUuId());
                } catch (Exception e) {
                    L.Companion.e("绑定终端失败：" + e.toString());
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
