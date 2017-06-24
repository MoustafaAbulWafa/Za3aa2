package com.example.abuelwafa_.za3a2.AidClases;

/**
 * Created by Abu El Wafa ^_^ on 21/06/2017.
 */

public class NotiItem {

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


}
