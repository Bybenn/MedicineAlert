package com.example.medicinealertapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.medicinealertapplication.Alarm.TimeMedActivity;
import com.example.medicinealertapplication.AllMedicine.AllMedActivity;
import com.example.medicinealertapplication.User.LogoutActivity;
import com.example.medicinealertapplication.YourMedicine.YourMedicineActivity;

public class KnowledgeActivity extends AppCompatActivity {

    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    BottomNavigationView navigationView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);


        Intent intent = getIntent();

        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");

        navigationView = findViewById(R.id.bottom_nav);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home){
                    Intent HomePage = new Intent(KnowledgeActivity.this, HomeActivity.class);
                    HomePage.putExtra("idUser", userID);
                    HomePage.putExtra("nameUser", userName);
                    HomePage.putExtra("passUser", userPass);
                    HomePage.putExtra("mornUser", userMorning);
                    HomePage.putExtra("afterUser", userAfter);
                    HomePage.putExtra("evenUser", userEven);
                    startActivity(HomePage);
                    return true;
                }else if (id == R.id.warehouse) {
                    Intent allmedIntent = new Intent(KnowledgeActivity.this, AllMedActivity.class);
                    allmedIntent.putExtra("idUser", userID);
                    allmedIntent.putExtra("nameUser", userName);
                    allmedIntent.putExtra("passUser", userPass);
                    allmedIntent.putExtra("mornUser", userMorning);
                    allmedIntent.putExtra("afterUser", userAfter);
                    allmedIntent.putExtra("evenUser", userEven);
                    startActivity(allmedIntent);
                    return true;
                }else if (id == R.id.ymedicine) {
                    Intent ymedicineIntent = new Intent(KnowledgeActivity.this, YourMedicineActivity.class);
                    ymedicineIntent.putExtra("idUser", userID);
                    ymedicineIntent.putExtra("nameUser", userName);
                    ymedicineIntent.putExtra("passUser", userPass);
                    ymedicineIntent.putExtra("mornUser", userMorning);
                    ymedicineIntent.putExtra("afterUser", userAfter);
                    ymedicineIntent.putExtra("evenUser", userEven);
                    startActivity(ymedicineIntent);
                    return true;
                }else if (id == R.id.timer) {
                    Intent timemedIntent = new Intent(KnowledgeActivity.this, TimeMedActivity.class);
                    timemedIntent.putExtra("idUser", userID);
                    timemedIntent.putExtra("nameUser", userName);
                    timemedIntent.putExtra("passUser", userPass);
                    timemedIntent.putExtra("mornUser", userMorning);
                    timemedIntent.putExtra("afterUser", userAfter);
                    timemedIntent.putExtra("evenUser", userEven);
                    startActivity(timemedIntent);
                    return true;
                }else if (id == R.id.account) {
                    Intent logountIntent = new Intent(KnowledgeActivity.this, LogoutActivity.class);
                    logountIntent.putExtra("idUser", userID);
                    logountIntent.putExtra("nameUser", userName);
                    logountIntent.putExtra("passUser", userPass);
                    logountIntent.putExtra("mornUser", userMorning);
                    logountIntent.putExtra("afterUser", userAfter);
                    logountIntent.putExtra("evenUser", userEven);
                    startActivity(logountIntent);
                    return true;
                }
                return false;
            }
        });

    }
}
