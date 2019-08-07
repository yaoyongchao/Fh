package com.lottchina.xdbao.protocol.message.order;

import com.lottchina.baselib.utils.GsonUtil;
import com.lottchina.xdbao.protocol.message.Message;
import com.lottchina.xdbao.protocol.message.MessageHead;

/**
 * Author: Austin
 * Date: 19-5-13
 * Description:
 */
public class OrderTicketRepMessage extends Message {
    private MessageHead messageHead;
    private OrderTicketRespBody messageBody;

    public OrderTicketRepMessage(MessageHead messageHead, String bodyMsg) {
        this.messageHead = messageHead;
//        this.messageBody = messageBody;
        this.messageBody = GsonUtil.GsonToBean(bodyMsg,OrderTicketRespBody.class);
    }

    @Override
    public MessageHead getMessageHead() {
        return messageHead;
    }

    public void setMessageHead(MessageHead messageHead) {
        this.messageHead = messageHead;
    }

    @Override
    public OrderTicketRespBody getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(OrderTicketRespBody messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public String toString() {
        return "OrderTicketRepMessage{" +
                "messageHead=" + messageHead +
                ", messageBody=" + messageBody +
                '}';
    }

    @Override
    public String getCommandId() {
        return messageHead.getCmd();
    }
}
