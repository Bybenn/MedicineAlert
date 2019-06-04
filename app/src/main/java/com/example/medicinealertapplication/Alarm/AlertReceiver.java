package com.example.medicinealertapplication.Alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.example.medicinealertapplication.QuestionActivity;
import com.example.medicinealertapplication.R;
import com.example.medicinealertapplication.User.LoginActivity;
import com.example.medicinealertapplication.User.SetTimeToEatActivity;
import com.example.medicinealertapplication.User.User;

import static com.example.medicinealertapplication.Alarm.NotificationHelper.channelID;

public class AlertReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "com.example.notificationDemo.channelId";
    NotificationHelper notificationHelper;
    static PendingIntent pendingIntent;
    User user = new User();
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    String medName;
    static int reqCode = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationHelper = new NotificationHelper(context);
        getChannelNotification();
        user.setUserID(Integer.parseInt(LoginActivity.loginID));
        user.setUserName(LoginActivity.user);
        user.setUserPass(LoginActivity.pass);
        userID = LoginActivity.loginID;
        userName = LoginActivity.user;
        userPass = LoginActivity.pass;
        userMorning = SetTimeToEatActivity.morning;
        userAfter = SetTimeToEatActivity.after;
        userEven = SetTimeToEatActivity.even;
        medName = intent.getStringExtra("nameMed");


//        Intent question = new Intent(context, class);
//        question.putExtra("idUser", userID);
//        question.putExtra("nameUser", userName);
//        question.putExtra("passUser", userPass);
//        question.putExtra("mornUser", userMorning);
//        question.putExtra("afterUser", userAfter);
//        question.putExtra("evenUser", userEven);
//        question.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pendingIntent = PendingIntent.getActivity(context, reqCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        intent.getAction();
        notificationHelper.getManager().notify(reqCode, getChannelNotification().build());

        reqCode++;
    }

    static int i = 1;

    public NotificationCompat.Builder getChannelNotification() {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        return new NotificationCompat.Builder(notificationHelper.getApplicationContext(), channelID + (i++))
                .setContentTitle("MEDICINE ALERT")
                .setSound(alarmSound)
                .setContentText("ได้เวลาทานยา "+medName+" ของคุณแล้ว")
                .setSmallIcon(R.drawable.ic_android_show)
                .setAutoCancel(true)
                .setVibrate(new long[] { 500, 1000, 500 })
                .setContentIntent(pendingIntent);
    }
}
