package com.lottchina.xdbao.protocol.message.order;

import com.lottchina.xdbao.protocol.message.CondBean;
import com.lottchina.xdbao.protocol.message.MessageBody;
import com.lottchina.xdbao.protocol.message.OpBean;

/**
 * Author: Austin
 * Date: 19-5-13
 * Description: 订单出票列表  请求体
 */
public class OrderTicketBody extends MessageBody {
    private CondBean cond;
    private OpBean op;
    private PoolCondBean pool_cond;
    private int terminal_id;

    public CondBean getCond() {
        return cond;
    }

    public void setCond(CondBean cond) {
        this.cond = cond;
    }

    public OpBean getOp() {
        return op;
    }

    public void setOp(OpBean op) {
        this.op = op;
    }

    public PoolCondBean getPool_cond() {
        return pool_cond;
    }

    public void setPool_cond(PoolCondBean pool_cond) {
        this.pool_cond = pool_cond;
    }

    public int getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(int terminal_id) {
        this.terminal_id = terminal_id;
    }
}
