package com.example.medicinealertapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.medicinealertapplication.Alarm.TimeMedActivity;
import com.example.medicinealertapplication.AllMedicine.AllMedActivity;
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
                startActivity(editIntent);
            }
        });


    }
}
