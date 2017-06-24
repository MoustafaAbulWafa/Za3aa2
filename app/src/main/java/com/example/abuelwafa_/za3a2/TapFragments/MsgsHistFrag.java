package com.example.abuelwafa_.za3a2.TapFragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.abuelwafa_.za3a2.Adapters.NotiListAdapter;
import com.example.abuelwafa_.za3a2.AidClases.MsgItem;
import com.example.abuelwafa_.za3a2.AidClases.NotiItem;
import com.example.abuelwafa_.za3a2.R;
import com.example.abuelwafa_.za3a2.SignalR_Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


@SuppressLint("ValidFragment")
public class MsgsHistFrag extends Fragment {
    ListView list;
    static boolean on = false;
    static  ArrayList<MsgItem> Msgs;
    NotiListAdapter notiListAdapter;
    Context context;
    Handler handler;
    @SuppressLint("ValidFragment")
    public MsgsHistFrag(Context context) {
        this.context = context;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View Root =  inflater.inflate(R.layout.fragment_msgs_hist, container, false);

        list  = (ListView) Root.findViewById(R.id.msghis_list);


        if(!on) {
            SignalR_Helper.getHub().subscribe(this);
            Msgs = new ArrayList<>();
            on = true;
        }
        notiListAdapter = new NotiListAdapter(Msgs,true,context);
        list.setAdapter(notiListAdapter);
        return Root;
    }

    public void RecieveMessage(String str){
    MsgItem msgItem;
    final ArrayList<MsgItem> Msgs1 = new ArrayList<>();
    try {

        JSONObject noti1 = new JSONObject(str);
        JSONArray data = noti1.getJSONArray("msgs");
        for (int i = 0 ; i<data.length();i++){

            JSONObject noti = data.getJSONObject(i);
            String not_id = noti.getString("id");
            String u_name = noti.getString("u_name");
            String u_msg = noti.getString("u_msg");
            String msg_time = noti.getString("msg_time");
            String loc = noti.getString("loc");
            msgItem = new MsgItem(not_id,u_name,u_msg,msg_time,loc);
            Msgs1.add(msgItem);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {

                try {
                   // notiListAdapter.getData().clear();
                    notiListAdapter.getData1().addAll(Msgs1);
                    notiListAdapter.notifyDataSetChanged();
                    Msgs=Msgs1;
                }
                catch (Exception e){
                    Log.d("noti1",e.getMessage());
                }
            }
        });
    }
         catch (JSONException e) {
        e.printStackTrace();
    }
}}
