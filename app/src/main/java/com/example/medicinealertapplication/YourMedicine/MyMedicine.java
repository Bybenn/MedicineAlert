package com.example.medicinealertapplication.YourMedicine;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.medicinealertapplication.R;

import java.util.ArrayList;

public class MyMedicine extends BaseAdapter {
    private static Activity activity;
    private static LayoutInflater inflater;
    ArrayList<MedList> myMedList;


    public MyMedicine(Activity activity,ArrayList<MedList> myMedList) {
        this.myMedList = myMedList;
        this.activity = activity;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return myMedList.size();
    }

    @Override
    public MedList getItem(int position) {
        return myMedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myMedList.get(position).getIdMed();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        v = inflater.inflate(R.layout.mymed_layout,null);
        TextView nameMedinList = (TextView)v.findViewById(R.id.medName_text);
        MedList nameMedList = myMedList.get(position);
        nameMedinList.setText(nameMedList.getMedNameText());

        TextView infoMedInList = (TextView)v.findViewById(R.id.infoMed_text);
        MedList infoMedList = myMedList.get(position);
        infoMedInList.setText(infoMedList.getMedInfoText());

        return v;
    }
}
