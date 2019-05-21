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
import com.example.medicinealertapplication.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class EditTimeActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    TextView nameMed;
    TextView timeText;
    TextView noteText;
    ImageView timeImage;
    Button editTime;
    String a;
    int b=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_time);
        final TimeList editTimeList = (TimeList) getIntent().getSerializableExtra("editTimeList");
        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");

        nameMed = (TextView)findViewById(R.id.nameMed);
        timeText = (TextView)findViewById(R.id.timeText);
        noteText = (TextView)findViewById(R.id.noteText);
        timeImage = (ImageView)findViewById(R.id.timeImage);
        editTime = (Button)findViewById(R.id.editTime);

        nameMed.setText(editTimeList.getNameMed());
        timeText.setText(editTimeList.getTime());
        if (editTimeList.getNote().isEmpty()){
            noteText.setText("ไม่มีโน้ตเตือนความจำ");
        }else {
            noteText.setText(editTimeList.getNote());
        }

        Button delBtn = (Button) findViewById(R.id.delete_btn);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeListDAO timeListDAODel = new TimeListDAO(getApplicationContext());
                timeListDAODel.open();
                timeListDAODel.delete(editTimeList);
                timeListDAODel.close();
                finish();
            }
        });

        timeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                b = 1;
            }
        });

        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeList timeList = new TimeList();
                timeList.setNameMed(String.valueOf(nameMed.getText()));
                timeList.setNote(String.valueOf(noteText.getText()));
                timeList.setTime(String.valueOf(timeText.getText()));
                timeList.setUserID(Integer.parseInt(userID));


                TimeListDAO timeListDAO = new TimeListDAO(getApplicationContext());
                timeListDAO.open();
                timeListDAO.add(timeList);
                timeListDAO.close();
                finish();

                Intent HomePage = new Intent(EditTimeActivity.this, TimeMedActivity.class);
                HomePage.putExtra("idUser",userID);
                HomePage.putExtra("nameUser",userName);
                HomePage.putExtra("passUser",userPass);
                HomePage.putExtra("mornUser",userMorning);
                HomePage.putExtra("afterUser",userAfter);
                HomePage.putExtra("evenUser",userEven);
                startActivity(HomePage);
            }
        });
    }

    @Override
    public void onTimeSet (TimePicker view, int hourOfDay, int minute){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 00);

        if (b == 1) {
            updateTimeText1(c);

        }

        startAlarm(c);
    }

    /* เมื่อตั้งเวลาแล้วมันจะแจ้งเตือนเฉพาะเวลาล่าสุดที่แอดเพิ่มเข้าไป แต่ตัวโปรแกรมต้องการให้แจ้งเตือนทุกเวลาที่แอดเข้าไป  */
    private void startAlarm (Calendar c){
//            for (int i = 0;i<10;i++) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY/*alarmManager.*/, pendingIntent);

        //alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
//            }

    }


    private void updateTimeText1 (Calendar c){
        a = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        timeText.setText("เวลาในการทานยาของคุณ : ");
        timeText.setText(a);
    }


    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.cancel(pendingIntent);
    }
}
