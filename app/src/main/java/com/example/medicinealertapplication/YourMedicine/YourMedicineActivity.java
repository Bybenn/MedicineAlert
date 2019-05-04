package com.example.medicinealertapplication.YourMedicine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.medicinealertapplication.AllMedicine.ViewAllMedActivity;
import com.example.medicinealertapplication.R;

import java.util.ArrayList;

public class YourMedicineActivity extends AppCompatActivity {
    ListView medListview;
    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_medicine);

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
//                Toast.makeText(getApplicationContext(),String.valueOf(adapter.getItemId(position)),
//                        Toast.LENGTH_SHORT).show();
                Intent editIntent = new Intent(getApplicationContext(),EditMedicineActivity.class);
                editIntent.putExtra("editMedList",adapter.getItem(position));
                editIntent.putExtra("idUser",userID);

                startActivity(editIntent);
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

    public void clickImageButton(View view){
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
