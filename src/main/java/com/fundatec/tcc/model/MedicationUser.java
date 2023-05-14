package com.fundatec.tcc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "medicationUser")
public class MedicationUser {
    @Id
    private String id;
    private String userName;

    private List<Medication> medicationList;

    public MedicationUser(String userName,List<Medication> medicationList){
        this.userName = userName;
        this.medicationList = medicationList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMedicationList(List<Medication> existingList) {
        this.medicationList = existingList;
    }

    public List<Medication> getMedicationList() {
        return medicationList;
    }
}
