package com.example.medicinealertapplication.YourMedicine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.medicinealertapplication.DatabaseHelper;
import com.example.medicinealertapplication.User.LoginActivity;
import com.example.medicinealertapplication.User.User;
import com.example.medicinealertapplication.User.UserDAO;

import java.util.ArrayList;

public class MedListDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
//    User user ;
//    MedList medList2;

    /*
    ถ้าต้องการเรียกใช้ ID user ที่ล็อคอินอยู่ให้ เรียกดังนี้ LoginACitivity.loginID; จะได้ค่า ID ที่ต้องการออกมา
    และให้ทำการแก้ Edit Del methid ไม่ให้สามารถลบยาของคนอื่นออกไปได้
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


/*    ตรงนี้อยากแก้ไขเป็นอ่านค่าเฉพาะของ user ที่ login เข้ามา แต่ลอง user.getUserID(); แล้วไม่มีค่าอะไรออกมาแต่ในหน้า
    แอคทิวิตี้อื่นๆเรียกใช้แล้วค่าขึ้นตามปกติ */
    public ArrayList<MedList> getAllMedList() {
        ArrayList<MedList> medList = new ArrayList<MedList>();
        //Cursor cursor = database.rawQuery("SELECT * FROM you_med ",null);
        Cursor cursor = database.rawQuery("SELECT * FROM you_med where userIDMed = ?"
                ,new String[] {LoginActivity.loginID});
//        Log.i("kong",cursor.getCount()+"");
        cursor.moveToFirst();
        MedList medList1;
//        Log.d("ii", "ID"+medList2.getIdUser());

        while (!cursor.isAfterLast()) {
//            ให้ข้อมูล เซ็ทอัพเดทเป็นปัจจุบัน
            medList1 = new MedList();
            medList1.setIdMed(cursor.getInt(0));
            medList1.setMedNameText(cursor.getString(1));
            medList1.setMedInfoText(cursor.getString(2));
            medList1.setIdUser(cursor.getInt(3));

            medList.add(medList1);
            cursor.moveToNext();
        }
        cursor.close();
        return medList;

    }
    //  อันนี้ไม่แก้
    public void add(MedList medList) {
        MedList newMedList = new MedList();
        newMedList = medList;

        ContentValues values = new ContentValues();
        values.put("med_name", newMedList.getMedNameText());
        values.put("med_info",newMedList.getMedInfoText());
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
