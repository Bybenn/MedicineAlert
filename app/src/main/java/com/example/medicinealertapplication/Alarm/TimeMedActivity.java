package com.example.medicinealertapplication.Alarm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.medicinealertapplication.HomeActivity;
import com.example.medicinealertapplication.KnowledgeActivity;
import com.example.medicinealertapplication.R;
import com.example.medicinealertapplication.User.LogoutActivity;
import java.util.ArrayList;

public class TimeMedActivity extends AppCompatActivity {
    ListView timeListView;
    BottomNavigationView navigationView ;
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_med);

        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");
        final String s = intent.getStringExtra("nameMed");

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

        navigationView = findViewById(R.id.bottom_nav);

        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home){
                    Intent HomePage = new Intent(TimeMedActivity.this, HomeActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }else if (id == R.id.know) {
                    Intent HomePage = new Intent(TimeMedActivity.this, KnowledgeActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }else if (id == R.id.timer) {
                    Intent HomePage = new Intent(TimeMedActivity.this, TimeMedActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }else if (id == R.id.account){
                    Intent HomePage = new Intent(TimeMedActivity.this, LogoutActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }
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
