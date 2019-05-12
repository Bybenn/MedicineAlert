package com.example.medicinealertapplication.Alarm;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.medicinealertapplication.R;
import com.example.medicinealertapplication.User.RegisterActivity;

import static com.example.medicinealertapplication.Alarm.NotificationHelper.channelID;

public class AlertReceiver extends BroadcastReceiver {
    NotificationHelper notificationHelper;
    PendingIntent pendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
//        for (int i = 0;i<2;i++){
            notificationHelper = new NotificationHelper(context);
            getChannelNotification();
            Intent intent1 = new Intent(context, RegisterActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pendingIntent = PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_ONE_SHOT);

            notificationHelper.getManager().notify(1, getChannelNotification().build());
//        }
    }

    public NotificationCompat.Builder getChannelNotification() {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        return new NotificationCompat.Builder(notificationHelper.getApplicationContext(), channelID)
                .setContentTitle("ได้เวลาทานยาแล้ว!")
                .setSound(alarmSound)
                .setContentText("ได้เวลาทานยาของคุณแล้วตอนนี้ โปรดทวนความจำของคุณ")
                .setSmallIcon(R.drawable.ic_android_show)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

    }
}
