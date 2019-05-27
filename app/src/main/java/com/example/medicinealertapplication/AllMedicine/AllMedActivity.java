package com.example.medicinealertapplication.AllMedicine;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.medicinealertapplication.Alarm.TimeMedActivity;
import com.example.medicinealertapplication.DatabaseHelper;
import com.example.medicinealertapplication.HomeActivity;
import com.example.medicinealertapplication.KnowledgeActivity;
import com.example.medicinealertapplication.R;
import com.example.medicinealertapplication.User.LogoutActivity;
import com.example.medicinealertapplication.YourMedicine.YourMedicineActivity;

import java.util.ArrayList;

public class AllMedActivity extends AppCompatActivity {
    DatabaseHelper db;
    ListView allMed;
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    ArrayList<String> listNameMed;
    ArrayList<String> listInfoMed;
    ArrayList<String> listHowtoMed;
    ArrayList<String> listTimeEat;
    ArrayAdapter adapter;
    BottomNavigationView navigationView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_med);

        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");

        db = new DatabaseHelper(this);
        navigationView = findViewById(R.id.bottom_nav);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home){
                    Intent HomePage = new Intent(AllMedActivity.this, HomeActivity.class);
                    HomePage.putExtra("idUser", userID);
                    HomePage.putExtra("nameUser", userName);
                    HomePage.putExtra("passUser", userPass);
                    HomePage.putExtra("mornUser", userMorning);
                    HomePage.putExtra("afterUser", userAfter);
                    HomePage.putExtra("evenUser", userEven);
                    startActivity(HomePage);
                    return true;
                }else if (id == R.id.warehouse) {
                    Intent allmedIntent = new Intent(AllMedActivity.this, AllMedActivity.class);
                    allmedIntent.putExtra("idUser", userID);
                    allmedIntent.putExtra("nameUser", userName);
                    allmedIntent.putExtra("passUser", userPass);
                    allmedIntent.putExtra("mornUser", userMorning);
                    allmedIntent.putExtra("afterUser", userAfter);
                    allmedIntent.putExtra("evenUser", userEven);
                    startActivity(allmedIntent);
                    return true;
                }else if (id == R.id.ymedicine) {
                    Intent ymedicineIntent = new Intent(AllMedActivity.this, YourMedicineActivity.class);
                    ymedicineIntent.putExtra("idUser", userID);
                    ymedicineIntent.putExtra("nameUser", userName);
                    ymedicineIntent.putExtra("passUser", userPass);
                    ymedicineIntent.putExtra("mornUser", userMorning);
                    ymedicineIntent.putExtra("afterUser", userAfter);
                    ymedicineIntent.putExtra("evenUser", userEven);
                    startActivity(ymedicineIntent);
                    return true;
                }else if (id == R.id.timer) {
                    Intent timemedIntent = new Intent(AllMedActivity.this, TimeMedActivity.class);
                    timemedIntent.putExtra("idUser", userID);
                    timemedIntent.putExtra("nameUser", userName);
                    timemedIntent.putExtra("passUser", userPass);
                    timemedIntent.putExtra("mornUser", userMorning);
                    timemedIntent.putExtra("afterUser", userAfter);
                    timemedIntent.putExtra("evenUser", userEven);
                    startActivity(timemedIntent);
                    return true;
                }else if (id == R.id.account) {
                    Intent logoutIntent = new Intent(AllMedActivity.this, LogoutActivity.class);
                    logoutIntent.putExtra("idUser", userID);
                    logoutIntent.putExtra("nameUser", userName);
                    logoutIntent.putExtra("passUser", userPass);
                    logoutIntent.putExtra("mornUser", userMorning);
                    logoutIntent.putExtra("afterUser", userAfter);
                    logoutIntent.putExtra("evenUser", userEven);
                    startActivity(logoutIntent);
                    return true;
                }
                return false;
            }
        });



        listNameMed = new ArrayList<>();
        listInfoMed = new ArrayList<>();
        listHowtoMed = new ArrayList<>();
        listTimeEat = new ArrayList<>();

        allMed = findViewById(R.id.all_med);
        viewData();

        allMed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nameMed = allMed.getItemAtPosition(position).toString();
                String infoMed = listInfoMed.get(position).toString();
                String howtoMed = listHowtoMed.get(position).toString();
                String timeMed = listTimeEat.get(position).toString();
                String positionIma = String.valueOf(position);


                Toast.makeText(AllMedActivity.this,""+nameMed,Toast.LENGTH_SHORT).show();
                Intent iintent = new Intent(getApplicationContext(),ViewAllMedActivity.class);

                iintent.putExtra("idUser",userID);
                iintent.putExtra("nameUser",userName);
                iintent.putExtra("passUser",userPass);
                iintent.putExtra("mornUser",userMorning);
                iintent.putExtra("afterUser",userAfter);
                iintent.putExtra("evenUser",userEven);
                iintent.putExtra("nameMed",nameMed);
                iintent.putExtra("infoMed",infoMed);
                iintent.putExtra("howtoMed",howtoMed);
                iintent.putExtra("timeMed",timeMed);
                iintent.putExtra("positionIma",positionIma);

                startActivity(iintent);
            }
        });




    }
    private void viewData() {
        Cursor cursor = db.viewData();
        if (cursor.getCount()==0){
            Toast.makeText(this,"ไม่พบข้อมูล", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listNameMed.add(cursor.getString(1));
                listInfoMed.add(cursor.getString(2));
                listHowtoMed.add(cursor.getString(3));
                listTimeEat.add(cursor.getString(4));

            }

            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listNameMed);
            allMed.setAdapter(adapter);

        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> medlist = new ArrayList<>();

                for (String med : listNameMed){
                    if (med.toLowerCase().contains(newText.toLowerCase())){
                        medlist.add(med);
                    }
                }
                ArrayAdapter adapter = new ArrayAdapter<String>(AllMedActivity.this,
                        android.R.layout.simple_list_item_1, medlist);
                allMed.setAdapter(adapter);


                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
