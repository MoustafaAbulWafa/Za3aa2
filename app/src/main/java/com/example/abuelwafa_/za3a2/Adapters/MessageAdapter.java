package com.example.abuelwafa_.za3a2.Adapters;

/**
 * Created by Abu El Wafa ^_^ on 20/06/2017.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.abuelwafa_.za3a2.Chating.ChatMessage;
import com.example.abuelwafa_.za3a2.R;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<ChatMessage> {

    private Context activity;
    private List<ChatMessage> messages;

    public MessageAdapter(Context context, int resource, List<ChatMessage> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.messages = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        int layoutResource = 0; // determined by view type
        ChatMessage chatMessage = messages.get(position);
        int viewType = getItemViewType(position);

        if (chatMessage.isMine().equals("R")) {
            layoutResource = R.layout.item_chat_left;
        } else {
            layoutResource = R.layout.item_chat_right;
        }

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        //set message content
        holder.msg.setText(chatMessage.getContent());

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        // return the total number of view types. this value should never change
        // at runtime
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        return position % 2 ;
    }

    private class ViewHolder {
        private TextView msg;

        public ViewHolder(View v) {
            msg = (TextView) v.findViewById(R.id.txt_msg);
        }
    }
}
