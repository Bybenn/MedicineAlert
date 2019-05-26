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
import android.support.v4.app.NotificationCompat;

import com.example.medicinealertapplication.QuestionActivity;
import com.example.medicinealertapplication.R;
import com.example.medicinealertapplication.User.LoginActivity;
import com.example.medicinealertapplication.User.RegisterActivity;
import com.example.medicinealertapplication.User.SetTimeToEatActivity;
import com.example.medicinealertapplication.User.User;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;
import static com.example.medicinealertapplication.Alarm.NotificationHelper.channelID;

public class AlertReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "com.example.notificationDemo.channelId";
    NotificationHelper notificationHelper;
    PendingIntent pendingIntent;
    User user = new User();
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;

    static int reqCode = 1;


    @Override
    public void onReceive(Context context, Intent intent) {
//        for (int i = 0;i<2;i++){
        notificationHelper = new NotificationHelper(context);
        getChannelNotification();
//            Intent intent1 = new Intent(context, TimeMedActivity.class);
        user.setUserID(Integer.parseInt(LoginActivity.loginID));
        user.setUserName(LoginActivity.user);
        user.setUserPass(LoginActivity.pass);
        userID = LoginActivity.loginID;
        userName = LoginActivity.user;
        userPass = LoginActivity.pass;
        userMorning = SetTimeToEatActivity.morning;
        userAfter = SetTimeToEatActivity.after;
        userEven = SetTimeToEatActivity.even;
        Intent HomePage = new Intent(context, QuestionActivity.class);
        HomePage.putExtra("idUser", userID);
        HomePage.putExtra("nameUser", userName);
        HomePage.putExtra("passUser", userPass);
        HomePage.putExtra("mornUser", userMorning);
        HomePage.putExtra("afterUser", userAfter);
        HomePage.putExtra("evenUser", userEven);
//            startActivity(HomePage);
        HomePage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pendingIntent = PendingIntent.getActivity(context, reqCode, HomePage, PendingIntent.FLAG_UPDATE_CURRENT);

        notificationHelper.getManager().notify(reqCode, getChannelNotification().build());

        reqCode++;
    }

//        Notification.Builder builder = new Notification.Builder(context);
//
//        int requestId = intent.getIntExtra("requestCode", 1);
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        Notification notification = builder.setContentTitle("Demo App Notification")
//                .setSound(alarmSound)
//                .setContentText("New notification from reqest ID: "+requestId)
//                .setTicker("New Message Alert!")
//                .setSmallIcon(R.mipmap.ic_launcher).build();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            builder.setChannelId(CHANNEL_ID);
//        }
//
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(
//                    CHANNEL_ID,
//                    "MultipleNotifications",
//                    IMPORTANCE_DEFAULT
//            );
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        notificationManager.notify(requestId, notification);
//
//    }

    static int i = 1;

    public NotificationCompat.Builder getChannelNotification() {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        return new NotificationCompat.Builder(notificationHelper.getApplicationContext(), channelID + (i++))
                .setContentTitle("ได้เวลาทานยาแล้ว!")
                .setSound(alarmSound)
                .setContentText("ได้เวลาทานยาของคุณแล้วตอนนี้ โปรดทวนความจำของคุณ")
                .setSmallIcon(R.drawable.ic_android_show)
                .setAutoCancel(false)
                .setContentIntent(pendingIntent);

    }
}
