package com.example.medicinealertapplication.YourMedicine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicinealertapplication.Alarm.SetAlarmActivity;
import com.example.medicinealertapplication.R;

public class EditMedicineActivity extends AppCompatActivity {
    TextView medNameView;
    TextView infoMedView;
    Button getAlertbutton;
    Button deleteButton;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medicine);
        final MedList editMedList = (MedList) getIntent().getSerializableExtra("editMedList");

        medNameView = (TextView) findViewById(R.id.medNameView);
        infoMedView = (TextView) findViewById(R.id.infoMedView);
        getAlertbutton = (Button)findViewById(R.id.getAlertbutton);
        deleteButton = (Button)findViewById(R.id.deleteButton);

        medNameView.setText(editMedList.getMedNameText());
        infoMedView.setText(editMedList.getMedInfoText());
        Intent intent = getIntent();
        userID = intent.getStringExtra("idUser");

        getAlertbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),userID,
                        Toast.LENGTH_SHORT).show();
                String name = String.valueOf(medNameView.getText());
                Intent intent = new Intent(getApplicationContext(), SetAlarmActivity.class);
                intent.putExtra("nameMed",name);
                intent.putExtra("idUser",userID);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedListDAO medListDAODel = new MedListDAO(getApplicationContext());
                medListDAODel.open();
                medListDAODel.delete(editMedList);
                medListDAODel.close();
                finish();
            }
        });


    }
}
