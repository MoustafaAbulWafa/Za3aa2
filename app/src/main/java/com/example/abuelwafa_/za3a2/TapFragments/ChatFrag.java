package com.example.abuelwafa_.za3a2.TapFragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
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
import com.example.abuelwafa_.za3a2.Chating.ChatMessage;
import com.example.abuelwafa_.za3a2.R;
import com.example.abuelwafa_.za3a2.SignalR_Helper;
import com.example.abuelwafa_.za3a2.TabControl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public    Handler handler;
    @SuppressLint("ValidFragment")
    public ChatFrag(Context context) {

        this.context = context;
        // Required empty public constructor
    }

    static boolean on = false;
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
        adapter = new MessageAdapter(context, R.layout.item_chat_left, chatMessages);
        listView.setAdapter(adapter);

        if(!on)
        {
            SignalR_Helper.getHub().subscribe(this);
            on = true;
        }
        //event for button SEND
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "Please input some text...", Toast.LENGTH_SHORT).show();
                } else {
                    //add message to list
                    try {
                        SignalR_Helper.getHub().invoke("serveMessage",""+longitude+" "+latitude,editText.getText().toString()).get();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();

                    }

                    ChatMessage chatMessage = new ChatMessage(editText.getText().toString(), isMine);
                    chatMessages.add(chatMessage);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    if (isMine) {
                        isMine = false;
                    } else {
                        isMine = true;
                    }
                }
            }
        });




        return root;
    }

    public void  RecieveMessage(final String str){
        Log.d("ReciveMSg",str);

        handler.post(new Runnable() {
            @Override
            public void run() {
                ChatMessage chatMessage = new ChatMessage(str, isMine);
                chatMessages.add(chatMessage);
                adapter.notifyDataSetChanged();
                if (isMine) {
                    isMine = false;
                } else {
                    isMine = true;
                }
            }
        });

    }
}
