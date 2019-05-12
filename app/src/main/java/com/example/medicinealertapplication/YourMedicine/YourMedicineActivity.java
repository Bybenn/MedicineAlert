package com.example.medicinealertapplication.YourMedicine;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.medicinealertapplication.Alarm.TimeMedActivity;
import com.example.medicinealertapplication.AllMedicine.AllMedActivity;
import com.example.medicinealertapplication.AllMedicine.ViewAllMedActivity;
import com.example.medicinealertapplication.HomeActivity;
import com.example.medicinealertapplication.KnowledgeActivity;
import com.example.medicinealertapplication.R;
import com.example.medicinealertapplication.User.LogoutActivity;
import com.example.medicinealertapplication.User.User;

import java.util.ArrayList;

public class YourMedicineActivity extends AppCompatActivity {
    ListView medListview;
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    Button addMed;
    BottomNavigationView navigationView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_medicine);


        addMed = (Button)findViewById(R.id.addMed);
        medListview = (ListView)findViewById(R.id.todo_listView);
        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");

        MedListDAO medListDAO = new MedListDAO(getApplicationContext());
        medListDAO.open();
        ArrayList<MedList> myList = medListDAO.getAllMedList();
        final MyMedicine adapter = new MyMedicine(this,myList);
        medListview.setAdapter(adapter);
        medListDAO.close();

        medListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
//            คืนค่าเป็นไอดีเพื่อเอาไปแก้ไข
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),userID,
                        Toast.LENGTH_SHORT).show();
                Intent editIntent = new Intent(getApplicationContext(),EditMedicineActivity.class);
                editIntent.putExtra("editMedList",adapter.getItem(position));
                editIntent.putExtra("idUser",userID);
                editIntent.putExtra("nameUser",userName);
                editIntent.putExtra("passUser",userPass);
                editIntent.putExtra("mornUser",userMorning);
                editIntent.putExtra("afterUser",userAfter);
                editIntent.putExtra("evenUser",userEven);

                startActivity(editIntent);
            }
        });

        navigationView = findViewById(R.id.bottom_nav);

        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home){
                    Intent HomePage = new Intent(YourMedicineActivity.this, HomeActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }else if (id == R.id.know) {
                    Intent HomePage = new Intent(YourMedicineActivity.this, KnowledgeActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }else if (id == R.id.timer) {
                    Intent HomePage = new Intent(YourMedicineActivity.this, TimeMedActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }else if (id == R.id.account){
                    Intent HomePage = new Intent(YourMedicineActivity.this, LogoutActivity.class);
                    HomePage.putExtra("idUser",userID);
                    HomePage.putExtra("nameUser",userName);
                    HomePage.putExtra("passUser",userPass);
                    HomePage.putExtra("mornUser",userMorning);
                    HomePage.putExtra("afterUser",userAfter);
                    HomePage.putExtra("evenUser",userEven);
                    startActivity(HomePage);
                }
            }
        });
    }
    public void onResume() {

        super.onResume();
        MedListDAO medListDAO = new MedListDAO(getApplicationContext());
        medListDAO.open();
        ArrayList<MedList> myList = medListDAO.getAllMedList();
        MyMedicine adapter = new MyMedicine(this,myList);
        medListview.setAdapter(adapter);
        medListDAO.close();
    }

    public void getAddMed(View view){
        Intent intent = new Intent(getApplicationContext(), AddMedicineActivity.class);
        intent.putExtra("idUser",userID);
        intent.putExtra("nameUser",userName);
        intent.putExtra("passUser",userPass);
        intent.putExtra("mornUser",userMorning);
        intent.putExtra("afterUser",userAfter);
        intent.putExtra("evenUser",userEven);
        startActivity(intent);

    }
}
