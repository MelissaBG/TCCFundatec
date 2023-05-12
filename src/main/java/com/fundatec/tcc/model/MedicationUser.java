package com.fundatec.tcc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document(collection = "medicationUser")
public class MedicationUser {
    @Id
    private String id;
    private String userName;

    private List<Medication> medicationList = new ArrayList<>();

    // construtor, getters e setters aqui

    public MedicationUser(String userName, List<Medication> medicationList){
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

    public void setMedicationList(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }

    public List<Medication> getMedicationList() {
        return medicationList;
    }
}
