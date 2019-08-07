package com.lottchina.xdbao.utils.log;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 将Log日志写入文件中
 * <p>
 * 使用单例模式是因为要初始化文件存放位置
 * <p>
 * Created by waka on 2016/3/14.
 */
public class LogToFile {

    private String TAG = "LogToFile";

    private String logPath = null;//log日志存放路径

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);//日期格式;

    private Date date = new Date();//因为log日志是使用日期命名的，使用静态成员变量主要是为了在整个程序运行期间只存在一个.log文件中;

    private FileOutputStream fos = null;//FileOutputStream会自动调用底层的close()方法，不用关闭
    private BufferedWriter bw = null;
    private String fileName;

    private long lastCheckTime = 0 ;
    public LogToFile(Context context) {
        logPath = getFilePath(context) + "/Logs";//获得文件储存路径,在后面加"/Logs"建立子文件夹
    }

    /**
     * 获得文件存储路径
     *
     * @return
     */
    private String getFilePath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable()) {//如果外部储存可用
            return context.getExternalFilesDir(null).getPath();//获得外部存储路径,默认路径为 /storage/emulated/0/Android/data/com.waka.workspace.logtofile/files/Logs/log_2016-03-14_16-15-09.log
        } else {
            return context.getFilesDir().getPath();//直接存在/data/data里，非root手机是看不到的
        }
    }

    public void removeOldLogFiles(){
        String logFilePath = logPath + "/";
        File logFilePathFile = new File(logFilePath);
        if(logFilePathFile.exists() && logFilePathFile.isDirectory()){
            File[] files = logFilePathFile.listFiles();
            for(File file : files){
                String fileName = file.getName();
                if(fileName.contains(".log")){
                    try {
                        String dateFile = fileName.substring(0, 10);
                        String[] sdt = dateFile.split("-");
                        int year = Integer.parseInt(sdt[0]);
                        int month = Integer.parseInt(sdt[1]);
                        int date = Integer.parseInt(sdt[2]);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year,month-1,date,0,0,0);
                        if((System.currentTimeMillis()-calendar.getTimeInMillis())>1000*3*24*3600){
                            file.delete();
                        }
                    }
                    catch(Exception e){
                    }
                }
            }
        }


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        for(int i=0;i<20;i++) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            String fileName = logPath + "/" + dateFormat.format(calendar.getTime()) + ".log";
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 将log信息写入文件中
     *
     * @param msg
     */
    public boolean writeToFile(List<String> msg) {
        String nowFileName = logPath + "/" + dateFormat.format(new Date()) + ".log";//log日志名，使用时间命名，保证不重复
        if(!nowFileName.equals(fileName)) {
            //如果父路径不存在
            File file = new File(logPath);
            if (!file.exists()) {
                file.mkdirs();//创建父路径
            }
            fileName = nowFileName;
            close();
        }

        if(System.currentTimeMillis()-lastCheckTime>600000) {
            File file = new File(fileName);
            if (file.exists()) {
                long fileSize = file.length() / 1000000;
                if (fileSize >= 30) {
                    String tempFileName = null;
                    for (int i = 1; i < 20; i++) {
                        tempFileName = fileName + "." + i;
                        File tempFile = new File(tempFileName);
                        if (!tempFile.exists()) {
                            break;
                        }
                    }
                    close();
                    file.renameTo(new File(tempFileName));
                }
            }
            lastCheckTime = System.currentTimeMillis();
        }

        try {
            if(fos==null && bw ==null){
                fos = new FileOutputStream(fileName, true);//这里的第二个参数代表追加还是覆盖，true为追加，flase为覆盖
                bw = new BufferedWriter(new OutputStreamWriter(fos));
            }
            for(String log:msg) {
                bw.write(log+"\r\n");
            }
            bw.flush();
            return true;

        }catch (IOException e) {
            e.printStackTrace();
            close();
        } finally {
        }
        return false;
    }

    private void close(){
        try{
            if(bw!=null) {
                bw.close();
            }
        }
        catch (Exception ex){
        }
        bw = null;
        try{
            if(fos!=null) {
                fos.close();
            }
        }
        catch (Exception ex){
        }
        fos = null;
    }

}