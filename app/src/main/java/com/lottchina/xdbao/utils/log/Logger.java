package com.lottchina.xdbao.utils.log;

import android.util.Log;

import com.lottchina.xdbao.net.CpSocketUtil;
import com.lottchina.xdbao.utils.ParseTimeUtil;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ljm on 2016/12/30.
 */

public class Logger {
    private Class clz;
    private LinkedBlockingQueue<String> logQueue = null;

    public Logger(Class clz, LinkedBlockingQueue<String> logQueue){
        this.clz = clz;
        this.logQueue = logQueue;
    }
    public void info(String format, Object... arguments){
        try {
            if (arguments != null) {
                for (Object obj : arguments) {
                    StringBuilder builder = new StringBuilder();
                    int index = format.indexOf("{}");
                    if (index != -1) {
                        builder.append(format.substring(0, index));
                        builder.append(obj == null ? "null" : obj.toString());
                        builder.append(format.substring(index + 2));
                        format = builder.toString();
                    }
                }
            }
        }
        catch(Exception e){
            format = "记录日志异常："+format+" "+e.getLocalizedMessage();
        }
        String logMsg = (format.length() > 10000 ? format.substring(0, 10000) : format);
        String log = String.format("[%s] [%s] [%s] [%s] %s：%s", ParseTimeUtil.getAllDate(System.currentTimeMillis()),LoggerFactory.getPhone(),LoggerFactory.getTerminalId(),LoggerFactory.getTerminalCode(),clz.getSimpleName(),logMsg);
        logQueue.offer(log);
        //RobotBox.getInstance().appendLog(" " + logMsg);
        Log.i(clz.getSimpleName(), log);
    }
    public void error(String format, Object... arguments){
        try {
            if (arguments != null) {
                for (Object obj : arguments) {
                    StringBuilder builder = new StringBuilder();
                    int index = format.indexOf("{}");
                    if (index != -1) {
                        builder.append(format.substring(0, index));
                        builder.append(obj == null ? "null" : obj.toString());
                        builder.append(format.substring(index + 2));
                        format = builder.toString();
                    }
                }
            }
        }
        catch(Exception e){
            format = "记录日志异常："+format+" "+e.getLocalizedMessage();
        }
        String logMsg = (format.length() > 10000 ? format.substring(0, 10000) : format);

        logQueue.offer(String.format("[%s] [%s] [%s] [%s] %s：%s", ParseTimeUtil.getAllDate(System.currentTimeMillis()),LoggerFactory.getPhone(),LoggerFactory.getTerminalId(),LoggerFactory.getTerminalCode(),clz.getSimpleName(),logMsg));
        CpSocketUtil.getInstance().appendLog(" " + logMsg);
        Log.e(clz.getSimpleName(), format);
    }

    public LinkedBlockingQueue<String> getLogQueue() {
        return logQueue;
    }

    public void setLogQueue(LinkedBlockingQueue<String> logQueue) {
        this.logQueue = logQueue;
    }

    public static void main(String args[]){
    }
}
