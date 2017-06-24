package com.example.abuelwafa_.za3a2;

import android.content.Context;
import android.os.Handler;

import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;

/**
 * Created by Abu El Wafa ^_^ on 20/06/2017.
 */

public class SignalR_Helper {
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

    public  static void SignalR_Helper_init(Context context) {
        Platform.loadPlatformComponent(new AndroidPlatformComponent());
        host = "http://172.31.225.200:8070";
        connection = new HubConnection(host);
        hub = connection.createHubProxy( "chapter3Hub" );
        awaitConnection = connection.start();
    }
}
