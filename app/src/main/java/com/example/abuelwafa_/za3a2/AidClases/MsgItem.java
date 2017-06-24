package com.example.abuelwafa_.za3a2.AidClases;

/**
 * Created by Abu El Wafa ^_^ on 22/06/2017.
 */

public class MsgItem {
    String Not_id;
    String u_name;
    String u_msg;
    String msg_time;
    String loc;

    public String getNot_id() {
        return Not_id;
    }

    public String getU_name() {
        return u_name;
    }

    public String getU_msg() {
        return u_msg;
    }

    public String getMsg_time() {
        return msg_time;
    }

    public String getLoc() {
        return loc;
    }

    public MsgItem(String not_id, String u_name, String u_msg, String msg_time, String loc) {
        Not_id = not_id;
        this.u_name = u_name;
        this.u_msg = u_msg;
        this.msg_time = msg_time;
        this.loc = loc;
    }
}
