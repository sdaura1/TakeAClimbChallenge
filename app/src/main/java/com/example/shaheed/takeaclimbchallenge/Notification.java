package com.example.shaheed.takeaclimbchallenge;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        int drawable = Integer.valueOf(R.drawable.ic_notifications_paused_black_24dp);

        NotificationManager mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.mipmap.ic_launcher, "It's time for medication", System.currentTimeMillis());
        Intent myIntent = new Intent(this , DetailedMedication.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, myIntent, 0);
        notification.setLatestEventInfo(this, "Notify label", "Notify text", contentIntent);
        mNM.notify(NOTIFICATION, notification);
    }
}
