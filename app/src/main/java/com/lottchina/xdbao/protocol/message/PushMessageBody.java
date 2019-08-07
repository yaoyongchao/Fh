package com.lottchina.xdbao.protocol.message;

import java.util.List;

public class PushMessageBody extends MessageBody{

    private List<StationMessage> data;

    private String ri;
    private int type;
    public List<StationMessage> getData() {
        return data;
    }

    public void setData(List<StationMessage> data) {
        this.data = data;
    }

    public String getRi() {
        return ri;
    }

    public void setRi(String ri) {
        this.ri = ri;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PushMessageBody{" +
                "data=" + data +
                ", ri='" + ri + '\'' +
                ", type=" + type +
                '}';
    }
}
