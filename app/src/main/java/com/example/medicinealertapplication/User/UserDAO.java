package com.example.medicinealertapplication.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.medicinealertapplication.DatabaseHelper;

import java.util.ArrayList;

public class UserDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public UserDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> users = new ArrayList<User>();
        Cursor cursor = database.rawQuery("SELECT * FROM users  ",null);

        cursor.moveToFirst();
        User user;

        while (!cursor.isAfterLast()) {
//            ให้ข้อมูล เซ็ทอัพเดทเป็นปัจจุบัน
            user = new User();
            user.setUserID(cursor.getInt(0));
            user.setUserName(cursor.getString(1));
            user.setUserPass(cursor.getString(2));
            user.setUserMorning(cursor.getString(3));
            user.setUserAfter(cursor.getString(4));
            user.setUserEven(cursor.getString(5));


            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }


    public void add(User user) {
        User newUser = new User();
        newUser = user;

        ContentValues values = new ContentValues();
        values.put("username", newUser.getUserName());
        values.put("password",newUser.getUserPass());
        values.put("morning", newUser.getUserMorning());
        values.put("afternoon", newUser.getUserAfter());
        values.put("evening", newUser.getUserEven());

        //values.put("userID",newMedList.getUser());
        this.database.insert("users", null, values);

        Log.d("Todo List Demo :::", "Add OK!!!");

    }

    public void update(User user) {
        User updateUser = user;
        ContentValues values = new ContentValues();
        values.put("username", updateUser.getUserName());
        values.put("password",updateUser.getUserPass());
        values.put("morning", updateUser.getUserMorning());
        values.put("afternoon", updateUser.getUserAfter());
        values.put("evening", updateUser.getUserEven());
        values.put("idUSER", updateUser.getUserID());
        String where = "idUSER=" + updateUser.getUserID();

        this.database.update("users", values, where, null);
        Log.d("Todo List :::", "Update OK!!!");
    }


}
