package com.fundatec.tcc.service.Medicamento;

import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> getAllMedication() {
        return medicationRepository.findAll();
    }

    public Medication getMedicationById(String id) {
        return medicationRepository.findById(id).orElse(null);
    }

    public Medication saveMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    public Medication updateMedication(String id, Medication medication) {
        if (medicationRepository.existsById(id)) {
            Medication existingMedication = medicationRepository.findById(id).orElse(null);
            if (existingMedication != null) {
                existingMedication.setName(medication.getName());
                existingMedication.setDosage(medication.getDosage());
                existingMedication.setAmount(medication.getAmount());
                existingMedication.setDateRegister(medication.getDateRegister());
                existingMedication.setTime(medication.getTime());
                return medicationRepository.save(existingMedication);
            }
        }
        return null;
    }

    public void deleteMedicationById(String id) {
        medicationRepository.deleteById(id);
    }

    public Medication findByName(String name) {
        return medicationRepository.findByName(name);
    }

    public Optional<Medication> findById(String id) {
        return medicationRepository.findById(id);
    }
}

