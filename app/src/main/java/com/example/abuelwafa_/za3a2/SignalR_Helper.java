package com.example.abuelwafa_.za3a2;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.abuelwafa_.za3a2.LogReg.LogInActivity;
import com.example.abuelwafa_.za3a2.TapFragments.ChatFrag;
import com.example.abuelwafa_.za3a2.TapFragments.MsgsHistFrag;
import com.example.abuelwafa_.za3a2.TapFragments.NotiFrag;

import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;

/**
 * Created by Abu El Wafa ^_^ on 20/06/2017.
 */

public class SignalR_Helper {
    final static  SignalR_Helper SIGNAL_R_HELPER = new SignalR_Helper();
    static Object context;
    public SignalR_Helper() {
        SignalR_Helper_init();
        hub.subscribe(this);
    }


    public static HubConnection getConnection() {
        return connection;
    }

    public static void setConnection(HubConnection connection) {
        SignalR_Helper.connection = connection;
    }

    public static HubProxy getHub() {
        return hub;
    }

    public static void setHub(HubProxy hub) {
        SignalR_Helper.hub = hub;
    }

    public static SignalRFuture<Void> getAwaitConnection() {
        return awaitConnection;
    }

    public static void setAwaitConnection(SignalRFuture<Void> awaitConnection) {
        SignalR_Helper.awaitConnection = awaitConnection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public static  HubConnection connection;
    public static HubProxy hub;
    public static SignalRFuture<Void> awaitConnection;
  static   String host ;

    public  static void SignalR_Helper_init() {
        Platform.loadPlatformComponent(new AndroidPlatformComponent());
        host = "http://192.168.1.12:8070";
        connection = new HubConnection(host);
        hub = connection.createHubProxy( "chapter3Hub" );
        awaitConnection = connection.start();
    }



    public static  void setClassObject(Object sub)
    {
        context = sub;
    }

    public void  startChat(final String str) {
        ((ChatFrag)context).startChat(str);
    }

        public void LogInCheck(final String str) {
        ((LogInActivity)context).LogInCheck(str);

    }


    public void displayText(String  str) {
        ((LogInActivity)context).displayText(str);
    }

    public void getNotifics(String str){
        ((NotiFrag)context).getNotifics(str);
    }

    public void Recieve(String str) {
        ((MsgsHistFrag)context).Recieve(str);
    }
    public void newMsg(String str) {
        Log.d("NewMsg",str);
        ((MsgsHistFrag)context).Recieve(str);
    }
}
