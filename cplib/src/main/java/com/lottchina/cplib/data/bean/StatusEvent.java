package com.lottchina.cplib.data.bean;

/**
 * Created by ljm on 16/12/31.
 */
public class StatusEvent {
    public static final int BOX_STATUS = 1;
    public static final int TERMINAL_STATUS = 2;
    public static final int SOCKET_STATUS = 3;

    private int status;

    private int type;

    public StatusEvent(int type,int status){
        this.type = type;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
