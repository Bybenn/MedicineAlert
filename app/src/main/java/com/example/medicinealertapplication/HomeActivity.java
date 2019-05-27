package com.example.medicinealertapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.medicinealertapplication.Alarm.TimeMedActivity;
import com.example.medicinealertapplication.AllMedicine.AllMedActivity;
import com.example.medicinealertapplication.User.LogoutActivity;
import com.example.medicinealertapplication.User.SetTimeToEatActivity;
import com.example.medicinealertapplication.User.User;
import com.example.medicinealertapplication.YourMedicine.YourMedicineActivity;

public class HomeActivity extends AppCompatActivity  {
    ImageView viewAllMed;
    ImageView viewYourMed;
    ImageView viewTimeMed;
    ImageView viewKnow;
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    BottomNavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        viewAllMed = (ImageView) findViewById(R.id.viewAllMed);
        viewYourMed = (ImageView) findViewById(R.id.viewYourMed);
        viewTimeMed = (ImageView) findViewById(R.id.viewTimeMed);
        viewKnow = (ImageView) findViewById(R.id.viewKnow);

        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");

        navigationView = findViewById(R.id.bottom_nav);

        viewAllMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allmedIntent = new Intent(getApplicationContext(), AllMedActivity.class);
                allmedIntent.putExtra("idUser", userID);
                allmedIntent.putExtra("nameUser", userName);
                allmedIntent.putExtra("passUser", userPass);
                allmedIntent.putExtra("mornUser", userMorning);
                allmedIntent.putExtra("afterUser", userAfter);
                allmedIntent.putExtra("evenUser", userEven);
                startActivity(allmedIntent);
            }
        });

        viewYourMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent youmedIntent = new Intent(getApplicationContext(), YourMedicineActivity.class);
                youmedIntent.putExtra("idUser", userID);
                youmedIntent.putExtra("nameUser", userName);
                youmedIntent.putExtra("passUser", userPass);
                youmedIntent.putExtra("mornUser", userMorning);
                youmedIntent.putExtra("afterUser", userAfter);
                youmedIntent.putExtra("evenUser", userEven);
                startActivity(youmedIntent);
            }
        });

        viewTimeMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timemedIntent = new Intent(getApplicationContext(), TimeMedActivity.class);
                timemedIntent.putExtra("idUser", userID);
                timemedIntent.putExtra("nameUser", userName);
                timemedIntent.putExtra("passUser", userPass);
                timemedIntent.putExtra("mornUser", userMorning);
                timemedIntent.putExtra("afterUser", userAfter);
                timemedIntent.putExtra("evenUser", userEven);
                startActivity(timemedIntent);

            }
        });
        viewKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent knowIntent = new Intent(getApplicationContext(), KnowledgeActivity.class);
                knowIntent.putExtra("idUser", userID);
                knowIntent.putExtra("nameUser", userName);
                knowIntent.putExtra("passUser", userPass);
                knowIntent.putExtra("mornUser", userMorning);
                knowIntent.putExtra("afterUser", userAfter);
                knowIntent.putExtra("evenUser", userEven);
                startActivity(knowIntent);

            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home){
                    Intent HomePage = new Intent(HomeActivity.this, HomeActivity.class);
                    HomePage.putExtra("idUser", userID);
                    HomePage.putExtra("nameUser", userName);
                    HomePage.putExtra("passUser", userPass);
                    HomePage.putExtra("mornUser", userMorning);
                    HomePage.putExtra("afterUser", userAfter);
                    HomePage.putExtra("evenUser", userEven);
                    startActivity(HomePage);
                    return true;
                }else if (id == R.id.warehouse) {
                    Intent allmedIntent = new Intent(HomeActivity.this, AllMedActivity.class);
                    allmedIntent.putExtra("idUser", userID);
                    allmedIntent.putExtra("nameUser", userName);
                    allmedIntent.putExtra("passUser", userPass);
                    allmedIntent.putExtra("mornUser", userMorning);
                    allmedIntent.putExtra("afterUser", userAfter);
                    allmedIntent.putExtra("evenUser", userEven);
                    startActivity(allmedIntent);
                    return true;
                }else if (id == R.id.ymedicine) {
                    Intent HomePage = new Intent(HomeActivity.this, YourMedicineActivity.class);
                    HomePage.putExtra("idUser", userID);
                    HomePage.putExtra("nameUser", userName);
                    HomePage.putExtra("passUser", userPass);
                    HomePage.putExtra("mornUser", userMorning);
                    HomePage.putExtra("afterUser", userAfter);
                    HomePage.putExtra("evenUser", userEven);
                    startActivity(HomePage);
                    return true;
                }else if (id == R.id.timer) {
                    Intent timemedIntent = new Intent(HomeActivity.this, TimeMedActivity.class);
                    timemedIntent.putExtra("idUser", userID);
                    timemedIntent.putExtra("nameUser", userName);
                    timemedIntent.putExtra("passUser", userPass);
                    timemedIntent.putExtra("mornUser", userMorning);
                    timemedIntent.putExtra("afterUser", userAfter);
                    timemedIntent.putExtra("evenUser", userEven);
                    startActivity(timemedIntent);
                    return true;
                }else if (id == R.id.account) {
                    Intent logoutIntent = new Intent(HomeActivity.this, LogoutActivity.class);
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
}
