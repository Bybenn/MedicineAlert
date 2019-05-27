package com.example.medicinealertapplication.YourMedicine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.medicinealertapplication.DatabaseHelper;
import com.example.medicinealertapplication.User.LoginActivity;

import java.util.ArrayList;

public class MedListDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;


    /*
    ถ้าต้องการเรียกใช้ ID user ที่ล็อคอินอยู่ให้ เรียกดังนี้ LoginACitivity.loginID; จะได้ค่า ID ที่ต้องการออกมา
    และให้ทำการแก้ Edit Del method ไม่ให้สามารถลบยาของคนอื่นออกไปได้
    */


    public MedListDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }



    public ArrayList<MedList> getAllMedList() {
        ArrayList<MedList> medList = new ArrayList<MedList>();
        Cursor cursor = database.rawQuery("SELECT * FROM you_med where userIDMed = ?"
                ,new String[] {LoginActivity.loginID});
        cursor.moveToFirst();
        MedList medList1;
        while (!cursor.isAfterLast()) {
            medList1 = new MedList();
            medList1.setIdMed(cursor.getInt(0));
            medList1.setMedNameText(cursor.getString(1));
            medList1.setMedInfoText(cursor.getString(2));
            medList1.setTimeMed(cursor.getString(3));
            medList1.setIdUser(cursor.getInt(4));

            medList.add(medList1);
            cursor.moveToNext();
        }
        cursor.close();

        return medList;

    }
    public void add(MedList medList) {
        MedList newMedList = new MedList();
        newMedList = medList;

        ContentValues values = new ContentValues();
        values.put("med_name", newMedList.getMedNameText());
        values.put("med_info",newMedList.getMedInfoText());
        values.put("med_time",newMedList.getTimeMed());
        values.put("userIDMed",newMedList.getIdUser());

        //values.put("userID",newMedList.getUser());
        this.database.insert("you_med", null, values);

        Log.d("Todo List Demo :::", "Add OK!!!");

    }

    public void update(MedList medList) {
        MedList updateMedList = medList;
        ContentValues values = new ContentValues();
        values.put("med_name", updateMedList.getMedNameText());
        values.put("med_info",updateMedList.getMedInfoText());
        values.put("med_time",updateMedList.getTimeMed());
        values.put("userIDMed",updateMedList.getIdUser());
        values.put("idYOUMED", updateMedList.getIdMed());
        String where = "idYOUMED=" + updateMedList.getIdMed();

        this.database.update("you_med", values, where, null);
        Log.d("Todo List :::", "Update OK!!!");
    }

    public void delete(MedList medList) {
        MedList delMedList = medList;
        String sqlText = "DELETE FROM you_med WHERE idYOUMED=" + delMedList.getIdMed();
        this.database.execSQL(sqlText);
    }
}
