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
import android.widget.Toast;

import com.example.medicinealertapplication.AllMedicine.AllMedActivity;
import com.example.medicinealertapplication.HomeActivity;
import com.example.medicinealertapplication.KnowledgeActivity;
import com.example.medicinealertapplication.R;
import com.example.medicinealertapplication.User.LogoutActivity;
import com.example.medicinealertapplication.YourMedicine.YourMedicineActivity;
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
    long requestId;

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
        requestId = intent.getLongExtra("requestId", -1);

        timeListView = (ListView)findViewById(R.id.time_listView);

        TimeListDAO timeListDAO = new TimeListDAO(getApplicationContext());
        timeListDAO.open();
        ArrayList<TimeList> myList = timeListDAO.getAllList();
        final MyTime adapter = new MyTime(this,myList);
        timeListView.setAdapter(adapter);
        timeListDAO.close();

        timeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent editIntent = new Intent(getApplicationContext(),EditTimeActivity.class);
                editIntent.putExtra("editTimeList",adapter.getItem(position));
                editIntent.putExtra("idUser",userID);
                editIntent.putExtra("nameUser",userName);
                editIntent.putExtra("passUser",userPass);
                editIntent.putExtra("mornUser",userMorning);
                editIntent.putExtra("afterUser",userAfter);
                editIntent.putExtra("evenUser",userEven);
                startActivity(editIntent);
            }
        });

        navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home){
                    Intent HomePage = new Intent(TimeMedActivity.this, HomeActivity.class);
                    HomePage.putExtra("idUser", userID);
                    HomePage.putExtra("nameUser", userName);
                    HomePage.putExtra("passUser", userPass);
                    HomePage.putExtra("mornUser", userMorning);
                    HomePage.putExtra("afterUser", userAfter);
                    HomePage.putExtra("evenUser", userEven);
                    startActivity(HomePage);
                    return true;
                }else if (id == R.id.warehouse) {
                    Intent allmedIntent = new Intent(TimeMedActivity.this, AllMedActivity.class);
                    allmedIntent.putExtra("idUser", userID);
                    allmedIntent.putExtra("nameUser", userName);
                    allmedIntent.putExtra("passUser", userPass);
                    allmedIntent.putExtra("mornUser", userMorning);
                    allmedIntent.putExtra("afterUser", userAfter);
                    allmedIntent.putExtra("evenUser", userEven);
                    startActivity(allmedIntent);
                    return true;
                }else if (id == R.id.ymedicine) {
                    Intent ymedicineIntent = new Intent(TimeMedActivity.this, YourMedicineActivity.class);
                    ymedicineIntent.putExtra("idUser", userID);
                    ymedicineIntent.putExtra("nameUser", userName);
                    ymedicineIntent.putExtra("passUser", userPass);
                    ymedicineIntent.putExtra("mornUser", userMorning);
                    ymedicineIntent.putExtra("afterUser", userAfter);
                    ymedicineIntent.putExtra("evenUser", userEven);
                    startActivity(ymedicineIntent);
                    return true;
                }else if (id == R.id.timer) {
                    Intent timemedIntent = new Intent(TimeMedActivity.this, TimeMedActivity.class);
                    timemedIntent.putExtra("idUser", userID);
                    timemedIntent.putExtra("nameUser", userName);
                    timemedIntent.putExtra("passUser", userPass);
                    timemedIntent.putExtra("mornUser", userMorning);
                    timemedIntent.putExtra("afterUser", userAfter);
                    timemedIntent.putExtra("evenUser", userEven);
                    startActivity(timemedIntent);
                    return true;
                }else if (id == R.id.account) {
                    Intent logoutIntent = new Intent(TimeMedActivity.this, LogoutActivity.class);
                    logoutIntent.putExtra("idUser", userID);
                    logoutIntent.putExtra("nameUser", userName);
                    logoutIntent.putExtra("passUser", userPass);
                    logoutIntent.putExtra("mornUser", userMorning);
                    logoutIntent.putExtra("afterUser", userAfter);
                    logoutIntent.putExtra("evenUser", userEven);
                    startActivity(logoutIntent);
                    return true;
                }
                return false;
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
