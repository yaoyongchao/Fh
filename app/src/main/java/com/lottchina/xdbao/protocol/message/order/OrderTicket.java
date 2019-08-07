package com.lottchina.xdbao.protocol.message.order;

/**
 * Author: Austin
 * Date: 19-5-13
 * Description:
 */
public class OrderTicket {

    /**
     * amount : 14000
     * bet_type : 2:3*1
     * cancel_count : 0
     * cur_term_code : 0
     * customer_id : 10
     * end_time : 1557763200
     * game_id : 201
     * max_bonus : 222097
     * multiple : 70
     * number : 20190513001:02:0(-1)@1.73;20190513002:01:0@3.50;20190513003:02:0(-2)@2.62
     * play_type : 10
     * print_count : 0
     * scheme_id : 2019051300000000003
     * start_term_code : 0
     * term_count : 0
     * ticket_count : 2
     * type : 10
     * wait_print_count : 2
     */

    private int amount;
    private String bet_type;
    private int cancel_count;
    private int cur_term_code;
    private int customer_id;
    private int end_time;
    private int game_id;
    private int max_bonus;
    private int multiple;
    private String number;
    private String play_type;
    private int print_count;
    private String scheme_id;
    private int start_term_code;
    private int term_count;
    private int ticket_count;
    private int type;
    private int wait_print_count;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBet_type() {
        return bet_type;
    }

    public void setBet_type(String bet_type) {
        this.bet_type = bet_type;
    }

    public int getCancel_count() {
        return cancel_count;
    }

    public void setCancel_count(int cancel_count) {
        this.cancel_count = cancel_count;
    }

    public int getCur_term_code() {
        return cur_term_code;
    }

    public void setCur_term_code(int cur_term_code) {
        this.cur_term_code = cur_term_code;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getMax_bonus() {
        return max_bonus;
    }

    public void setMax_bonus(int max_bonus) {
        this.max_bonus = max_bonus;
    }

    public int getMultiple() {
        return multiple;
    }

    public void setMultiple(int multiple) {
        this.multiple = multiple;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPlay_type() {
        return play_type;
    }

    public void setPlay_type(String play_type) {
        this.play_type = play_type;
    }

    public int getPrint_count() {
        return print_count;
    }

    public void setPrint_count(int print_count) {
        this.print_count = print_count;
    }

    public String getScheme_id() {
        return scheme_id;
    }

    public void setScheme_id(String scheme_id) {
        this.scheme_id = scheme_id;
    }

    public int getStart_term_code() {
        return start_term_code;
    }

    public void setStart_term_code(int start_term_code) {
        this.start_term_code = start_term_code;
    }

    public int getTerm_count() {
        return term_count;
    }

    public void setTerm_count(int term_count) {
        this.term_count = term_count;
    }

    public int getTicket_count() {
        return ticket_count;
    }

    public void setTicket_count(int ticket_count) {
        this.ticket_count = ticket_count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWait_print_count() {
        return wait_print_count;
    }

    public void setWait_print_count(int wait_print_count) {
        this.wait_print_count = wait_print_count;
    }
}
