package com.example.medicinealertapplication.User;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.medicinealertapplication.R;

import java.util.ArrayList;

public class MyUser extends BaseAdapter {
    private static Activity activity;
    private static LayoutInflater inflater;
    ArrayList<User> myUser;


    public MyUser(Activity activity,ArrayList<User> myUser) {
        this.myUser = myUser;
        this.activity = activity;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return myUser.size();
    }

    @Override
    public User getItem(int position) {
        return myUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myUser.get(position).getUserID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        v = inflater.inflate(R.layout.my_user_layout,null);
        TextView textView = (TextView)v.findViewById(R.id.listview_text);
        User todoList = myUser.get(position);
        textView.setText("   "+todoList.getUserName());

//        TextView textView2 = (TextView)v.findViewById(R.id.i);
//        User todoList2 = myUser.get(position);
//        textView2.setText(todoList2.);


        return v;
    }

}