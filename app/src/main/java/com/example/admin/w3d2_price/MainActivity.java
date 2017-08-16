package com.example.admin.w3d2_price;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    ArrayList<Stuff> stuffList;
    StuffAdapter stuffAdapter;
    IntentFilter intentFilter;
    MyBroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);

        recyclerView = (RecyclerView) findViewById(R.id.recycleList);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);

        stuffAdapter = new StuffAdapter(stuffList);
        broadcastReceiver = new MyBroadcastReceiver(stuffList);
        recyclerView.setAdapter(stuffAdapter);

    }

    public void startAlarm(View view){
        Intent intent2 = new Intent();
        intent2.setAction("action.ALARM");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent2 , PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 5);
        long firstTime = c.getTimeInMillis();
        // Schedule the alarm!
        alarm.set(AlarmManager.RTC_WAKEUP,  firstTime, pendingIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        intentFilter = new IntentFilter();
        intentFilter.addAction("action.GETSTUFF");
        intentFilter.addAction("action.ALARM");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stuffAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(broadcastReceiver);
    }




}
