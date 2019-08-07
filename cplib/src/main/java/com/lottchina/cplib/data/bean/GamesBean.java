package com.lottchina.cplib.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @Author: Austin
 * @Date: 19-4-12
 * @Description: 彩种实体类
 */
public class GamesBean implements Parcelable {


    /**
     * 100 : 1
     * 101 : 1
     * 200 : 1
     * 201 : 1
     * 202 : 1
     * 203 : 1
     * 205 : 1
     * 207 : 1
     * 301 : 1
     * 401 : 1
     * 402 : 1
     * 601 : 1
     */

    @SerializedName("100")
    public int _$100;
    @SerializedName("101")
    public int _$101;
    @SerializedName("200")
    public int _$200;
    @SerializedName("201")
    public int _$201;
    @SerializedName("202")
    public int _$202;
    @SerializedName("203")
    public int _$203;
    @SerializedName("205")
    public int _$205;
    @SerializedName("207")
    public int _$207;
    @SerializedName("301")
    public int _$301;
    @SerializedName("401")
    public int _$401;
    @SerializedName("402")
    public int _$402;
    @SerializedName("601")
    public int _$601;
    @SerializedName("602")
    public int _$602;


    public int get_$100() {
        return _$100;
    }

    public void set_$100(int _$100) {
        this._$100 = _$100;
    }

    public int get_$101() {
        return _$101;
    }

    public void set_$101(int _$101) {
        this._$101 = _$101;
    }

    public int get_$200() {
        return _$200;
    }

    public void set_$200(int _$200) {
        this._$200 = _$200;
    }

    public int get_$201() {
        return _$201;
    }

    public void set_$201(int _$201) {
        this._$201 = _$201;
    }

    public int get_$202() {
        return _$202;
    }

    public void set_$202(int _$202) {
        this._$202 = _$202;
    }

    public int get_$203() {
        return _$203;
    }

    public void set_$203(int _$203) {
        this._$203 = _$203;
    }

    public int get_$205() {
        return _$205;
    }

    public void set_$205(int _$205) {
        this._$205 = _$205;
    }

    public int get_$207() {
        return _$207;
    }

    public void set_$207(int _$207) {
        this._$207 = _$207;
    }

    public int get_$301() {
        return _$301;
    }

    public void set_$301(int _$301) {
        this._$301 = _$301;
    }

    public int get_$401() {
        return _$401;
    }

    public void set_$401(int _$401) {
        this._$401 = _$401;
    }

    public int get_$402() {
        return _$402;
    }

    public void set_$402(int _$402) {
        this._$402 = _$402;
    }

    public int get_$601() {
        return _$601;
    }

    public void set_$601(int _$601) {
        this._$601 = _$601;
    }

    public int get_$602() {
        return _$602;
    }

    public void set_$602(int _$602) {
        this._$602 = _$602;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._$100);
        dest.writeInt(this._$101);
        dest.writeInt(this._$200);
        dest.writeInt(this._$201);
        dest.writeInt(this._$202);
        dest.writeInt(this._$203);
        dest.writeInt(this._$205);
        dest.writeInt(this._$207);
        dest.writeInt(this._$301);
        dest.writeInt(this._$401);
        dest.writeInt(this._$402);
        dest.writeInt(this._$601);
        dest.writeInt(this._$602);
    }

    public GamesBean() {
    }

    protected GamesBean(Parcel in) {
        this._$100 = in.readInt();
        this._$101 = in.readInt();
        this._$200 = in.readInt();
        this._$201 = in.readInt();
        this._$202 = in.readInt();
        this._$203 = in.readInt();
        this._$205 = in.readInt();
        this._$207 = in.readInt();
        this._$301 = in.readInt();
        this._$401 = in.readInt();
        this._$402 = in.readInt();
        this._$601 = in.readInt();
        this._$602 = in.readInt();
    }

    public static final Parcelable.Creator<GamesBean> CREATOR = new Parcelable.Creator<GamesBean>() {
        @Override
        public GamesBean createFromParcel(Parcel source) {
            return new GamesBean(source);
        }

        @Override
        public GamesBean[] newArray(int size) {
            return new GamesBean[size];
        }
    };
}