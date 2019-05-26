package com.example.medicinealertapplication.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinealertapplication.DatabaseHelper;
import com.example.medicinealertapplication.R;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
//    public static String userName = null;
//    public static String passWord = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.textview_login);

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this,UserActivity.class);
                startActivity(LoginIntent);
            }
        });


        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();


                if (user.equals("") || pwd.equals("") || cnf_pwd.equals("")) {
                    Toast.makeText(RegisterActivity.this, "กรุณาใส่ในครบทุกช่อง",
                            Toast.LENGTH_SHORT).show();
                }else if(pwd.equals(cnf_pwd)){
                    User user1 = new User();
                    user1.setUserName(String.valueOf(mTextUsername.getText()));
                    user1.setUserPass(String.valueOf(mTextPassword.getText()));
//                    userName = String.valueOf(mTextUsername.getText());
//                    passWord = String.valueOf(mTextPassword.getText());
                    user1.setUserMorning("07:00");
                    user1.setUserAfter("12:00");
                    user1.setUserEven("18:00");

                    UserDAO userDAO = new UserDAO(getApplicationContext());
                    userDAO.open();
                    userDAO.add(user1);
                    userDAO.close();
                    finish();

                    Toast.makeText(RegisterActivity.this,"บัญชีของคุณถูกสร้างเรียบร้อยแล้ว",Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new Intent(RegisterActivity.this,UserActivity.class);
                    startActivity(moveToLogin);

                }
                else{
                    Toast.makeText(RegisterActivity.this,"พาสเวิร์ดไม่ตรงกัน",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
