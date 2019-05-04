package com.example.medicinealertapplication.YourMedicine;

import java.io.Serializable;

public class MedList implements Serializable {
    private int idMed;
    private String medNameText;
    private String medInfoText;
    private int idUser;

    public int getIdMed() {
        return idMed;
    }

    public void setIdMed(int idMed) {
        this.idMed = idMed;
    }

    public String getMedNameText() {
        return medNameText;
    }

    public void setMedNameText(String medNameText) {
        this.medNameText = medNameText;
    }

    public String getMedInfoText() {
        return medInfoText;
    }

    public void setMedInfoText(String medInfoText) {
        this.medInfoText = medInfoText;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
