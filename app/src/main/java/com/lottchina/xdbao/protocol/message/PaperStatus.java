package com.lottchina.xdbao.protocol.message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zeng on 2017-12-20.
 */

public enum PaperStatus {
    init(0, "未出纸"),     //初始状态
    taking(10, "未出纸取走冻结"),         //未出纸取走冻结
    taken(20, "已出纸"),        //已经出纸
    paper_taking(30, "已出纸取走冻结"),       //已出纸取走冻结
    user_taken(40, "已取票");      //用户取走

    private int status;
    private String desc;

    private PaperStatus(int status, String desc){
        this.status = status;
        this.desc = desc;
        Cache.map.put(status, this);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static PaperStatus getPaperStatus(int status) {
        return Cache.map.get(status);
    }

    public static class Cache{
        private static final Map<Integer, PaperStatus> map = new HashMap<>();
    }
}
