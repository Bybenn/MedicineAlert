package com.example.medicinealertapplication.Alarm;

import java.io.Serializable;

public class TimeList implements Serializable {
    private int id;
    private String nameMed;
    private String note;
    private String time;
    private String info;
    private String timeeat;
    private int userID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameMed() {
        return nameMed;
    }

    public void setNameMed(String nameMed) {
        this.nameMed = nameMed;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTimeeat() {
        return timeeat;
    }

    public void setTimeeat(String timeeat) {
        this.timeeat = timeeat;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}