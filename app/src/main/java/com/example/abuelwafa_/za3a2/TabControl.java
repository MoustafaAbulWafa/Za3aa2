package com.example.abuelwafa_.za3a2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.abuelwafa_.za3a2.Adapters.NotiListAdapter;
import com.example.abuelwafa_.za3a2.AidClases.Model;
import com.example.abuelwafa_.za3a2.AidClases.NotiItem;
import com.example.abuelwafa_.za3a2.TapFragments.ChatFrag;
import com.example.abuelwafa_.za3a2.TapFragments.MsgsHistFrag;
import com.example.abuelwafa_.za3a2.TapFragments.NotiFrag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TabControl extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId()==R.id.navigation_home) {
                MsgsHistFrag singerFrag = new MsgsHistFrag(TabControl.this);
                getSupportFragmentManager().beginTransaction().replace(R.id.content,singerFrag).commit();
                Log.d("Done","Done Done Done");
                return true;
            }
        /*    else  if (item.getItemId()==R.id.navigation_dashboard)
            {

                ChatFrag Messagges = new ChatFrag(TabControl.this);
                getSupportFragmentManager().beginTransaction().replace(R.id.content,Messagges).commit();

              return true;

            }*/

            else  if (item.getItemId()==R.id.navigation_notifications) {

                NotiFrag Notific = new NotiFrag(TabControl.this);
                getSupportFragmentManager().beginTransaction().replace(R.id.content,Notific).commit();

              return true;
            }


            return true;
        }

    };
    private Handler handler;
 public static   BottomNavigationView navigation;

    static boolean on = false;
    ArrayList<Model> locations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Intent n = getIntent();
        locations = n.getParcelableArrayListExtra("latlngs");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.broad){
            Intent n = new Intent(this,BroadCastingActivity.class);
            n.putParcelableArrayListExtra("latlngs",locations);
            startActivity(n);

        }


    return true;
    }
}
