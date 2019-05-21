package com.example.medicinealertapplication.User;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinealertapplication.Alarm.EditTimeActivity;
import com.example.medicinealertapplication.Alarm.TimeMedActivity;
import com.example.medicinealertapplication.AllMedicine.AllMedActivity;
import com.example.medicinealertapplication.HomeActivity;
import com.example.medicinealertapplication.KnowledgeActivity;
import com.example.medicinealertapplication.R;

public class LogoutActivity extends AppCompatActivity {
    Button logoutbtn;
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    BottomNavigationView navigationView ;
    TextView time1,time2,time3,editTime,user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");

        time1 = (TextView)findViewById(R.id.time1);
        time2 = (TextView)findViewById(R.id.time2);
        time3 = (TextView)findViewById(R.id.time3);
        editTime = (TextView)findViewById(R.id.editTime);
        user = (TextView)findViewById(R.id.userName);

        user.setText(userName);
        time1.setText("มื้อเช้า : "+userMorning);
        time2.setText("มื้อกลางวัน : "+userAfter);
        time3.setText("มื้อเย็น : "+userEven);

        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomePage = new Intent(LogoutActivity.this, SetTimeToEatActivity.class);
                HomePage.putExtra("idUser",userID);
                HomePage.putExtra("nameUser",userName);
                HomePage.putExtra("passUser",userPass);
                HomePage.putExtra("mornUser",userMorning);
                HomePage.putExtra("afterUser",userAfter);
                HomePage.putExtra("evenUser",userEven);
                startActivity(HomePage);
            }
        });

        logoutbtn = (Button)findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LogoutActivity.this,"ล้อคเอาท์สำเร็จ",Toast.LENGTH_SHORT).show();

                Intent LogoutIntent = new Intent(LogoutActivity.this,RegisterActivity.class);
                startActivity(LogoutIntent);
            }
        });


        navigationView = findViewById(R.id.bottom_nav);

        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home){
                    Intent HomePage = new Intent(LogoutActivity.this, HomeActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }else if (id == R.id.know) {
                    Intent HomePage = new Intent(LogoutActivity.this, KnowledgeActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }else if (id == R.id.timer) {
                    Intent HomePage = new Intent(LogoutActivity.this, TimeMedActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }else if (id == R.id.account){
                    Intent HomePage = new Intent(LogoutActivity.this, LogoutActivity.class);
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
