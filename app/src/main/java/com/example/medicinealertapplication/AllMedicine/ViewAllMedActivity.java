package com.example.medicinealertapplication.AllMedicine;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicinealertapplication.R;
import com.example.medicinealertapplication.YourMedicine.MedList;
import com.example.medicinealertapplication.YourMedicine.MedListDAO;
import com.example.medicinealertapplication.YourMedicine.YourMedicineActivity;

import java.time.LocalTime;
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

    Button getAlertButton;
    Button noAlertButton;
    TextView nameMedi;
    TextView infoMedi;
    TextView useMedi;
    TextView suggestTime;
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
        suggestTime = (TextView)findViewById(R.id.suggestTime);

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
        positionIma = intent.getStringExtra("positionIma");


        nameMedi.setText(nameMed);
        infoMedi.setText(infoMed);
        useMedi.setText(howtoMed);
        imageMed.setImageResource(imgid[Integer.parseInt(positionIma)]);
        getTime();



        getAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), YourMedicineActivity.class);
                MedList medList = new MedList();
                medList.setMedNameText(String.valueOf(nameMedi.getText()));
                medList.setMedInfoText(String.valueOf(infoMedi.getText()));
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getTime(){
        if (timeMed.equals("01")){
            int bmor = Integer.parseInt(userMorning.substring(0,2));
            int cmor = Integer.parseInt(userMorning.substring(3));
            int dmor = bmor*60*60;
            int emor = cmor*60;
            int fmor = dmor+emor;
            int gmor = 30*60;
            int hmor = fmor-gmor;
            int hrmor =hmor/3600;
            int minutemor =(hmor%3600)/60;


            int bafter = Integer.parseInt(userAfter.substring(0,2));
            int cafter = Integer.parseInt(userAfter.substring(3));
            int dafter = bafter*60*60;
            int eafter = cafter*60;
            int fafter = dafter+eafter;
            int gafter = 30*60;
            int hafter = fafter-gafter;
            int hrafter =hafter/3600;
            int minuteafter =(hafter%3600)/60;


            int beve = Integer.parseInt(userEven.substring(0,2));
            int ceve = Integer.parseInt(userEven.substring(3));
            int deve = beve*60*60;
            int eeve = ceve*60;
            int feve = deve+eeve;
            int geve = 30*60;
            int heve = feve-geve;
            int hreve =heve/3600;
            int minuteeve =(heve%3600)/60;




            suggestTime.setText("มื่อเช้าที่ควรทาน "+hrmor+" นาฬิกา "+minutemor+" นาที\n"+
                    "มื้อกลางวันที่ควรทาน "+hrafter+" นาฬิกา "+minuteafter+" นาที\n"+
                    "มื้อเย็นที่ควรทาน "+hreve+" นาฬิกา "+minuteeve+" นาที");
        }else if (timeMed.equals("02")){
            int bmor = Integer.parseInt(userMorning.substring(0,2));
            int cmor = Integer.parseInt(userMorning.substring(3));
            int dmor = bmor*60*60;
            int emor = cmor*60;
            int fmor = dmor+emor;
            int gmor = 30*60;
            int hmor = fmor+gmor;
            int hrmor =(hmor/3600)%24;
            int minutemor =(hmor%3600)/60;


            int bafter = Integer.parseInt(userAfter.substring(0,2));
            int cafter = Integer.parseInt(userAfter.substring(3));
            int dafter = bafter*60*60;
            int eafter = cafter*60;
            int fafter = dafter+eafter;
            int gafter = 30*60;
            int hafter = fafter+gafter;
            int hrafter =(hafter/3600)%24;
            int minuteafter =(hafter%3600)/60;


            int beve = Integer.parseInt(userEven.substring(0,2));
            int ceve = Integer.parseInt(userEven.substring(3));
            int deve = beve*60*60;
            int eeve = ceve*60;
            int feve = deve+eeve;
            int geve = 30*60;
            int heve = feve+geve;
            int hreve =(heve/3600)%24;
            int minuteeve =(heve%3600)/60;


            suggestTime.setText("มื้อเช้าที่ควรทาน "+hrmor+" นาฬิกา "+minutemor+" นาที\n"+
                    "มื้อกลางวันที่ควรทาน "+hrafter+" นาฬิกา "+minuteafter+" นาที\n"+
                    "มื้อเย็นที่ควรทาน "+hreve+" นาฬิกา "+minuteeve+" นาที");
        }else if (timeMed.equals("03")){
            int b = Integer.parseInt(userEven.substring(0,2));
            int c = Integer.parseInt(userEven.substring(3));
            int d = b*60*60;
            int e = c*60;
            int f = d+e;
            int g = 120*60;
            int h = f+g;
            int hr =h/3600;
            int minute =(h%3600)/60;
            suggestTime.setText("ควรทานก่อนนอนเวลา "+hr+" นาฬิกา "+minute+" นาที");
        }else if (timeMed.equals("04")){
//          4hr
            Date date = new Date();
            String time = date.toString();

            int b = Integer.parseInt(time.substring(11,13));
            int c = Integer.parseInt(time.substring(14,16));
            int d = b*60*60;
            int e = c*60;
            int f = d+e;
            int g = 4*60*60;
            int h = f+g;
            int i = f+(g*2);
            int hr2 =(h/3600)%24;
            int minute2 =(h%3600)/60;
            int hr3 =(i/3600)%24;
            int minute3 =(h%3600)/60;
            suggestTime.setText("ครั้งแรกที่ควรทาน "+b+" นาฬิกา"+c+" นาที\n"+
                    "ครั้งถัดไป "+hr2+" นาฬิกา "+minute2+" นาที\n"+
                    "และครั้งถัดไป "+hr3+" นาฬิกา "+minute3+" นาที");
        }else if (timeMed.equals("05")){
//            6hr
            Date date = new Date();
            String time = date.toString();

            int b = Integer.parseInt(time.substring(11,13));
            int c = Integer.parseInt(time.substring(14,16));
            int d = b*60*60;
            int e = c*60;
            int f = d+e;
            int g = 6*60*60;
            int h = f+g;
            int i = f+(g*2);
            int hr2 =(h/3600)%24;
            int minute2 =(h%3600)/60;
            int hr3 =(i/3600)%24;
            int minute3 =(h%3600)/60;
            suggestTime.setText("ครั้งแรกที่ควรทาน "+b+" นาฬิกา"+c+" นาที\n"+
                    "ครั้งถัดไป "+hr2+" นาฬิกา "+minute2+" นาที\n"+
                    "และครั้งถัดไป "+hr3+" นาฬิกา "+minute3+" นาที");
        }else if (timeMed.equals("06")){
//            12hr
            Date date = new Date();
            String time = date.toString();

            int b = Integer.parseInt(time.substring(11,13));
            int c = Integer.parseInt(time.substring(14,16));
            int d = b*60*60;
            int e = c*60;
            int f = d+e;
            int g = 12*60*60;
            int h = f+g;
            int i = f+(g*2);
            int hr2 =(h/3600)%24;
            int minute2 =(h%3600)/60;
            int hr3 =(i/3600)%24;
            int minute3 =(h%3600)/60;
            suggestTime.setText("ครั้งแรกที่ควรทาน "+b+" นาฬิกา"+c+" นาที\n"+
                    "ครั้งถัดไป "+hr2+" นาฬิกา "+minute2+" นาที\n"+
                    "และครั้งถัดไป "+hr3+" นาฬิกา "+minute3+" นาที");
        }
    }
}
