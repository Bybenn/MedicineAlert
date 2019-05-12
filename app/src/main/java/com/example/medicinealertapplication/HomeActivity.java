package com.example.medicinealertapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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

public class HomeActivity extends AppCompatActivity {
    ImageView viewAllMed;
    ImageView viewYourMed;
    ImageView viewTimeMed;
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
        setContentView(R.layout.activity_home);


        viewAllMed = (ImageView) findViewById(R.id.viewAllMed);
        viewYourMed = (ImageView) findViewById(R.id.viewYourMed);
        viewTimeMed = (ImageView) findViewById(R.id.viewTimeMed);

        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");


        viewAllMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(getApplicationContext(), AllMedActivity.class);
//                Intent mainIntent = new Intent(HomeActivity.this, AllMedActivity.class);
                editIntent.putExtra("idUser",userID);
                editIntent.putExtra("nameUser",userName);
                editIntent.putExtra("passUser",userPass);
                editIntent.putExtra("mornUser",userMorning);
                editIntent.putExtra("afterUser",userAfter);
                editIntent.putExtra("evenUser",userEven);
                startActivity(editIntent);
            }
        });

        viewYourMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(getApplicationContext(), YourMedicineActivity.class);
                editIntent.putExtra("idUser",userID);
                editIntent.putExtra("nameUser",userName);
                editIntent.putExtra("passUser",userPass);
                editIntent.putExtra("mornUser",userMorning);
                editIntent.putExtra("afterUser",userAfter);
                editIntent.putExtra("evenUser",userEven);
                startActivity(editIntent);
            }
        });

        viewTimeMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(getApplicationContext(), TimeMedActivity.class);
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

        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home){
                    Intent HomePage = new Intent(HomeActivity.this, HomeActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }else if (id == R.id.know) {
                    Intent HomePage = new Intent(HomeActivity.this, KnowledgeActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }else if (id == R.id.timer) {
                    Intent HomePage = new Intent(HomeActivity.this, TimeMedActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }else if (id == R.id.account){
                    Intent HomePage = new Intent(HomeActivity.this, LogoutActivity.class);
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
}
