package com.example.abuelwafa_.za3a2.Chating;

/**
 * Created by Abu El Wafa ^_^ on 20/06/2017.
 */

public class ChatMessage {

    private String content;
    private String isMine;

    public ChatMessage(String content, String isMine) {
        this.content = content;
        this.isMine = isMine;
    }

    public String getContent() {
        return content;
    }

    public String isMine() {
        return isMine;
    }

}
