package com.example.medicinealertapplication.User;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.medicinealertapplication.AllMedicine.AllMedActivity;
import com.example.medicinealertapplication.DatabaseHelper;
import com.example.medicinealertapplication.HomeActivity;
import com.example.medicinealertapplication.R;
import com.example.medicinealertapplication.TimePickerFragment;
import com.example.medicinealertapplication.YourMedicine.YourMedicineActivity;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

public class SetTimeToEatActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    DatabaseHelper db;
    int a ;
    String timeText ;
    String timeText2 ;
    String timeText3 ;
    ImageView mTextMorning;
    ImageView mTextAfternoon;
    ImageView mTextEvening;
    TextView textViewMorn;
    TextView textViewAfter;
    TextView textViewEven;
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    Button button_setEatTime;

    FrameLayout frameLayout1;
    FrameLayout frameLayout2;
    FrameLayout frameLayout3;

    public static String morning = null;
    public static String after = null;
    public static String even = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time_to_eat);
        mTextMorning = (ImageView)findViewById(R.id.edittext_morning);
        mTextAfternoon = (ImageView)findViewById(R.id.edittext_afternoon);
        mTextEvening = (ImageView)findViewById(R.id.edittext_evening);
        textViewMorn = (TextView)findViewById(R.id.textViewMorn);
        textViewAfter = (TextView)findViewById(R.id.textViewAfter);
        textViewEven = (TextView)findViewById(R.id.textViewEven);
        button_setEatTime = (Button) findViewById(R.id.button_setEatTime);
        frameLayout1 = (FrameLayout)findViewById(R.id.FrameLayout1) ;
        frameLayout2 = (FrameLayout)findViewById(R.id.FrameLayout2) ;
        frameLayout3 = (FrameLayout)findViewById(R.id.FrameLayout3) ;


        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");

        timeText = userMorning;
        timeText2 = userAfter;
        timeText3 = userEven;


        textViewMorn.setText(userMorning);
        textViewAfter.setText(userAfter);
        textViewEven.setText(userEven);

        frameLayout1.setOnClickListener(new View.OnClickListener() {
            //            Calendar calendar = Calendar.getInstance();
//            int hour = calendar.get(Calendar.HOUR);
//            int minute = calendar.get(Calendar.MINUTE);
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
                a=1;

            }
        });
        frameLayout2.setOnClickListener(new View.OnClickListener() {
            //            Calendar calendar = Calendar.getInstance();
//            int hour = calendar.get(Calendar.HOUR);
//            int minute = calendar.get(Calendar.MINUTE);
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
                a=2;

            }
        });
        frameLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
                a=3;

            }
        });

        button_setEatTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeText.equals("")||timeText2.equals("")||timeText3.equals("")){
                    Toast.makeText(SetTimeToEatActivity.this, "กรุณาตั้งเวลาการทานอาหาร", Toast.LENGTH_SHORT).show();

                }else {

                    User eUser = new User();
                    eUser.setUserID(Integer.parseInt(userID));
                    eUser.setUserName(userName);
                    eUser.setUserPass(userPass);
                    eUser.setUserMorning(timeText);
                    eUser.setUserAfter(timeText2);
                    eUser.setUserEven(timeText3);

//                เพื่ออัพเดท

                    UserDAO userDAO = new UserDAO(getApplicationContext());
                    userDAO.open();
                    userDAO.update(eUser);
                    userDAO.close();
                    Toast.makeText(SetTimeToEatActivity.this, "ตั้งเวลาเรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent(SetTimeToEatActivity.this, HomeActivity.class);
                    mainIntent.putExtra("idUser",userID);
                    mainIntent.putExtra("nameUser",userName);
                    mainIntent.putExtra("passUser",userPass);
                    mainIntent.putExtra("mornUser",userMorning);
                    mainIntent.putExtra("afterUser",userAfter);
                    mainIntent.putExtra("evenUser",userEven);

                    morning = userMorning;
                    after = userAfter;
                    even = userEven;

                    startActivity(mainIntent);
                    finish();

                }
            }
        });


    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        if (a==1){
            updateTimeText1(c);

        }else if (a==2){
            updateTimeText2(c);

        }else if (a==3){
            updateTimeText3(c);

        }
    }

    private void updateTimeText1(Calendar c) {
        timeText = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        textViewMorn.setText(timeText);
        userMorning = timeText;

    }
    private void updateTimeText2(Calendar c) {
        timeText2 = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        textViewAfter.setText(timeText2);
        userAfter = timeText2;

    }
    private void updateTimeText3(Calendar c) {
        timeText3 = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        textViewEven.setText(timeText3);
        userEven = timeText3;

    }

}
