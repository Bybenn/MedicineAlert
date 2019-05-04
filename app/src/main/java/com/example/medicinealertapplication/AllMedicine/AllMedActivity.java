package com.example.medicinealertapplication.AllMedicine;

import android.content.Intent;
import android.database.Cursor;
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

import com.example.medicinealertapplication.DatabaseHelper;
import com.example.medicinealertapplication.R;

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
            Toast.makeText(this,"No data to show", Toast.LENGTH_SHORT).show();
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
