package com.lottchina.cplib.data.bean;

/**
 * Author: Austin
 * Date: 19-5-6
 * Description:
 */
public class Customer {

    /**
     * id : 4
     * is_info_open : 0
     * nickname : 国民彩票店
     * province : 11
     * reg_time : 1546404509
     * username : 18039364720
     */

    private int id;
    private int is_info_open;
    private String nickname;
    private int province;
    private int reg_time;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_info_open() {
        return is_info_open;
    }

    public void setIs_info_open(int is_info_open) {
        this.is_info_open = is_info_open;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getReg_time() {
        return reg_time;
    }

    public void setReg_time(int reg_time) {
        this.reg_time = reg_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
