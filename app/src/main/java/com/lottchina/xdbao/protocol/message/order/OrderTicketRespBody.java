package com.lottchina.xdbao.protocol.message.order;

import com.lottchina.xdbao.protocol.message.Error;
import com.lottchina.xdbao.protocol.message.MessageBody;

import java.util.List;

/**
 * Author: Austin
 * Date: 19-5-13
 * Description: 订单列表响应体
 */
public class OrderTicketRespBody extends MessageBody {
    private Error err;
    private List<OrderTicket> data;
    private int cur_time;

    public Error getErr() {
        return err;
    }

    public void setErr(Error err) {
        this.err = err;
    }

    public List<OrderTicket> getData() {
        return data;
    }

    public void setData(List<OrderTicket> data) {
        this.data = data;
    }

    public int getCur_time() {
        return cur_time;
    }

    public void setCur_time(int cur_time) {
        this.cur_time = cur_time;
    }
}
