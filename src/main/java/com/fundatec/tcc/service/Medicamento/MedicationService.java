package com.fundatec.tcc.service.Medicamento;

import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    public Medication findByName(String name) {
        return medicationRepository.findByName(name);
    }

    public Medication saveMedication(Medication medication) {
        medication.setName(medication.getName().toLowerCase());
        return medicationRepository.save(medication);
    }

    public Medication getMedicationById(String id) {
        return medicationRepository.findById(id).orElse(null);
    }

    public void deleteMedicationById(String id) {
        medicationRepository.deleteById(id);
    }
}


