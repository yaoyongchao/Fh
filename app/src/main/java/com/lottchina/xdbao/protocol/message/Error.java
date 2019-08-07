package com.lottchina.xdbao.protocol.message;

/**
 * Created by ljm on 2017/2/9.
 */

public class Error {
    private int code;
    private String des;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", des='" + des + '\'' +
                '}';
    }
}
