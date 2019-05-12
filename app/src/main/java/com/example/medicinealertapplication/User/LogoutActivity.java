package com.example.medicinealertapplication.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.medicinealertapplication.AllMedicine.AllMedActivity;
import com.example.medicinealertapplication.R;

public class LogoutActivity extends AppCompatActivity {
    Button logoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        logoutbtn = (Button)findViewById(R.id.logoutbtn);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LogoutActivity.this,"ล้อคเอาท์สำเร็จ",Toast.LENGTH_SHORT).show();

                Intent LogoutIntent = new Intent(LogoutActivity.this,RegisterActivity.class);
                startActivity(LogoutIntent);
            }
        });
    }
}
