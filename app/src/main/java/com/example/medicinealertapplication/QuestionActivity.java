package com.example.medicinealertapplication;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicinealertapplication.Alarm.EditTimeActivity;
import com.example.medicinealertapplication.Alarm.TimeMedActivity;
import com.example.medicinealertapplication.User.LoginActivity;
import com.example.medicinealertapplication.User.RegisterActivity;
import com.example.medicinealertapplication.User.SetTimeToEatActivity;

public class QuestionActivity extends AppCompatActivity {
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");


        AlertDialog.Builder mBuilder = new AlertDialog.Builder(QuestionActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_question,null);
        Button getAlert = (Button) mView.findViewById(R.id.getAlert);
        Button noAlert = (Button) mView.findViewById(R.id.noAlert);
        getAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(getApplicationContext(), TimeMedActivity.class);
                editIntent.putExtra("idUser", LoginActivity.loginID);
                editIntent.putExtra("nameUser", LoginActivity.user);
                editIntent.putExtra("passUser",LoginActivity.pass);
                editIntent.putExtra("mornUser",userMorning);
                editIntent.putExtra("afterUser",userAfter);
                editIntent.putExtra("evenUser",userEven);
                startActivity(editIntent);

            }
        });

        noAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(getApplicationContext(), HomeActivity.class);
                editIntent.putExtra("idUser",LoginActivity.loginID);
                editIntent.putExtra("nameUser",LoginActivity.user);
                editIntent.putExtra("passUser",LoginActivity.pass);
                editIntent.putExtra("mornUser",userMorning);
                editIntent.putExtra("afterUser",userAfter);
                editIntent.putExtra("evenUser",userEven);
                startActivity(editIntent);
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

}
