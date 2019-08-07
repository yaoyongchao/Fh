package com.lottchina.xdbao.utils.log;

import android.content.Context;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by ljm on 2017/9/21.
 */

public class LogUploadThread implements Runnable {

    LinkedBlockingQueue<String> logQueue = null;

    private Context context;

    private boolean uploadLog = false;

    private String phone;

    private LogToFile logToFile;

    private boolean removeOldFiles = false;

    private long lastCheckTime = 0;

    private boolean stop = false;

    private ThreadPoolExecutor httpLogSenderExcutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public LogUploadThread(Context context, LinkedBlockingQueue<String> logQueue, String phone) {
        this.context = context;
        this.logQueue = logQueue;
        this.phone = phone;
        LoggerFactory.setPhone(phone);
        logToFile = new LogToFile(context);
    }

    @Override
    public void run() {
        ArrayList<String> msgList = new ArrayList<String>();
        boolean writeFileLog = true;
        while(!isStop()){
            if(!removeOldFiles){
                removeOldFiles = true;
                logToFile.removeOldLogFiles();
            }
            if(msgList.size()>=5){
                if(!writeFileLog) {
                    writeFileLog = logToFile.writeToFile(msgList);
                }
                if(writeFileLog){
                    msgList.clear();
                }
            }
            if(msgList.size()<5) {
                writeFileLog = false;
                String msg = logQueue.poll();
                if (msg != null) {
                    msgList.add(msg);
                }
                else{
                    try {
                        Thread.sleep(1000);
                    }
                    catch(Exception e){}
                    continue;
                }
            }
        }
    }

}
