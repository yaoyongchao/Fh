package com.lottchina.xdbao.protocol.message.order;

import com.lottchina.xdbao.protocol.Command;
import com.lottchina.xdbao.protocol.message.Message;

/**
 * Author: Austin
 * Date: 19-5-13
 * Description:
 */
public class OrderTicketMessage extends Message {



    @Override
    public String toString() {
        return null;
    }

    @Override
    public String getCommandId() {
        return Command.CMD_STATION_TICKET_SCHEME_LIST;
    }
}
