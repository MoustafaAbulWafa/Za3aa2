package com.example.abuelwafa_.za3a2.Adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abuelwafa_.za3a2.AidClases.MsgItem;
import com.example.abuelwafa_.za3a2.AidClases.NotiItem;
import com.example.abuelwafa_.za3a2.R;
import com.example.abuelwafa_.za3a2.TabControl;
import com.example.abuelwafa_.za3a2.TapFragments.ChatFrag;
import com.example.abuelwafa_.za3a2.TapFragments.NotiFrag;

import java.util.ArrayList;
/**
 * Created by Abu El Wafa ^_^ on 21/06/2017.
 */

public class NotiListAdapter extends BaseAdapter{
    ArrayList<MsgItem> Msgs;
boolean flag = false;
    boolean on1=false,on2=false,on3=false,on4=false;
    public NotiListAdapter(ArrayList<NotiItem> singers, Context context) {
        this.Notific = singers;
        this.context = context;
//        Toast.makeText(context, "Hereoncreateview"+Notific.size(), Toast.LENGTH_SHORT).show();

    }
public NotiListAdapter(ArrayList<MsgItem> Msgs,boolean flag,Context context){
    this.Msgs = Msgs;
    this.flag = flag;
    this.context = context;
}

    ArrayList<NotiItem> Notific;
    Context context;

    @Override
    public int getCount() {
        if(!flag)
        return Notific.size();
    else
        return Msgs.size();
    }

    @Override
    public Object getItem(int position)
    {
        if(!flag)
        return Notific.get(position);
     else
        return Msgs.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;
        View view = convertView;
        if(view == null){
            LayoutInflater lay= LayoutInflater.from(context);
         if(flag) {
             view = lay.inflate(R.layout.row_noti_item, null);
         }  else {
             view = lay.inflate(R.layout.row_msg_item, null);
         }
            holder = new Holder(view);
            view.setTag(holder);
        }
        else
        {
            holder= (Holder)view.getTag();

        }


if(!flag) {

        holder.u_name.setText(Notific.get(position).getU_name());
        holder.u_msg.setText(Notific.get(position).getU_msg());
        String [] str = Notific.get(position).getMsg_time().split(" ");
        String str1= str[1].substring(0,4);
        holder.msg_time.setText("at "+str1 + " " + str[2]);

    }

else {
    final int pos = Dublicated(Msgs.get(position).getNot_id(), position);
    if (pos != -1) {
        holder.u_name.setText(Msgs.get(pos).getU_name());
        holder.u_msg.setText(Msgs.get(pos).getU_msg());
        String [] str = Msgs.get(position).getMsg_time().split(" ");
        String str1= str[1].substring(0,4);
        holder.msg_time.setText("at "+str1 + " " + str[2]);
        holder.loc.setText("in: "+Msgs.get(position).getLoc());

    } else {
        holder.u_name.setText(Msgs.get(position).getU_name());
        holder.u_msg.setText(Msgs.get(position).getU_msg());
        String [] str = Msgs.get(position).getMsg_time().split(" ");
        String str1= str[1].substring(0,4);
        holder.msg_time.setText("at "+str1 + " " + str[2]);
        holder.loc.setText("in: "+Msgs.get(position).getLoc());

    }
    holder.interest.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int m1 = R.drawable.like2;
            int m2 = R.drawable.like1;
            if(!on3){
                holder.interest.setBackgroundResource(m2);
                on3=true;
            }
            else
            {
                holder.interest.setBackgroundResource(m1);
                on3=false;
            }

            Toast.makeText(context,"Button Interest Clicked",Toast.LENGTH_SHORT).show();
        }
    });
    holder.angry.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            int m1 = R.drawable.ann;
            int m2 = R.drawable.ann2;
            if(!on4){
                holder.angry.setBackgroundResource(m2);
                on4=true;
            }
            else
            {
                holder.angry.setBackgroundResource(m1);
                on4=false;
            }
            Toast.makeText(context,"Button Annoyed Clicked",Toast.LENGTH_SHORT).show();
        }
    });

    holder.re_broad.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int m1 = R.drawable.bro1;
            int m2 = R.drawable.bro2;
            if(!on1){
                holder.re_broad.setBackgroundResource(m2);
                on1=true;
            }
            else
            {
                holder.re_broad.setBackgroundResource(m1);
                on1=false;
            }
            Toast.makeText(context,"Button Rebroadcast  Clicked",Toast.LENGTH_SHORT).show();
        }
    });
    holder.rep.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int m1 = R.drawable.replay;
            int m2 = R.drawable.replay2;
            if(!on2){
                holder.rep.setBackgroundResource(m2);
                on2=true;
            }
            else
            {
                holder.rep.setBackgroundResource(m1);
                on2=false;
            }

            ChatFrag Messagges = new ChatFrag(Msgs.get(position));
            ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.content,Messagges).commit();
            TabControl.navigation.getMenu().getItem(1).setChecked(true);
        }
    });

}
        return view;
    }

    public ArrayList<NotiItem>getData()
    {
        return Notific;

    }
    public ArrayList<MsgItem>getData1()
    {
        return Msgs;

    }
    private int Dublicated(String not_id,int j) {
    int pos = -1;
        for(int i=j+1;i<Msgs.size();i++){
            if(Msgs.get(i).getNot_id().equals(not_id))
                return i;
        }

    return pos;
    }

    class Holder {
        public View view;
        public TextView u_name,u_msg,msg_time,loc;
Button interest,angry,re_broad,rep;
        public Holder(View v) {
            this.view = v;
            u_name = (TextView) view.findViewById(R.id.u_name);
            u_msg = (TextView) view.findViewById(R.id.u_msg);
            msg_time = (TextView) view.findViewById(R.id.msg_t);
            if (flag) {
                loc = (TextView) view.findViewById(R.id.place);
                interest = (Button) view.findViewById(R.id.like);
                angry = (Button) view.findViewById(R.id.angry);
                re_broad = (Button) view.findViewById(R.id.reprodcat);
                rep = (Button) view.findViewById(R.id.rep);
            }
        }   }}
