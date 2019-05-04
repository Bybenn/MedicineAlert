package com.example.medicinealertapplication.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.medicinealertapplication.R;

public class EditTimeActivity extends AppCompatActivity {

    TextView nameMed;
    TextView timeText;
    TextView noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_time);
        final TimeList editTimeList = (TimeList) getIntent().getSerializableExtra("editTimeList");

        nameMed = (TextView)findViewById(R.id.nameMed);
        timeText = (TextView)findViewById(R.id.timeText);
        noteText = (TextView)findViewById(R.id.noteText);

        nameMed.setText(editTimeList.getNameMed());
        timeText.setText(editTimeList.getTime());
        if (editTimeList.getNote().equals("")){
            noteText.setText("ไม่มีโน้ตเตือนความจำ");
        }
        noteText.setText(editTimeList.getNote());

        Button delBtn = (Button) findViewById(R.id.delete_btn);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeListDAO timeListDAODel = new TimeListDAO(getApplicationContext());
                timeListDAODel.open();
                timeListDAODel.delete(editTimeList);
                timeListDAODel.close();
                cancelAlarm();
                finish();
            }
        });
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.cancel(pendingIntent);
    }
}
