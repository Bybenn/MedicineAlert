package com.example.medicinealertapplication.User;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinealertapplication.DatabaseHelper;
import com.example.medicinealertapplication.HomeActivity;
import com.example.medicinealertapplication.R;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    ListView todoListView;
    TextView registerText;
    DatabaseHelper db;
    ArrayList<String> listMorning;
    ArrayList<String> listAfter;
    ArrayList<String> listEven;
    ArrayList<String> listID;
    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        listMorning = new ArrayList<>();
        listAfter = new ArrayList<>();
        listEven = new ArrayList<>();
        listID = new ArrayList<>();


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
//        viewUsers();

        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
//            คืนค่าเป็นไอดีเพื่อเอาไปแก้ไข
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editIntent = new Intent(getApplicationContext(), LoginActivity.class);
//                String timeMorn = listMorning.get(position).toString();
//                String timeAfter = listAfter.get(position).toString();
//                String timeEven = listEven.get(position).toString();

//                editIntent.putExtra("mornUser",timeMorn);
//                editIntent.putExtra("afterUser",timeAfter);
//                editIntent.putExtra("evenUser",timeEven);

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

//    private void viewUsers() {
//        Cursor cursor = db.viewUsers();
//        if (cursor.getCount()==0){
//            Toast.makeText(this,"No data to show", Toast.LENGTH_SHORT).show();
//        }else {
//            while (cursor.moveToNext()){
//                listID.add(cursor.getString(1));
////                listMorning.add(cursor.getString(3));
////                listAfter.add(cursor.getString(4));
////                listEven.add(cursor.getString(5));
//
//            }
//
//            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listID);
//            todoListView.setAdapter(adapter);
//
//        }
//    }


}
