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
import android.widget.Toast;

import com.example.medicinealertapplication.R;
import com.example.medicinealertapplication.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class SetAlarmActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    TextView nameMed;
    TextView noteText;
//    TextView timeInfoMed;
//    TextView timeToEat;
    TextView suggestTimer;


    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    Button addSetTimeButton;
    public static String medName;
    String infoMed;
    String timeEat;

    public static long requestId = -1;
    final int selectedTimeLimit = 5;
    int currentSelectedTime = 0;
    Calendar[] selectedTime = new Calendar[selectedTimeLimit];
    TextView[] selectAlarm;
    TextView[] showAlarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        selectAlarm = new TextView[]{
                findViewById(R.id.selectAlarm1),
                findViewById(R.id.selectAlarm2)
        };
        showAlarm = new TextView[]{
                findViewById(R.id.showAlarm1),
                findViewById(R.id.showAlarm2)
        };

        nameMed = (TextView) findViewById(R.id.nameMed);
        noteText = (TextView) findViewById(R.id.noteText);
//        timeInfoMed = (TextView) findViewById(R.id.timeInfoMed);
//        timeToEat = (TextView) findViewById(R.id.timeEat);
        addSetTimeButton = (Button) findViewById(R.id.addnew_button);
        suggestTimer = (TextView)findViewById(R.id.suggestTimer);



        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");
        medName = intent.getStringExtra("nameMed");
        infoMed = intent.getStringExtra("infoMed");
        timeEat = intent.getStringExtra("timeEat");
        nameMed.setText(medName);
//        timeInfoMed.setText(infoMed);
//        timeToEat.setText(timeEat);
        getTime();

        for (final TextView tv : selectAlarm) {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < selectAlarm.length; i++) {
                        if (selectAlarm[i] == tv) {
                            currentSelectedTime = i;
                        }
                    }
                    DialogFragment timePicker = new TimePickerFragment();
                    timePicker.show(getSupportFragmentManager(), "time picker");
                }
            });
        }

        addSetTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTime();
            }
        });
    }

    private void submitTime() {

        boolean hasAlarmSet = false;
        for (Calendar c : selectedTime) {
            if (c != null) {
                hasAlarmSet = true;
                break;
            }
        }

        if (!hasAlarmSet) {
            Toast.makeText(SetAlarmActivity.this, "กรุณาตั้งค่าเวลาในการแจ้งเตือน", Toast.LENGTH_SHORT).show();

        } else {

            for (int i = 0; i < selectAlarm.length; i++) {

                if (selectedTime[i] == null) {
                    continue;
                }

                TimeList timeList = new TimeList();
                timeList.setNameMed(String.valueOf(nameMed.getText()));
                timeList.setNote(String.valueOf(noteText.getText()));
                timeList.setTime(String.valueOf(showAlarm[i].getText()));
                timeList.setInfo(infoMed);
                timeList.setTimeeat(timeEat);
                timeList.setUserID(Integer.parseInt(userID));
                TimeListDAO timeListDAO = new TimeListDAO(getApplicationContext());
                timeListDAO.open();
                requestId = timeListDAO.add(timeList);
                timeListDAO.close();

                startAlarm(selectedTime[i], (int) requestId);
            }

            Intent timeMedPage = new Intent(SetAlarmActivity.this, TimeMedActivity.class);
            timeMedPage.putExtra("nameMed", medName);
            timeMedPage.putExtra("idUser", userID);
            timeMedPage.putExtra("nameUser", userName);
            timeMedPage.putExtra("passUser", userPass);
            timeMedPage.putExtra("mornUser", userMorning);
            timeMedPage.putExtra("afterUser", userAfter);
            timeMedPage.putExtra("evenUser", userEven);
            timeMedPage.putExtra("requestId", requestId);
            startActivity(timeMedPage);
            finish();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 00);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        showAlarm[currentSelectedTime].setText("" + DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime()));
        selectedTime[currentSelectedTime] = c;
    }


    private void startAlarm(Calendar c, int requestId) {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(this, AlertReceiver.class);
        notificationIntent.putExtra("requestCode", requestId);
        notificationIntent.putExtra("nameMed", medName);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, requestId,
                notificationIntent, PendingIntent.FLAG_ONE_SHOT);
        c.add(Calendar.SECOND, 1);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), broadcast);

    }

    public void getTime(){
        if (timeEat.equals("01")){
            int bmor = Integer.parseInt(userMorning.substring(0,2));
            int cmor = Integer.parseInt(userMorning.substring(3));
            int dmor = bmor*60*60;
            int emor = cmor*60;
            int fmor = dmor+emor;
            int gmor = 30*60;
            int hmor = fmor-gmor;
            int hrmor =hmor/3600;
            int minutemor =(hmor%3600)/60;

            int bafter = Integer.parseInt(userAfter.substring(0,2));
            int cafter = Integer.parseInt(userAfter.substring(3));
            int dafter = bafter*60*60;
            int eafter = cafter*60;
            int fafter = dafter+eafter;
            int gafter = 30*60;
            int hafter = fafter-gafter;
            int hrafter =hafter/3600;
            int minuteafter =(hafter%3600)/60;

            int beve = Integer.parseInt(userEven.substring(0,2));
            int ceve = Integer.parseInt(userEven.substring(3));
            int deve = beve*60*60;
            int eeve = ceve*60;
            int feve = deve+eeve;
            int geve = 30*60;
            int heve = feve-geve;
            int hreve =heve/3600;
            int minuteeve =(heve%3600)/60;

            suggestTimer.setText("มื้อเช้าที่ควรทาน "+hrmor+" นาฬิกา "+minutemor+" นาที\n"+
                    "มื้อกลางวันที่ควรทาน "+hrafter+" นาฬิกา "+minuteafter+" นาที\n"+
                    "มื้อเย็นที่ควรทาน "+hreve+" นาฬิกา "+minuteeve+" นาที");
        }else if (timeEat.equals("02")){
            int bmor = Integer.parseInt(userMorning.substring(0,2));
            int cmor = Integer.parseInt(userMorning.substring(3));
            int dmor = bmor*60*60;
            int emor = cmor*60;
            int fmor = dmor+emor;
            int gmor = 30*60;
            int hmor = fmor+gmor;
            int hrmor =(hmor/3600)%24;
            int minutemor =(hmor%3600)/60;


            int bafter = Integer.parseInt(userAfter.substring(0,2));
            int cafter = Integer.parseInt(userAfter.substring(3));
            int dafter = bafter*60*60;
            int eafter = cafter*60;
            int fafter = dafter+eafter;
            int gafter = 30*60;
            int hafter = fafter+gafter;
            int hrafter =(hafter/3600)%24;
            int minuteafter =(hafter%3600)/60;


            int beve = Integer.parseInt(userEven.substring(0,2));
            int ceve = Integer.parseInt(userEven.substring(3));
            int deve = beve*60*60;
            int eeve = ceve*60;
            int feve = deve+eeve;
            int geve = 30*60;
            int heve = feve+geve;
            int hreve =(heve/3600)%24;
            int minuteeve =(heve%3600)/60;


            suggestTimer.setText("มื้อเช้าที่ควรทาน "+hrmor+" นาฬิกา "+minutemor+" นาที\n"+
                    "มื้อกลางวันที่ควรทาน "+hrafter+" นาฬิกา "+minuteafter+" นาที\n"+
                    "มื้อเย็นที่ควรทาน "+hreve+" นาฬิกา "+minuteeve+" นาที");
        }else if (timeEat.equals("03")){
            int b = Integer.parseInt(userEven.substring(0,2));
            int c = Integer.parseInt(userEven.substring(3));
            int d = b*60*60;
            int e = c*60;
            int f = d+e;
            int g = 120*60;
            int h = f+g;
            int hr =h/3600;
            int minute =(h%3600)/60;
            suggestTimer.setText("ควรทานก่อนนอนเวลา "+hr+" นาฬิกา "+minute+" นาที");
        }else if (timeEat.equals("04")){
//          4hr
            Date date = new Date();
            String time = date.toString();

            int b = Integer.parseInt(time.substring(11,13));
            int c = Integer.parseInt(time.substring(14,16));
            int d = b*60*60;
            int e = c*60;
            int f = d+e;
            int g = 4*60*60;
            int h = f+g;
            int i = f+(g*2);
            int hr2 =(h/3600)%24;
            int minute2 =(h%3600)/60;
            int hr3 =(i/3600)%24;
            int minute3 =(h%3600)/60;
            suggestTimer.setText("ครั้งแรกที่ควรทาน "+b+" นาฬิกา"+c+" นาที\n"+
                    "ครั้งถัดไป "+hr2+" นาฬิกา "+minute2+" นาที\n"+
                    "และครั้งถัดไป "+hr3+" นาฬิกา "+minute3+" นาที");
        }else if (timeEat.equals("05")){
//            6hr
            Date date = new Date();
            String time = date.toString();

            int b = Integer.parseInt(time.substring(11,13));
            int c = Integer.parseInt(time.substring(14,16));
            int d = b*60*60;
            int e = c*60;
            int f = d+e;
            int g = 6*60*60;
            int h = f+g;
            int i = f+(g*2);
            int hr2 =(h/3600)%24;
            int minute2 =(h%3600)/60;
            int hr3 =(i/3600)%24;
            int minute3 =(h%3600)/60;
            suggestTimer.setText("ครั้งแรกที่ควรทาน "+b+" นาฬิกา"+c+" นาที\n"+
                    "ครั้งถัดไป "+hr2+" นาฬิกา "+minute2+" นาที\n"+
                    "และครั้งถัดไป "+hr3+" นาฬิกา "+minute3+" นาที");
        }else if (timeEat.equals("06")){
//            12hr
            Date date = new Date();
            String time = date.toString();

            int b = Integer.parseInt(time.substring(11,13));
            int c = Integer.parseInt(time.substring(14,16));
            int d = b*60*60;
            int e = c*60;
            int f = d+e;
            int g = 12*60*60;
            int h = f+g;
            int i = f+(g*2);
            int hr2 =(h/3600)%24;
            int minute2 =(h%3600)/60;
            int hr3 =(i/3600)%24;
            int minute3 =(h%3600)/60;
            suggestTimer.setText("ครั้งแรกที่ควรทาน "+b+" นาฬิกา"+c+" นาที\n"+
                    "ครั้งถัดไป "+hr2+" นาฬิกา "+minute2+" นาที\n"+
                    "และครั้งถัดไป "+hr3+" นาฬิกา "+minute3+" นาที");
        }
    }


}