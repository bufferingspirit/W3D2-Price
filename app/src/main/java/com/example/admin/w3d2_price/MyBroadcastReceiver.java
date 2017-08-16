package com.example.admin.w3d2_price;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Admin on 8/16/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    ArrayList<Stuff> myList = new ArrayList<>();

    MyBroadcastReceiver(ArrayList stuffList){
        this.myList = stuffList;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BroadCastReciver", "Received");
        switch(intent.getAction()){
            case "action.GETSTUFF":
                Toast.makeText(context, "Got Data", Toast.LENGTH_SHORT).show();
                myList = intent.getParcelableArrayListExtra("BUNDLE");
                for(int i = 0; i < myList.size(); i++){
                    System.out.println(myList.get(i));
                }
                break;

            case "action.ALARM":
                Log.d("BroadCastReceiver", "GotAlarm");
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                Intent intent2 = new Intent();
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent2, 0);
                builder.setContentIntent(pendingIntent);
                builder.setContentTitle("Alarm");
                builder.setSmallIcon(R.drawable.ic_access_alarm_black_24dp);

                NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

                notificationManager.notify(1, builder.build());
                break;
        }

    }
}
