package com.example.medicinealertapplication.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.medicinealertapplication.R;

import java.text.DateFormat;
import java.util.Calendar;

public class EditTimeActivity extends AppCompatActivity {

    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    TextView nameMed;
    TextView timeText;
    TextView noteText;
    TextView timeInfoMed;
    TextView timeToEat;

    String infoMed;
    String timeEat;


    int requestId;
    final int selectedTimeLimit = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_time);
        final TimeList editTimeList = (TimeList) getIntent().getSerializableExtra("editTimeList");
        requestId = editTimeList.getId();
        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");

        nameMed = (TextView) findViewById(R.id.nameMed);
        noteText = (TextView) findViewById(R.id.noteText);
        timeText = (TextView) findViewById(R.id.timeText);
        timeInfoMed = (TextView) findViewById(R.id.timeInfoMed);
        timeToEat = (TextView) findViewById(R.id.timeEat);

        nameMed.setText(editTimeList.getNameMed());
        timeInfoMed.setText(editTimeList.getInfo());
        timeText.setText(editTimeList.getTime());
        if (editTimeList.getTimeeat().equals("01")){
            timeEat = "ยาก่อนอาหาร";
            timeToEat.setText(timeEat);
        }else if(editTimeList.getTimeeat().equals("02")){
            timeEat = "ยาหลังอาหาร";
            timeToEat.setText(timeEat);
        }else if(editTimeList.getTimeeat().equals("03")){
            timeEat = "ยาก่อนนอน";
            timeToEat.setText(timeEat);
        }else if(editTimeList.getTimeeat().equals("04")){
            timeEat = "ทานเมื่อมีอาการทุก 4 ชั่วโมง";
            timeToEat.setText(timeEat);
        }else if(editTimeList.getTimeeat().equals("05")){
            timeEat = "ทานเมื่อมีอาการทุก 6 ชั่วโมง";
            timeToEat.setText(timeEat);
        }else if(editTimeList.getTimeeat().equals("06")){
            timeEat = "ทานเมื่อมีอาการทุก 12 ชั่วโมง";
            timeToEat.setText(timeEat);
        }

        if (editTimeList.getNote().isEmpty()) {
            noteText.setText("ไม่มีโน้ตเตือนความจำ");
        } else {
            noteText.setText(editTimeList.getNote());
        }

        Button delBtn = (Button) findViewById(R.id.delete_btn);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeListDAO timeListDAODel = new TimeListDAO(getApplicationContext());
                timeListDAODel.open();
                timeListDAODel.delete(editTimeList);
                cancelAlarm();
                timeListDAODel.close();
                finish();
            }
        });

//        timeImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                DialogFragment timePicker = new TimePickerFragment();
//                DialogFragment timePicker = new DialogFragment();
//
//                timePicker.show(getSupportFragmentManager(), "time picker");
//                b = 1;
//            }
//        });

//        editTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TimeList timeList = new TimeList();
//                timeList.setNameMed(String.valueOf(nameMed.getText()));
//                timeList.setNote(String.valueOf(noteText.getText()));
//                timeList.setTime(String.valueOf(timeText.getText()));
//                timeList.setUserID(Integer.parseInt(userID));
//
//
//                TimeListDAO timeListDAO = new TimeListDAO(getApplicationContext());
//                timeListDAO.open();
//                timeListDAO.add(timeList);
//                timeListDAO.close();
//                finish();
//
//                Intent HomePage = new Intent(EditTimeActivity.this, TimeMedActivity.class);
//                HomePage.putExtra("idUser",userID);
//                HomePage.putExtra("nameUser",userName);
//                HomePage.putExtra("passUser",userPass);
//                HomePage.putExtra("mornUser",userMorning);
//                HomePage.putExtra("afterUser",userAfter);
//                HomePage.putExtra("evenUser",userEven);
//                startActivity(HomePage);
//            }
//        });
    }

//    @Override
//    public void onTimeSet (TimePicker view, int hourOfDay, int minute){
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//        c.set(Calendar.MINUTE, minute);
//        c.set(Calendar.SECOND, 00);
//
//    }
//
//    /* เมื่อตั้งเวลาแล้วมันจะแจ้งเตือนเฉพาะเวลาล่าสุดที่แอดเพิ่มเข้าไป แต่ตัวโปรแกรมต้องการให้แจ้งเตือนทุกเวลาที่แอดเข้าไป  */
//    private void startAlarm (Calendar c){
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent notificationIntent = new Intent(this, AlertReceiver.class);
//        notificationIntent.putExtra("requestCode", requestId);
//        PendingIntent broadcast = PendingIntent.getBroadcast(this, requestId,
//                notificationIntent, PendingIntent.FLAG_ONE_SHOT);
//        c.add(Calendar.SECOND, 1);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), broadcast);
//
//    }
//
//
//    private void updateTimeText1 (Calendar c){
//        a = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
//        timeText.setText("เวลาในการทานยาของคุณ : ");
//        timeText.setText(a);
//    }


    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), requestId, intent, PendingIntent.FLAG_ONE_SHOT);
        alarmManager.cancel(pendingIntent);
    }
}
