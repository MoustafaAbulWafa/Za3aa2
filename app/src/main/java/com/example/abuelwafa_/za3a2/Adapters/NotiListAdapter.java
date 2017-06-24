package com.example.abuelwafa_.za3a2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abuelwafa_.za3a2.AidClases.MsgItem;
import com.example.abuelwafa_.za3a2.AidClases.NotiItem;
import com.example.abuelwafa_.za3a2.R;
import com.example.abuelwafa_.za3a2.TapFragments.NotiFrag;

import java.util.ArrayList;

/**
 * Created by Abu El Wafa ^_^ on 21/06/2017.
 */

public class NotiListAdapter extends BaseAdapter{
    ArrayList<MsgItem> Msgs;
boolean flag = false;
    public NotiListAdapter(ArrayList<NotiItem> singers, Context context) {
        this.Notific = singers;
        this.context = context;
        Toast.makeText(context, "Hereoncreateview"+Notific.size(), Toast.LENGTH_SHORT).show();

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
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        View view = convertView;
        if(view == null){
            LayoutInflater lay= LayoutInflater.from(context);
            view  = lay.inflate(R.layout.row_msg_item , null);
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
        holder.msg_time.setText(Notific.get(position).getMsg_time());

    }

else {

    int pos = Dublicated(Msgs.get(position).getNot_id(), position);
    if (pos != -1) {
        holder.u_name.setText(Msgs.get(pos).getU_name());
        holder.u_msg.setText(Msgs.get(pos).getU_msg());
        holder.msg_time.setText(Msgs.get(pos).getMsg_time());

    } else {
        holder.u_name.setText(Msgs.get(position).getU_name());
        holder.u_msg.setText(Msgs.get(position).getU_msg());
        holder.msg_time.setText(holder.msg_time.getText() + Msgs.get(position).getLoc() + " at : "  + Msgs.get(position).getMsg_time() );
    }
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
        for(int i=j+1;i<Notific.size();i++){
            if(Notific.get(i).getNot_id().equals(not_id))
                return i;
        }

    return pos;
    }

    class Holder {
        public View view;
        public TextView u_name,u_msg,msg_time;

        public Holder(View v) {
            this.view = v;
            u_name = (TextView) view.findViewById(R.id.u_name);
            u_msg = (TextView) view.findViewById(R.id.u_msg);
            msg_time = (TextView) view.findViewById(R.id.msg_t);

        }
    }}
