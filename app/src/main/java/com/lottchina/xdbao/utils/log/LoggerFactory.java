package com.lottchina.xdbao.utils.log;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ljm on 2016/12/30.
 */

public class LoggerFactory {

    private static LinkedBlockingQueue<String> logQueue = new LinkedBlockingQueue<String>(200);

    private static String phone;

    private static Integer terminalId;

    private static String terminalCode;

    public static Logger getLogger(Class clz){
        return new Logger(clz,logQueue);
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        LoggerFactory.phone = phone;
    }

    public static Integer getTerminalId() {
        return terminalId;
    }

    public static void setTerminalId(Integer terminalId) {
        LoggerFactory.terminalId = terminalId;
    }

    public static String getTerminalCode() {
        return terminalCode;
    }

    public static void setTerminalCode(String terminalCode) {
        LoggerFactory.terminalCode = terminalCode;
    }

    public static LinkedBlockingQueue<String> getLogQueue() {
        return logQueue;
    }

    public static void setLogQueue(LinkedBlockingQueue<String> logQueue) {
        LoggerFactory.logQueue = logQueue;
    }
}
