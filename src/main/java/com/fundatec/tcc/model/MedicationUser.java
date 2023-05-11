package com.fundatec.tcc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
//Medication não estende mais a classe ListMedication, mas a classe ListMedication contém uma lista de medicamentos.
// Dessa forma, cada instância de ListMedication pode conter uma lista de Medication.
@Document(collection = "medicationUser")
public class MedicationUser {
    @Id
    private String id;
    private String userName;

    private List<String> medications;

    // construtor, getters e setters aqui

    public MedicationUser(String userName, List<String> medications){
        this.userName = userName;
        this.medications = medications;
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

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getMedications() {
        return medications;
    }
}
