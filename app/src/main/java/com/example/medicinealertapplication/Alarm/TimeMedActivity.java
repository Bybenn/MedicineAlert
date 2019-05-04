package com.example.medicinealertapplication.Alarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.medicinealertapplication.R;

import java.util.ArrayList;

public class TimeMedActivity extends AppCompatActivity {
    ListView timeListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_med);

        timeListView = (ListView)findViewById(R.id.time_listView);

        TimeListDAO timeListDAO = new TimeListDAO(getApplicationContext());
        timeListDAO.open();
        ArrayList<TimeList> myList = timeListDAO.getAllList();
        final MyTime adapter = new MyTime(this,myList);
        timeListView.setAdapter(adapter);
        timeListDAO.close();

        timeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
//            คืนค่าเป็นไอดีเพื่อเอาไปแก้ไข
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),String.valueOf(adapter.getItemId(position)),
//                        Toast.LENGTH_SHORT).show();
                Intent editIntent = new Intent(getApplicationContext(),EditTimeActivity.class);
                editIntent.putExtra("editTimeList",adapter.getItem(position));
                startActivity(editIntent);
            }
        });

    }
    public void onResume() {

        super.onResume();
        TimeListDAO medListDAO = new TimeListDAO(getApplicationContext());
        medListDAO.open();
        ArrayList<TimeList> myList = medListDAO.getAllList();
        MyTime adapter = new MyTime(this,myList);
        timeListView.setAdapter(adapter);
        medListDAO.close();
    }
}
