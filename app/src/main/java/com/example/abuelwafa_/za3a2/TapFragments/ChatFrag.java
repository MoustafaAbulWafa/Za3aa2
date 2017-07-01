package com.example.abuelwafa_.za3a2.TapFragments;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abuelwafa_.za3a2.Adapters.MessageAdapter;
import com.example.abuelwafa_.za3a2.AidClases.MsgItem;
import com.example.abuelwafa_.za3a2.Chating.ChatMessage;
import com.example.abuelwafa_.za3a2.R;
import com.example.abuelwafa_.za3a2.SignalR_Helper;
import com.example.abuelwafa_.za3a2.TabControl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.example.abuelwafa_.za3a2.LogReg.LogInActivity.latitude;
import static com.example.abuelwafa_.za3a2.LogReg.LogInActivity.longitude;


@SuppressLint("ValidFragment")
public class ChatFrag extends Fragment {
Context context;
    private ListView listView;
    private View btnSend;
    private EditText editText;
    boolean isMine = true;
    private List<ChatMessage> chatMessages;
    private ArrayAdapter<ChatMessage> adapter;
    MsgItem user;
    public    Handler handler;
    @SuppressLint("ValidFragment")
    public ChatFrag(Context context) {

        this.context = context;
        // Required empty public constructor
    }

    static boolean on = true;

    public ChatFrag(MsgItem user) {
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_msg, container, false);
        handler = new Handler();

        chatMessages = new ArrayList<>();

        listView = (ListView) root.findViewById(R.id.list_msg);
        btnSend = root.findViewById(R.id.btn_chat_send);
        editText = (EditText) root.findViewById(R.id.msg_type);

        //set ListView adapter first
        adapter = new MessageAdapter(getContext(), R.layout.item_chat_right, chatMessages);
        listView.setAdapter(adapter);

            ChatMessage chatMessage = new ChatMessage(user.getU_msg(), "R");
            chatMessages.add(chatMessage);
            adapter.notifyDataSetChanged();

        SignalR_Helper.setClassObject(this);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "Please input some text...", Toast.LENGTH_SHORT).show();
                } else {
                    //add message to list
                    try {
                        ChatMessage chatMessage = new ChatMessage(editText.getText().toString(), "S");
                        chatMessages.add(chatMessage);
                        adapter.notifyDataSetChanged();

                            SignalR_Helper.getHub().invoke("sendNotific", user.getNot_id(), editText.getText().toString()).get();
                        SignalR_Helper.getHub().invoke("chatting",user.getNot_id(),editText.getText().toString()).get();

                        editText.setText("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();

                    }


                }
            }
        });




        return root;
    }



    public void  startChat(final String str){
        Log.d("ReciveMSg",str);
        on = false;
        handler.post(new Runnable() {
            @Override
            public void run() {
                ChatMessage chatMessage = new ChatMessage(str, "R");
                chatMessages.add(chatMessage);
                adapter.notifyDataSetChanged();

            }
        });

    }


}