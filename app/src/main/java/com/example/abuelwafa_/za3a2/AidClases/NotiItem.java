package com.example.abuelwafa_.za3a2.AidClases;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Abu El Wafa ^_^ on 21/06/2017.
 */

public class NotiItem implements Parcelable{

    protected NotiItem(Parcel in) {
        u_name = in.readString();
        u_msg = in.readString();
        msg_time = in.readString();
        Not_id = in.readString();
    }

    public static final Creator<NotiItem> CREATOR = new Creator<NotiItem>() {
        @Override
        public NotiItem createFromParcel(Parcel in) {
            return new NotiItem(in);
        }

        @Override
        public NotiItem[] newArray(int size) {
            return new NotiItem[size];
        }
    };

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_msg() {
        return u_msg;
    }

    public void setU_msg(String u_msg) {
        this.u_msg = u_msg;
    }

    public String getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(String msg_time) {
        this.msg_time = msg_time;
    }

    public NotiItem(String Not_id,String u_name, String u_msg, String msg_time) {
        this.Not_id = Not_id;
        this.u_name = u_name;
        this.u_msg = u_msg;
        this.msg_time = msg_time;
    }

    String u_name;
    String u_msg;
    String msg_time;

    public String getNot_id() {
        return Not_id;
    }

    public void setNot_id(String not_id) {
        Not_id = not_id;
    }

    String Not_id;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(u_name);
        parcel.writeString(u_msg);
        parcel.writeString(msg_time);
        parcel.writeString(Not_id);
    }
}
