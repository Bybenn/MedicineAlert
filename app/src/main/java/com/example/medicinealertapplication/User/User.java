package com.example.medicinealertapplication.User;

import java.io.Serializable;

public class User implements Serializable {
    private int userID;
    private String userName;
    private String userPass;
    private String userMorning;
    private String userAfter;
    private String userEven;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserMorning() {
        return userMorning;
    }

    public void setUserMorning(String userMorning) {
        this.userMorning = userMorning;
    }

    public String getUserAfter() {
        return userAfter;
    }

    public void setUserAfter(String userAfter) {
        this.userAfter = userAfter;
    }

    public String getUserEven() {
        return userEven;
    }

    public void setUserEven(String userEven) {
        this.userEven = userEven;
    }
}
