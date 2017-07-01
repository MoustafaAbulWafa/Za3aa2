package com.example.abuelwafa_.za3a2;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abuelwafa_.za3a2.AidClases.Model;
import com.example.abuelwafa_.za3a2.AidClases.NotiItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.abuelwafa_.za3a2.LogReg.LogInActivity.latitude;
import static com.example.abuelwafa_.za3a2.LogReg.LogInActivity.longitude;

public class BroadCastingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback {
    private GoogleMap mMap;
    ArrayList<Model> locations;
    EditText msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_casting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SignalR_Helper.setClassObject(this);
        msg = (EditText) findViewById(R.id.broadmsg);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        SignalR_Helper.setClassObject(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        Intent n = getIntent();
        locations = n.getParcelableArrayListExtra("latlngs");
        Log.d("Size1", locations.size() + "");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.broad_casting, menu);
        return true;
    }

String jop,gender,agef,aget;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_prof) {
jop=gender=agef=aget="";
            final Dialog p_dialog =   prof_dialog();
            p_dialog.show();

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String x = "";
                    if(chose!=null)
                    for(int i = 0 ; i<chose.size();i++)
                        jop += chose.get(i)  + " ";

                    Toast.makeText(BroadCastingActivity.this,"the jops : " + jop + "\n gender" + gender + "\n age from" + agef + "\n age to" + aget,Toast.LENGTH_LONG).show();
                    SignalR_Helper.getHub().invoke("getByFilters",jop,gender,agef + " " + aget);
                    p_dialog.hide();
                }
            });

            if(rd1.isChecked())
            {
                rd2.setChecked(false);
                rd3.setChecked(false);
                gender  = rd1.getText().toString();
            }

           else if(rd2.isChecked())
            {
                rd1.setChecked(false);
                rd3.setChecked(false);
                gender  = rd2.getText().toString();
            }
            else if(rd3.isChecked())
            {
                rd1.setChecked(false);
                rd2.setChecked(false);
                gender  = rd3.getText().toString();
            }

                    agef = sp1.getSelectedItem().toString();
                    aget = sp2.getSelectedItem().toString();

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chose = null;
                    p_dialog.hide();
                }
            });
            list_of_prof.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    chose.add(names.get(i));
                    Toast.makeText(BroadCastingActivity.this,"Filter Added",Toast.LENGTH_SHORT).show();

                    Toast.makeText(BroadCastingActivity.this,"Press Again to  Add another Filter",Toast.LENGTH_SHORT).show();
                }
            });

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    ListView list_of_prof;
    Button ok,cancel;
    ArrayList<String>names;
    ArrayList<String>chose;
    RadioButton rd1,rd2,rd3;
    Spinner sp1,sp2;
    String [] str;
    Dialog prof_dialog ()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_prof);
        dialog.setTitle("Prof");
        dialog.setCancelable(true);
        dialog.invalidateOptionsMenu();
        dialog.takeKeyEvents(true);
        list_of_prof = (ListView) dialog.findViewById(R.id.listprof);
        ok = (Button) dialog.findViewById(R.id.ok);
        cancel = (Button) dialog.findViewById(R.id.no);
        rd1= (RadioButton)dialog.findViewById(R.id.rd1);
        rd2= (RadioButton)dialog.findViewById(R.id.rd2);
        rd3= (RadioButton)dialog.findViewById(R.id.rd3);
        sp1= (Spinner)dialog.findViewById(R.id.planets_spinner);
        sp2= (Spinner)dialog.findViewById(R.id.planets_spinner1);
        str = new String[51];
        str[0]=0+"";
        try {
        for(int i=15;i<65;i++){
            str [i-15]=String.valueOf(i);
        }

          ArrayAdapter adapter = new ArrayAdapter(this,
                  android.R.layout.simple_spinner_item, str);
          sp1.setAdapter(adapter);
          sp2.setAdapter(adapter);
      }
      catch (Exception e){
          Log.d("EXEC1",e.getMessage());
      }
          chose= new ArrayList<>();
        names = new ArrayList<>();
        try {
            SignalR_Helper.getHub().invoke("getProfs").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


return dialog;
      /*
     ok = ()

        return p_dialog;
*/
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        msg.setText("");
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        msg.setText(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney;


        for (int i = 1; i < locations.size(); i++) {
            sydney = new LatLng(locations.get(i).getLng(), locations.get(i).getLat());
            mMap.addMarker(new MarkerOptions().position(sydney).title("vistor"));
        }
        sydney = new LatLng(locations.get(0).getLng(), locations.get(0).getLat());
        mMap.addMarker(new MarkerOptions().position(sydney).title("Here").icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(50));
        CircleOptions c = new CircleOptions().center(sydney).fillColor(R.color.main).radius(500);
        mMap.addCircle(c);
        //Toast.makeText(this, locations.size(), Toast.LENGTH_SHORT).show();
    }


    Handler handler;
NotiItem split (String s)
{

    NotiItem notiItem = null;
    //final ArrayList<NotiItem>Notifics1=new ArrayList<>();
    try {

        JSONObject noti1 = new JSONObject(s);
        JSONArray data = noti1.getJSONArray("notifics");
        for (int i = 0 ; i<data.length();i++){

            JSONObject noti = data.getJSONObject(i);
            String not_id = noti.getString("id");
            String u_name = noti.getString("u_name");
            String u_msg = noti.getString("u_msg");
            String msg_time = noti.getString("msg_time");
            notiItem = new NotiItem(not_id,u_name,u_msg,msg_time);
        //    Notifics1.add(notiItem);
        }
} catch (JSONException e) {
        e.printStackTrace();
    }

    return notiItem;
}

    public void notifi(String s) {


        NotiItem n =split(s);
        //Toast.makeText(BroadCastingActivity.this, "sdsdsd", Toast.LENGTH_SHORT).show();
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(BroadCastingActivity.this)
                        .setSmallIcon(R.drawable.background)
                        .setContentTitle(n.getU_name())
                        .setContentText(n.getU_msg());

        Intent intent = new Intent(BroadCastingActivity.this, TabControl.class);
        intent.putExtra("a7",n);
        PendingIntent pendingIntent = PendingIntent.getActivity(BroadCastingActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);

        int mNotificationId = 001;
// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());

    }



public void setProfs(String str){
    String [] data = str.split(" ");
        for(int i=0;i<data.length;i++)
            names.add(data[i]);

    ArrayAdapter<String> arr = new ArrayAdapter<String>(this, R.layout.profitem, names);
    arr.notifyDataSetChanged();
    try {
        list_of_prof.setAdapter(arr);
    }
    catch (Exception e){
        Log.d("EXEC1",e.getMessage());

    }

}



    public void sendbroadcastmsg(View view) {
try {
    SignalR_Helper.getHub().invoke("serveMessage", locations.get(0).getLng() + " " + locations.get(0).getLat(), msg.getText().toString());
    msg.setText("");
    Toast.makeText(this, "Send....", Toast.LENGTH_SHORT).show();
}catch (Exception e)
{
    Toast.makeText(this, "dsd", Toast.LENGTH_SHORT).show();
}
    }
    public void displayText1(String  str){

        final String finalMsg =  " says in map " + str;
        Log.d("MoustafaMsg",finalMsg);
        try {
            locations = new ArrayList<>();
            Model latlg;
            latlg = new Model(longitude,latitude);
            locations.add(latlg);
            JSONObject jsonObject = new JSONObject(str);
            JSONArray Locs = jsonObject.getJSONArray("locations");

            for(int i=0;i<Locs.length();i++){
                JSONObject l = Locs.getJSONObject(i);
                double lng = l.getDouble("lng");
                double lat = l.getDouble("lat");
                latlg = new Model(lng,lat);
                locations.add(latlg);
            }

            Log.d("MoustafaMsg",locations.size()+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        onMapReady(mMap);
}
}
