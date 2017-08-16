package com.example.admin.w3d2_price;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";
    ArrayList<Stuff> stuff = new ArrayList<>();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d(TAG, "onHandleIntent: " + Thread.currentThread());
        Random random = new Random();
        for(int i = 0; i < random.nextInt(50); i++){
            Stuff foo = new Stuff(this);
            stuff.add(foo);
            System.out.println(stuff.get(i).getOne());
        }
        Intent intent2 = new Intent();
        //Bundle bundle = new Bundle();
        //bundle.putParcelableArrayList("stuff", stuff);
        //intent2.setAction("action.GETSTUFF");
        //intent2.putExtra("BUNDLE", stuff);
        sendBroadcast(intent2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy: ");
    }
}
