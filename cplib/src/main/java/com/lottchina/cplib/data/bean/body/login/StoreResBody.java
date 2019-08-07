package com.lottchina.cplib.data.bean.body.login;

import com.lottchina.cplib.data.bean.Account;
import com.lottchina.cplib.data.bean.ChannelBean;
import com.lottchina.cplib.data.bean.Customer;
import com.lottchina.cplib.data.bean.Station;
import com.lottchina.cplib.data.bean.Terminal;
import com.vcaidian.wclib.wc.BaseResponseBody;

import java.util.List;

/**
 * Author: Austin
 * Date: 19-5-6
 * Description:
 */
public class StoreResBody extends BaseResponseBody {
    private Account account;
    private List<ChannelBean> channel;
    private Customer customer;
    private Station station;
    private List<Terminal> terminal;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public List<ChannelBean> getChannel() {
        return channel;
    }

    public void setChannel(List<ChannelBean> channel) {
        this.channel = channel;
    }

    public List<Terminal> getTerminal() {
        return terminal;
    }

    public void setTerminal(List<Terminal> terminal) {
        this.terminal = terminal;
    }
}
