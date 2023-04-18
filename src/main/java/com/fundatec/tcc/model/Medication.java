package com.fundatec.tcc.model;

import com.mongodb.internal.connection.Time;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Medication {
    private String id;
    private String name;
    private String dosage;
    private Integer amount;
    private LocalDate dateRegister;
    private List<Time> listTime;
    private List<Medication> medicationList;

    // construtor, getters e setters aqui

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }

    public List<Time> getListTime() {
        return listTime;
    }

    public void setListTime(List<Time> listTime) {
        this.listTime = listTime;
    }

    public List<Medication> getMedicationList() {
        return medicationList;
    }

    public void setMedicationList(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }
}

