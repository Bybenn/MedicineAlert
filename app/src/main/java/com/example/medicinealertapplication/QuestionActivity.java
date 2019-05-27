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
import com.example.medicinealertapplication.YourMedicine.YourMedicineActivity;

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
                Intent alertIntent = new Intent(getApplicationContext(), YourMedicineActivity.class);
                alertIntent.putExtra("idUser", LoginActivity.loginID);
                alertIntent.putExtra("nameUser", LoginActivity.user);
                alertIntent.putExtra("passUser",LoginActivity.pass);
                alertIntent.putExtra("mornUser",userMorning);
                alertIntent.putExtra("afterUser",userAfter);
                alertIntent.putExtra("evenUser",userEven);
                startActivity(alertIntent);

            }
        });

        noAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noalertIntent = new Intent(getApplicationContext(), HomeActivity.class);
                noalertIntent.putExtra("idUser",LoginActivity.loginID);
                noalertIntent.putExtra("nameUser",LoginActivity.user);
                noalertIntent.putExtra("passUser",LoginActivity.pass);
                noalertIntent.putExtra("mornUser",userMorning);
                noalertIntent.putExtra("afterUser",userAfter);
                noalertIntent.putExtra("evenUser",userEven);
                startActivity(noalertIntent);
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

}
