package com.fundatec.tcc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
//Medication não estende mais a classe ListMedication, mas a classe ListMedication contém uma lista de medicamentos.
// Dessa forma, cada instância de ListMedication pode conter uma lista de Medication.
@Document(collection = "medication")
public class MedicationUser {
    @Id
    private String medicationUserId;
    private User user;
    private List<Medication> medicationList;

    // construtor, getters e setters aqui


    public String getMedicationUserId() {
        return medicationUserId;
    }

    public void setMedicationUserId(String medicationUserId) {
        this.medicationUserId = medicationUserId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMedicationList(List<Medication> existingList) {
        this.medicationList = medicationList;
    }

    public List<Medication> getMedicationList() {
        return medicationList;
    }
}
