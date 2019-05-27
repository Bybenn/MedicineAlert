package com.example.medicinealertapplication.Alarm;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.medicinealertapplication.R;

import java.util.ArrayList;

public class MyTime extends BaseAdapter {
    private static Activity activity;
    private static LayoutInflater inflater;
    ArrayList<TimeList> myTimeList;


    public MyTime(Activity activity, ArrayList<TimeList> myTimeList) {
        this.myTimeList = myTimeList;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return myTimeList.size();
    }

    @Override
    public TimeList getItem(int position) {
        return myTimeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myTimeList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        v = inflater.inflate(R.layout.mytime_layout, null);
        TextView textView = (TextView) v.findViewById(R.id.time_text);

        TextView textView2 = (TextView) v.findViewById(R.id.i);

        TimeList todoList = myTimeList.get(position);
        textView.setText(todoList.getNameMed());

        TimeList todoList2 = myTimeList.get(position);
        textView2.setText(todoList2.getTime());


        return v;
    }


}
