package com.example.medicinealertapplication.Alarm;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.medicinealertapplication.R;

import static com.example.medicinealertapplication.Alarm.NotificationHelper.channelID;

public class AlertReceiver extends BroadcastReceiver {
    NotificationHelper notificationHelper;
    PendingIntent pendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationHelper = new NotificationHelper(context);
        getChannelNotification();
        Intent intent1 = new Intent(context, TimeMedActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pendingIntent = PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_ONE_SHOT);

        notificationHelper.getManager().notify(1, getChannelNotification().build());
    }

    public NotificationCompat.Builder getChannelNotification() {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        return new NotificationCompat.Builder(notificationHelper.getApplicationContext(), channelID)
                .setContentTitle("Alarm!")
                .setSound(alarmSound)
                .setContentText("Your AlarmManager is working.")
                .setSmallIcon(R.drawable.ic_android)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

    }
}
