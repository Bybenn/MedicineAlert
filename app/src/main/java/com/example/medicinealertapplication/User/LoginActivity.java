package com.example.medicinealertapplication.User;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicinealertapplication.R;

public class LoginActivity extends AppCompatActivity {
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    String passwordCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final User editUser = (User) getIntent().getSerializableExtra("loginUser");
        passwordCheck = editUser.getUserPass();

        userID = String.valueOf(editUser.getUserID());
        userName = editUser.getUserName();
        userPass = editUser.getUserPass();
        userMorning = editUser.getUserMorning();
        userAfter = editUser.getUserAfter();
        userEven = editUser.getUserEven();


        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_login,null);
        final EditText editText = (EditText)mView.findViewById(R.id.editText);
        Button button = (Button) mView.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals(passwordCheck)){

                    Intent editIntent = new Intent(getApplicationContext(), SetTimeToEatActivity.class);
                    editUser.setUserID(Integer.parseInt(userID));

                    editIntent.putExtra("idUser",userID);
                    editIntent.putExtra("nameUser",userName);
                    editIntent.putExtra("passUser",userPass);
                    editIntent.putExtra("mornUser",userMorning);
                    editIntent.putExtra("afterUser",userAfter);
                    editIntent.putExtra("evenUser",userEven);

                    startActivity(editIntent);

                    Toast.makeText(LoginActivity.this,"ล็อคอินสำเร็จแล้ว",Toast.LENGTH_SHORT).show();
                }else if (editText.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this,"กรุณาใส่พาสเวิด",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this,"พาสเวิดไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

}
