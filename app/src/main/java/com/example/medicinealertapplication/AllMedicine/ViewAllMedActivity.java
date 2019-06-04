package com.example.medicinealertapplication.AllMedicine;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinealertapplication.DatabaseHelper;
import com.example.medicinealertapplication.R;
import com.example.medicinealertapplication.User.LoginActivity;
import com.example.medicinealertapplication.YourMedicine.MedList;
import com.example.medicinealertapplication.YourMedicine.MedListDAO;
import com.example.medicinealertapplication.YourMedicine.MyMedicine;
import com.example.medicinealertapplication.YourMedicine.YourMedicineActivity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class ViewAllMedActivity extends AppCompatActivity {
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    String nameMed;
    String infoMed;
    String howtoMed;
    String timeMed;
    String positionIma;
    ArrayList<String> allMed = new ArrayList<>();

    Button getAlertButton;
    Button noAlertButton;
    TextView nameMedi;
    TextView infoMedi;
    TextView useMedi;
    Integer[] imgid= {R.mipmap.picture1,R.mipmap.picture2,R.mipmap.picture3,R.mipmap.picture4,
            R.mipmap.picture5,R.mipmap.picture6,R.mipmap.picture7,R.mipmap.picture8,R.mipmap.picture9,
            R.mipmap.picture10,R.mipmap.picture11,R.mipmap.picture12,R.mipmap.picture13,R.mipmap.picture14,
            R.mipmap.picture15,R.mipmap.picture16,R.mipmap.picture17,R.mipmap.picture18,R.mipmap.picture19,
            R.mipmap.picture20,R.mipmap.picture21,R.mipmap.picture22,R.mipmap.picture23,R.mipmap.picture24,
            R.mipmap.picture25,R.mipmap.picture26,R.mipmap.picture27,R.mipmap.picture28,R.mipmap.picture29,
            R.mipmap.picture30,R.mipmap.picture31,R.mipmap.picture32,R.mipmap.picture33,R.mipmap.picture34,
            R.mipmap.picture35,R.mipmap.picture36,R.mipmap.picture37,R.mipmap.picture38,R.mipmap.picture39,
            R.mipmap.picture40};

    ImageView imageMed;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_med);

        nameMedi = (TextView)findViewById(R.id.nameMed);
        infoMedi = (TextView)findViewById(R.id.infoMed);
        useMedi = (TextView)findViewById(R.id.useMed);
        imageMed = (ImageView)findViewById(R.id.imageMed);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getAlertButton = (Button)findViewById(R.id.getAlertButton);
        noAlertButton = (Button)findViewById(R.id.noAlertButton);

        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");
        nameMed = intent.getStringExtra("nameMed");
        infoMed = intent.getStringExtra("infoMed");
        howtoMed = intent.getStringExtra("howtoMed");
        timeMed = intent.getStringExtra("timeMed");
        positionIma  = intent.getStringExtra("positionIma");


        nameMedi.setText(nameMed);
        infoMedi.setText(infoMed);
        useMedi.setText(howtoMed);
        imageMed.setImageResource(imgid[Integer.parseInt(positionIma)]);



        getAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for (String s : allMed){
//                    Log.d("ii", s);
//
//                }
                Intent intent = new Intent(getApplicationContext(), YourMedicineActivity.class);
                MedList medList = new MedList();
                medList.setMedNameText(String.valueOf(nameMedi.getText()));
                medList.setMedInfoText(String.valueOf(infoMedi.getText()));
                medList.setTimeMed(timeMed);
                medList.setIdUser(Integer.parseInt(userID));

                intent.putExtra("idUser",userID);
                intent.putExtra("nameUser",userName);
                intent.putExtra("passUser",userPass);
                intent.putExtra("mornUser",userMorning);
                intent.putExtra("afterUser",userAfter);
                intent.putExtra("evenUser",userEven);

                MedListDAO medListDAO = new MedListDAO(getApplicationContext());
                medListDAO.open();
                medListDAO.add(medList);
                medListDAO.close();
//                allMed.add(String.valueOf(nameMedi.getText()));
                finish();
                startActivity(intent);
                }


        });

        noAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AllMedActivity.class);
                intent.putExtra("idUser",userID);
                intent.putExtra("nameUser",userName);
                intent.putExtra("passUser",userPass);
                intent.putExtra("mornUser",userMorning);
                intent.putExtra("afterUser",userAfter);
                intent.putExtra("evenUser",userEven);
                startActivity(intent);
            }
        });

    }
}
