package com.example.medicinealertapplication.YourMedicine;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.medicinealertapplication.R;
import com.example.medicinealertapplication.User.LoginActivity;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddMedicineActivity extends AppCompatActivity {
    public static final int REQUEST_GALLERY = 1;
    Bitmap bitmap;
    ImageView imageUpMed;
    String uriPhoto;
    EditText addMedText;
    EditText addInfoText;
    EditText addType;
    Button addMed;
    String[] listSuggestTime;

    String userID;
    String userName;
    String userPass;
    String userMorning;
    String userAfter;
    String userEven;
    String nameMed;
    String infoMed;
    String howtoMed;
    String timeMed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        addMedText = (EditText) findViewById(R.id.addnew_editText);
        addInfoText = (EditText) findViewById(R.id.noteText);
        addType = (EditText) findViewById(R.id.addType);


        Intent intent = getIntent();
        userID = (intent.getStringExtra("idUser"));
        userName = intent.getStringExtra("nameUser");
        userPass = intent.getStringExtra("passUser");
        userMorning = intent.getStringExtra("mornUser");
        userAfter = intent.getStringExtra("afterUser");
        userEven = intent.getStringExtra("evenUser");
        nameMed = intent.getStringExtra("nameMed");
        infoMed = intent.getStringExtra("infoMed");
        howtoMed = intent.getStringExtra("howtoMed");
        timeMed = intent.getStringExtra("timeMed");

        addType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSuggestTime = new String[]{"01 : ก่อนอาหาร","02 : หลังอาหาร","03 : ก่อนนอน","04 : ทุก 4 ชั่วโมง","05 : ทุก 6 ชั่วโมง","06 : ทุก 12 ชั่วโมง"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddMedicineActivity.this);
                mBuilder.setTitle("เลือกประเภทของยา");
                mBuilder.setIcon(R.drawable.ic_android_list);
                mBuilder.setSingleChoiceItems(listSuggestTime, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addType.setText(listSuggestTime[i].substring(0,2));
                        dialogInterface.dismiss();

                    }
                });
                mBuilder.setNeutralButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        addMed = (Button) findViewById(R.id.addnew_button);
        addMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addMedText.getText().toString().isEmpty()||addInfoText.getText().toString().isEmpty()){
                    Toast.makeText(AddMedicineActivity.this,"กรุณากรอกข้อมูลยา",Toast.LENGTH_SHORT).show();

                }else {
                    MedList medList = new MedList();
                    medList.setMedNameText(String.valueOf(addMedText.getText()));
                    medList.setMedInfoText(String.valueOf(addInfoText.getText()));
                    medList.setTimeMed(String.valueOf(addType.getText()));
                    medList.setIdUser(Integer.parseInt(LoginActivity.loginID));


                    MedListDAO medListDAO = new MedListDAO(getApplicationContext());
                    medListDAO.open();
                    medListDAO.add(medList);
                    medListDAO.close();

                    Intent intent = new Intent(getApplicationContext(), YourMedicineActivity.class);
                    intent.putExtra("nameMed",nameMed);
                    intent.putExtra("idUser",userID);
                    intent.putExtra("nameUser",userName);
                    intent.putExtra("passUser",userPass);
                    intent.putExtra("mornUser",userMorning);
                    intent.putExtra("afterUser",userAfter);
                    intent.putExtra("evenUser",userEven);
                    finish();
                    startActivity(intent);
                }

            }
        });

        imageUpMed = (ImageView)findViewById(R.id.imageUpMed);
        imageUpMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(AddMedicineActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AddMedicineActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},200);
                    return;
                }

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent
                        , "Select Picture"), REQUEST_GALLERY);

            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            uriPhoto = uri.toString();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                imageUpMed.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
