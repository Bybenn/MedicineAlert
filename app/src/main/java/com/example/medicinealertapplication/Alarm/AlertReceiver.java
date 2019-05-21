package com.example.medicinealertapplication.Alarm;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.medicinealertapplication.QuestionActivity;
import com.example.medicinealertapplication.R;
import com.example.medicinealertapplication.User.LoginActivity;
import com.example.medicinealertapplication.User.RegisterActivity;
import com.example.medicinealertapplication.User.SetTimeToEatActivity;
import com.example.medicinealertapplication.User.User;

import static com.example.medicinealertapplication.Alarm.NotificationHelper.channelID;

public class AlertReceiver extends BroadcastReceiver {
    NotificationHelper notificationHelper;
    PendingIntent pendingIntent;
    User user = new User();
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;


    @Override
    public void onReceive(Context context, Intent intent) {
//        for (int i = 0;i<2;i++){
            notificationHelper = new NotificationHelper(context);
            getChannelNotification();
//            Intent intent1 = new Intent(context, TimeMedActivity.class);
            user.setUserID(Integer.parseInt(LoginActivity.loginID));
            user.setUserName(LoginActivity.user);
            user.setUserPass(LoginActivity.pass);
            userID =  LoginActivity.loginID;
            userName = LoginActivity.user;
            userPass = LoginActivity.pass;
            userMorning = SetTimeToEatActivity.morning;
            userAfter = SetTimeToEatActivity.after;
            userEven = SetTimeToEatActivity.even;
            Intent HomePage = new Intent(context, QuestionActivity.class);
            HomePage.putExtra("idUser",userID);
            HomePage.putExtra("nameUser",userName);
            HomePage.putExtra("passUser",userPass);
            HomePage.putExtra("mornUser",userMorning);
            HomePage.putExtra("afterUser",userAfter);
            HomePage.putExtra("evenUser",userEven);
//            startActivity(HomePage);
            HomePage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pendingIntent = PendingIntent.getActivity(context, 1, HomePage, PendingIntent.FLAG_UPDATE_CURRENT);

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
