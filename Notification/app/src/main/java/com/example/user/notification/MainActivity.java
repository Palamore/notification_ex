package com.example.user.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {
    public final static int MY_NOTIFICATION_ID = 1;

    ArrayList<String> mItems;
    ArrayAdapter<String> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mItems);

        ListView gird = (ListView) this.findViewById(R.id.list);
        gird.setAdapter(adapter);
        gird.setOnItemClickListener(this);

        fill();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                            Thread.sleep(5000);
                    onSimpleNotification();

                    }
                }
        ).start();

    }


    private void fill(){
        mItems.clear();

        for (int i = 1; i <= 8; i++) {
            mItems.add("friend" + i);

        }
    }


    public void onSimpleNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_alarm_on_black_24dp);
        mBuilder.setContentTitle(getResources().getString(R.string.notif_title));
        mBuilder.setContentText(getResources().getString(R.string.notif_body));
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // MY_NOTIFICATION_ID allows you to update the notification later on.
        mNotificationManager.notify(MY_NOTIFICATION_ID, mBuilder.build());

        Random mRandom = new Random();
        int rd = (mRandom.nextInt(7) + 1);

        Intent intent = new Intent(this, ChatActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ChatActivity.class);
        stackBuilder.addNextIntent(intent);
        intent.putExtra("Param1","friend" + rd);
        PendingIntent pIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pIntent);
        mBuilder.setAutoCancel(true);
        ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE))
                .notify(MY_NOTIFICATION_ID, mBuilder.build());

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        if (mItems.get(arg2).equals("")) {

        } else {
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("Param1", mItems.get(arg2));
            startActivity(intent);
        }
    }
}
