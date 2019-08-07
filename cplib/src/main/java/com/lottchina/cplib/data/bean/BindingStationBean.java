package com.lottchina.cplib.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Author: Austin
 * Date: 19-4-15
 * Description: 绑定 Station
 */
public class BindingStationBean implements Parcelable {

    /**
     * addr : 北京市丰台区南四环西路辅路
     * card_no : 41092219890307495X
     * descrip : 微彩内部测试
     * dist : 12233175
     * face_pic_id : 8
     * games : {"100":1,"101":1,"200":1,"201":1,"202":1,"203":1,"205":1,"207":1,"301":1,"401":1,"402":1,"601":1}
     * id : 4
     * is_info_open : 0
     * lantitude : 39.829559326171875
     * longitude : 116.29912567138672
     * name : 勇超
     * nickname : 国民彩票店
     * notice :
     * sale_id : 5107853
     * sale_pic_id : 7
     * self_c_id : 3
     * tel_phone : 18039364720
     * terminals : [{"code":"1111222222","games":"201,202,301,200,203,205,207,401,402,601","id":2},{"code":"1346497643215","games":"201,202,301,200,203,205,207,401,402,601,100,101","id":16}]
     * username : 18039364720
     * weixin_account_id :
     * weixin_account_pic_id : -1
     * weixin_account_url : https://u.wechat.com/EJhHk_pjfpkWqVwu_7uf4Pc
     * weixin_rec_url :
     * zfb_id :
     */

    public String addr;
    public String card_no;
    public String descrip;
    public int dist;
    public int face_pic_id;
    public GamesBean games;
    public int id;
    public int is_info_open;
    public double lantitude;
    public double longitude;
    public String name;
    public String nickname;
    public String notice;
    public String sale_id;
    public int sale_pic_id;
    public int self_c_id;
    public String tel_phone;
    public String username;
    public String weixin_account_id;
    public int weixin_account_pic_id;
    public String weixin_account_url;
    public String weixin_rec_url;
    public String zfb_id;
    public List<Terminal> terminals;



    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getFace_pic_id() {
        return face_pic_id;
    }

    public void setFace_pic_id(int face_pic_id) {
        this.face_pic_id = face_pic_id;
    }

    public GamesBean getGames() {
        return games;
    }

    public void setGames(GamesBean games) {
        this.games = games;
    }

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

    public double getLantitude() {
        return lantitude;
    }

    public void setLantitude(double lantitude) {
        this.lantitude = lantitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getSale_id() {
        return sale_id;
    }

    public void setSale_id(String sale_id) {
        this.sale_id = sale_id;
    }

    public int getSale_pic_id() {
        return sale_pic_id;
    }

    public void setSale_pic_id(int sale_pic_id) {
        this.sale_pic_id = sale_pic_id;
    }

    public int getSelf_c_id() {
        return self_c_id;
    }

    public void setSelf_c_id(int self_c_id) {
        this.self_c_id = self_c_id;
    }

    public String getTel_phone() {
        return tel_phone;
    }

    public void setTel_phone(String tel_phone) {
        this.tel_phone = tel_phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWeixin_account_id() {
        return weixin_account_id;
    }

    public void setWeixin_account_id(String weixin_account_id) {
        this.weixin_account_id = weixin_account_id;
    }

    public int getWeixin_account_pic_id() {
        return weixin_account_pic_id;
    }

    public void setWeixin_account_pic_id(int weixin_account_pic_id) {
        this.weixin_account_pic_id = weixin_account_pic_id;
    }

    public String getWeixin_account_url() {
        return weixin_account_url;
    }

    public void setWeixin_account_url(String weixin_account_url) {
        this.weixin_account_url = weixin_account_url;
    }

    public String getWeixin_rec_url() {
        return weixin_rec_url;
    }

    public void setWeixin_rec_url(String weixin_rec_url) {
        this.weixin_rec_url = weixin_rec_url;
    }

    public String getZfb_id() {
        return zfb_id;
    }

    public void setZfb_id(String zfb_id) {
        this.zfb_id = zfb_id;
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<Terminal> terminals) {
        this.terminals = terminals;
    }

    @Override
    public String toString() {
        return "BindingStationBean{" +
                "addr='" + addr + '\'' +
                ", card_no='" + card_no + '\'' +
                ", descrip='" + descrip + '\'' +
                ", dist=" + dist +
                ", face_pic_id=" + face_pic_id +
                ", games=" + games +
                ", id=" + id +
                ", is_info_open=" + is_info_open +
                ", lantitude=" + lantitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", notice='" + notice + '\'' +
                ", sale_id='" + sale_id + '\'' +
                ", sale_pic_id=" + sale_pic_id +
                ", self_c_id=" + self_c_id +
                ", tel_phone='" + tel_phone + '\'' +
                ", username='" + username + '\'' +
                ", weixin_account_id='" + weixin_account_id + '\'' +
                ", weixin_account_pic_id=" + weixin_account_pic_id +
                ", weixin_account_url='" + weixin_account_url + '\'' +
                ", weixin_rec_url='" + weixin_rec_url + '\'' +
                ", zfb_id='" + zfb_id + '\'' +
                ", terminals=" + terminals +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.addr);
        dest.writeString(this.card_no);
        dest.writeString(this.descrip);
        dest.writeInt(this.dist);
        dest.writeInt(this.face_pic_id);
        dest.writeParcelable(this.games, flags);
        dest.writeInt(this.id);
        dest.writeInt(this.is_info_open);
        dest.writeDouble(this.lantitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.name);
        dest.writeString(this.nickname);
        dest.writeString(this.notice);
        dest.writeString(this.sale_id);
        dest.writeInt(this.sale_pic_id);
        dest.writeInt(this.self_c_id);
        dest.writeString(this.tel_phone);
        dest.writeString(this.username);
        dest.writeString(this.weixin_account_id);
        dest.writeInt(this.weixin_account_pic_id);
        dest.writeString(this.weixin_account_url);
        dest.writeString(this.weixin_rec_url);
        dest.writeString(this.zfb_id);
        dest.writeTypedList(this.terminals);
    }

    public BindingStationBean() {
    }

    protected BindingStationBean(Parcel in) {
        this.addr = in.readString();
        this.card_no = in.readString();
        this.descrip = in.readString();
        this.dist = in.readInt();
        this.face_pic_id = in.readInt();
        this.games = in.readParcelable(GamesBean.class.getClassLoader());
        this.id = in.readInt();
        this.is_info_open = in.readInt();
        this.lantitude = in.readDouble();
        this.longitude = in.readDouble();
        this.name = in.readString();
        this.nickname = in.readString();
        this.notice = in.readString();
        this.sale_id = in.readString();
        this.sale_pic_id = in.readInt();
        this.self_c_id = in.readInt();
        this.tel_phone = in.readString();
        this.username = in.readString();
        this.weixin_account_id = in.readString();
        this.weixin_account_pic_id = in.readInt();
        this.weixin_account_url = in.readString();
        this.weixin_rec_url = in.readString();
        this.zfb_id = in.readString();
        this.terminals = in.createTypedArrayList(Terminal.CREATOR);
    }

    public static final Parcelable.Creator<BindingStationBean> CREATOR = new Parcelable.Creator<BindingStationBean>() {
        @Override
        public BindingStationBean createFromParcel(Parcel source) {
            return new BindingStationBean(source);
        }

        @Override
        public BindingStationBean[] newArray(int size) {
            return new BindingStationBean[size];
        }
    };
}
