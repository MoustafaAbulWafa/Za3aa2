package com.example.abuelwafa_.za3a2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.abuelwafa_.za3a2.R;

import java.util.ArrayList;

/**
 * Created by Abu El Wafa ^_^ on 01/07/2017.
 */

public class dialogAdpter extends BaseAdapter {

    Context context;
    ArrayList<String>names_prof;

    public dialogAdpter(Context context, ArrayList<String> names_prof) {
        this.context = context;
        this.names_prof = names_prof;
    }
       static ArrayList<String>chosen;


    @Override
    public int getCount() {
        return names_prof.size();
    }

    @Override
    public Object getItem(int i) {
        return names_prof.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long)i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        dialogAdpter.Holder holder;
        View views = view;
        if(view == null){
            LayoutInflater lay= LayoutInflater.from(context);
            view = lay.inflate(R.layout.profitem, null);
            holder = new dialogAdpter.Holder(view);
            view.setTag(holder);
        }
        else
        {
            holder= (dialogAdpter.Holder)view.getTag();

        }

        holder.name.setText(names_prof.get(i).toString());


        return null;
    }
    class Holder {
        public View view;
        public TextView name;
        CheckBox checkBox;
        public Holder(View v) {
            this.view = v;
            name = (TextView) view.findViewById(R.id.prof_name);
           //checkBox = (CheckBox) view.findViewById(R.id.cprof_chec);


        }
    }
}
