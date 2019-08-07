package com.lottchina.cplib.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @Author: Austin
 * @Date: 19-4-15
 * @Description: 终端
 */
public class Terminal implements Parcelable {

    public Integer id;
    public String block_num;
    public String code;
    public String games;
    public Integer hard_type_id;
    public String hard_type;
    public String sale_num;
    public String sale_pass;
    public String ukey;
    public Integer enter_sep;
    public String game_config;
    public Integer user_status;
    public String robotInitPrefix;
    public Integer is_sale_online;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlock_num() {
        return block_num;
    }

    public void setBlock_num(String block_num) {
        this.block_num = block_num;
    }

    public Integer getHard_type_id() {
        return hard_type_id;
    }

    public void setHard_type_id(Integer hard_type_id) {
        this.hard_type_id = hard_type_id;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHard_type() {
        return hard_type;
    }

    public void setHard_type(String hard_type) {
        this.hard_type = hard_type;
    }

    public String getUkey() {
        return ukey;
    }

    public void setUkey(String ukey) {
        this.ukey = ukey;
    }

    public String getSale_num() {
        return sale_num;
    }

    public void setSale_num(String sale_num) {
        this.sale_num = sale_num;
    }

    public String getSale_pass() {
        return sale_pass;
    }

    public void setSale_pass(String sale_pass) {
        this.sale_pass = sale_pass;
    }

    public Integer getEnter_sep() {
        return enter_sep;
    }

    public void setEnter_sep(Integer enter_sep) {
        this.enter_sep = enter_sep;
    }

    public String getGame_config() {
        return game_config;
    }

    public void setGame_config(String game_config) {
        this.game_config = game_config;
    }


    public Integer getUser_status() {
        return user_status;
    }

    public void setUser_status(Integer user_status) {
        this.user_status = user_status;
    }

    public String getRobotInitPrefix() {
        return robotInitPrefix;
    }

    public void setRobotInitPrefix(String robotInitPrefix) {
        this.robotInitPrefix = robotInitPrefix;
    }

    public Integer getIs_sale_online() {
        return is_sale_online;
    }

    public void setIs_sale_online(Integer is_sale_online) {
        this.is_sale_online = is_sale_online;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.block_num);
        dest.writeString(this.code);
        dest.writeString(this.games);
        dest.writeValue(this.hard_type_id);
        dest.writeString(this.hard_type);
        dest.writeString(this.sale_num);
        dest.writeString(this.sale_pass);
        dest.writeString(this.ukey);
        dest.writeValue(this.enter_sep);
        dest.writeString(this.game_config);
        dest.writeValue(this.user_status);
        dest.writeString(this.robotInitPrefix);
        dest.writeValue(this.is_sale_online);
    }

    public Terminal() {
    }

    protected Terminal(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.block_num = in.readString();
        this.code = in.readString();
        this.games = in.readString();
        this.hard_type_id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.hard_type = in.readString();
        this.sale_num = in.readString();
        this.sale_pass = in.readString();
        this.ukey = in.readString();
        this.enter_sep = (Integer) in.readValue(Integer.class.getClassLoader());
        this.game_config = in.readString();
        this.user_status = (Integer) in.readValue(Integer.class.getClassLoader());
        this.robotInitPrefix = in.readString();
        this.is_sale_online = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Terminal> CREATOR = new Parcelable.Creator<Terminal>() {
        @Override
        public Terminal createFromParcel(Parcel source) {
            return new Terminal(source);
        }

        @Override
        public Terminal[] newArray(int size) {
            return new Terminal[size];
        }
    };
}