package com.example.medicinealertapplication.User;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.medicinealertapplication.HomeActivity;
import com.example.medicinealertapplication.R;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    ListView todoListView;
    TextView registerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        todoListView = (ListView) findViewById(R.id.todo_listView);
        registerText = (TextView)findViewById(R.id.registerText);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(UserActivity.this, RegisterActivity.class);
                startActivity(mainIntent);

            }
        });

        UserDAO userDAO = new UserDAO(getApplicationContext());
        userDAO.open();
        ArrayList<User> myList = userDAO.getAllUser();
        final MyUser adapter = new MyUser(this, myList);


        todoListView.setAdapter(adapter);
        userDAO.close();

//        ให้กดใน listView ได้

        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
//            คืนค่าเป็นไอดีเพื่อเอาไปแก้ไข
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editIntent = new Intent(getApplicationContext(), LoginActivity.class);
                editIntent.putExtra("loginUser", adapter.getItem(position));
                editIntent.putExtra("userID",id);
                startActivity(editIntent);
            }
        });

    }

    public void onResume() {

        super.onResume();
        UserDAO userDAO = new UserDAO(getApplicationContext());
        userDAO.open();
        ArrayList<User> myList = userDAO.getAllUser();
        MyUser adapter = new MyUser(this,myList);
        todoListView.setAdapter(adapter);
        userDAO.close();
    }


}
