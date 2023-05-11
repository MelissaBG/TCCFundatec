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
    private List<String> medicationNameList;

    public MedicationUser(String userName, List<String> medicationNameList) {
        this.userName = userName;
        this.medicationNameList = medicationNameList;
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

    public List<String> getMedicationNameList() {
        return medicationNameList;
    }

    public void setMedicationNameList(List<String> medicationNameList) {
        this.medicationNameList = medicationNameList;
    }
}
