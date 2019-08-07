package com.lottchina.xdbao.protocol.message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zeng on 2017-12-20.
 */

public enum TicketStatus {
    un_paied(1, "未支付"),     //预售
    pre_sale(5, "预售"),     //预售
    printing(10, "出票中"),      //出票中
    print_err(15, "出票异常"),   //出票异常
    print_success(20, "出票成功"),     //出票成功
    hit(30, "中奖"),                     //中奖
    hit_unsure(35, "待确认"),          //待确认
    not_hit(40, "未中奖"),             //未中奖
    not_hit_sp(45, "未中奖"),   //未中奖(特殊)
    refund(50, "已退款"),              //已退款
    bonus_super(52, "超级大奖"),      //超级大奖
    bonus_big(55, "中大奖"),          //中大奖
    bonus_big_cashed(58, "大奖已兑"),     //大奖已兑
    funded(60, "已返奖"),             //已返奖
    can_bonus(61, "可兑奖"),         //可兑奖
    funded_sp(63, "已返奖"),  //已返奖(特殊)
    bonus_err(65, "兑奖异常"),      //兑奖异常
    cashed(70, "已兑奖"),           //已兑奖
    un_known(-1000, "未知状态");     //预售
    private int status;
    private String desc;

    private TicketStatus(int status, String desc){
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

    public static TicketStatus getTicketStatus(int status) {
        TicketStatus ticketStatus = Cache.map.get(status);
        if(ticketStatus==null){
            return un_known;
        }
        return ticketStatus;
    }

    public static class Cache{
        private static final Map<Integer, TicketStatus> map = new HashMap<>();
    }
}
