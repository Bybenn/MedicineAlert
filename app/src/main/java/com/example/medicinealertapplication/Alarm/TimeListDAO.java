package com.example.medicinealertapplication.Alarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.medicinealertapplication.DatabaseHelper;
import com.example.medicinealertapplication.User.LoginActivity;

import java.util.ArrayList;

public class TimeListDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public TimeListDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
//       เปิดเพื่อแก้ไข
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /*    ตรงนี้อยากแก้ไขเป็นอ่านค่าเฉพาะของ user ที่ login เข้ามา แต่ลอง user.getUserID(); แล้วไม่มีค่าอะไรออกมาแต่ในหน้า
    แอคทิวิตี้อื่นๆเรียกใช้แล้วค่าขึ้นตามปกติ */
    public ArrayList<TimeList> getAllList() {
        ArrayList<TimeList> timeList = new ArrayList<TimeList>();
        // เหมือนยา ต้องมี where user_id ด้วย
        Cursor cursor = database.rawQuery("SELECT * FROM timer_med where userIDTime = ?"
                ,new String[] {LoginActivity.loginID});
        cursor.moveToFirst();
        TimeList timeList1;
        while (!cursor.isAfterLast()) {
            timeList1 = new TimeList();
            timeList1.setId(cursor.getInt(0));
            timeList1.setNameMed(cursor.getString(1));
            timeList1.setNote(cursor.getString(2));
            timeList1.setTime(cursor.getString(3));
            timeList1.setUserID(cursor.getInt(4));

            timeList.add(timeList1);
            cursor.moveToNext();
        }
        cursor.close();
        return timeList;
    }

    public void add(TimeList timeList) {
        TimeList newTimeList = new TimeList();
        newTimeList = timeList;

        ContentValues values = new ContentValues();
        values.put("timemed_name", newTimeList.getNameMed());
        values.put("timenote_med",newTimeList.getNote());
        values.put("time",newTimeList.getTime());
        values.put("userIDTime",newTimeList.getUserID());

        this.database.insert("timer_med", null, values);

        Log.d("Todo List Demo :::", "Add OK!!!");

    }

    public void update(TimeList medList) {
        TimeList updateTimeList = medList;
        ContentValues values = new ContentValues();
        values.put("timemed_name", updateTimeList.getNameMed());
        values.put("timenote_med",updateTimeList.getNote());
        values.put("time",updateTimeList.getTime());
        values.put("userIDTime",updateTimeList.getUserID());
        values.put("idTIMERMED", updateTimeList.getId());
        String where = "idTIMERMED=" + updateTimeList.getId();

        this.database.update("timer_med", values, where, null);
        Log.d("Todo List :::", "Update OK!!!");
    }

    public void delete(TimeList timeList) {
        TimeList delTimeList = timeList;
        String sqlText = "DELETE FROM timer_med WHERE idTIMERMED=" + delTimeList.getId();
        this.database.execSQL(sqlText);
    }

}
