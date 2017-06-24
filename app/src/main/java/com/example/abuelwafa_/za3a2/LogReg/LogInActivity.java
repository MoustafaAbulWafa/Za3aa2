package com.example.abuelwafa_.za3a2.LogReg;

import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abuelwafa_.za3a2.AidClases.GPSTracker;
import com.example.abuelwafa_.za3a2.SignalR_Helper;
import com.example.abuelwafa_.za3a2.TabControl;
import com.example.abuelwafa_.za3a2.AidClases.Model;
import com.example.abuelwafa_.za3a2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;

public class LogInActivity extends AppCompatActivity {


EditText t1,t2;
    boolean flag;
    // GPSTracker class
    GPSTracker gps;
    ArrayList<Model> latlngs;
     Button logIn,SignUp;
Handler handler;

    @Override
    protected void onStart() {
        gps = new GPSTracker(LogInActivity.this);
        loc = gps.getLocation();
        // check if GPS enabled
        if(gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
        }

        try {

            SignalR_Helper.getAwaitConnection().get();

            logIn.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    if(t1.getText().toString().matches("")|| t1.getText().toString().matches(""))
                        Toast.makeText(getApplicationContext(),"The E-Mail Or The Pass isn't set ..!", Toast.LENGTH_LONG).show();
                    else
                    { try {
                        SignalR_Helper.getHub().invoke("loging",t1.getText().toString(),t2.getText().toString()).get();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        Log.d("EXEC1",e.getMessage());
                    }

                    }

                }});
            //  awaitConnection.get();
        } catch (InterruptedException e) {
            // Handle ...
        } catch (ExecutionException e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            Log.d("TAG1",e.getMessage());
        }

        super.onStart();
    }

    public static  double longitude,latitude;
    public  Location loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        handler  =new Handler();
        logIn = (Button) findViewById(R.id.btn_login);
        SignUp = (Button) findViewById(R.id.btn_register);
        t1 = (EditText) findViewById(R.id.email);
        t2 = (EditText) findViewById(R.id.pass);
        SignalR_Helper.SignalR_Helper_init(this);


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this,RegistrationActivity.class));
            }
        });

        SignalR_Helper.getHub().subscribe(this);
    }

    public void LogInCheck(final String str){

        handler.post(new Runnable() {
            @Override
            public void run() {
                if(str.equals("1"))
                {
                    try {

                        if(loc != null) {
                        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                            SignalR_Helper.getHub().invoke("broadcastMessage",""+12.3+" "+13.2).get();

                    }

                    else {
                        loc = gps.getLocation();
                        Toast.makeText(getApplicationContext(), "Wait ... !", Toast.LENGTH_LONG).show();
                            SignalR_Helper.getHub().invoke("broadcastMessage",""+12.3+" "+13.2).get();

                    }
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                        Log.d("EXEC1",e.getMessage());
                }
                }
                else
                {Toast.makeText(getApplicationContext(), "UnAuthurized E-Mial Or Password", Toast.LENGTH_LONG).show();}

            }
        });
        }

    public void displayText(String  str){

        final String finalMsg =  " says jj " + str;
        Log.d("MoustafaMsg",finalMsg);
        try {
            latlngs = new ArrayList<>();
            Model latlg;
            latlg = new Model(longitude,latitude);
            latlngs.add(latlg);
            JSONObject jsonObject = new JSONObject(str);
            JSONArray Locs = jsonObject.getJSONArray("locations");

            for(int i=0;i<Locs.length();i++){
                JSONObject l = Locs.getJSONObject(i);
                double lng = l.getDouble("lng");
                double lat = l.getDouble("lat");
                latlg = new Model(lng,lat);
                latlngs.add(latlg);
            }

            Log.d("MoustafaMsg",latlngs.size()+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
         //       Toast.makeText(LogInActivity.this,finalMsg,Toast.LENGTH_LONG).show();
            }
        });
        Intent in = new Intent(LogInActivity.this,TabControl.class);
        in.putParcelableArrayListExtra("latlngs",latlngs);
        //connection.disconnect();

        //  connection.stop();
        startActivity(in);
    }



}
