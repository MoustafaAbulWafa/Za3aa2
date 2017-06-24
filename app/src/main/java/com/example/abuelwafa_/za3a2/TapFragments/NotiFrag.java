package com.example.abuelwafa_.za3a2.TapFragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abuelwafa_.za3a2.Adapters.NotiListAdapter;
import com.example.abuelwafa_.za3a2.AidClases.NotiItem;
import com.example.abuelwafa_.za3a2.R;
import com.example.abuelwafa_.za3a2.SignalR_Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@SuppressLint("ValidFragment")
public class NotiFrag extends Fragment {
ListView list;
    static boolean on = false;
static  ArrayList<NotiItem> Notifics;
    NotiListAdapter notiListAdapter;
    Context context;
    Handler handler;
    @SuppressLint("ValidFragment")
    public NotiFrag(Context context) {
    this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        handler=new Handler();

        View Root =  inflater.inflate(R.layout.fragment_noti, container, false);
        list  = (ListView) Root.findViewById(R.id.noti_list);

        if(!on) {
            SignalR_Helper.getHub().subscribe(this);
            Notifics = new ArrayList<>();
            on = true;
        }


        try {
            SignalR_Helper.getHub().invoke("get").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Toast.makeText(context, "Hereoncreateview", Toast.LENGTH_SHORT).show();


        notiListAdapter = new NotiListAdapter(Notifics,context);
        list.setAdapter(notiListAdapter);


        return Root;
    }


//    Handler handler ;
public void getNotifics(String str){
         NotiItem notiItem;
final ArrayList<NotiItem>Notifics1=new ArrayList<>();
    try {

        JSONObject noti1 = new JSONObject(str);
        JSONArray data = noti1.getJSONArray("notifics");
            for (int i = 0 ; i<data.length();i++){

                JSONObject noti = data.getJSONObject(i);
            String not_id = noti.getString("id");
            String u_name = noti.getString("u_name");
            String u_msg = noti.getString("u_msg");
            String msg_time = noti.getString("msg_time");
            notiItem = new NotiItem(not_id,u_name,u_msg,msg_time);
            Notifics1.add(notiItem);
            }
     handler.post(new Runnable() {
         @Override
         public void run() {

             try {
               //notiListAdapter.getData().clear();
                 notiListAdapter.getData().addAll(Notifics1);
                 notiListAdapter.notifyDataSetChanged();
                 Notifics=Notifics1;
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
//        Toast.makeText(context, Notifics.get(0).getU_name(), Toast.LENGTH_SHORT).show();


    }

    public void  RecieveMessage(final String str){
        Log.d("ReciveMSg",str);

        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,"you have a message :)",Toast.LENGTH_LONG);
            }
        });

    }

}
