package com.fundatec.tcc.service.Medicamento;

import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;


    public Medication createMedication(Medication medication) {

        return medicationRepository.save(medication);
    }

    public List<Medication> getAllMedications() {

        return medicationRepository.findAll();
    }

    public Optional<Medication> getMedicationById(String id) {

        return medicationRepository.findById(id);
    }

    public void deleteMedication(String id) {

        medicationRepository.deleteById(id);
    }

    public Medication updateMedication(String id, Medication medication) {
        Optional<Medication> medicationOptional = medicationRepository.findById(id);

        if (!medicationOptional.isPresent()) {
            throw new RuntimeException("Medication not found");
        }

        Medication existingMedication = medicationOptional.get();

        existingMedication.setName(medication.getName());
        existingMedication.setDosage(medication.getDosage());
        existingMedication.setAmount(medication.getAmount());
        existingMedication.setDateRegister(medication.getDateRegister());
        existingMedication.setListTime(medication.getListTime());

        return medicationRepository.save(existingMedication);
    }

    public void addMedicationsToList(String id, List<Medication> listaMedicamentos) {
        Optional<Medication> medicationOptional = medicationRepository.findById(id);

        if (!medicationOptional.isPresent()) {
            throw new RuntimeException("Medication not found");
        }

        Medication existingMedication = medicationOptional.get();
        List<Medication> existingList = existingMedication.getMedicationList();
        existingList.addAll(listaMedicamentos);
        existingMedication.setMedicationList(existingList);

        medicationRepository.save(existingMedication);
    }

    public void removeMedicationFromList(String id, Medication medication) {
        Optional<Medication> medicationOptional = medicationRepository.findById(id);

        if (!medicationOptional.isPresent()) {
            throw new RuntimeException("Medication not found");
        }

        Medication existingMedication = medicationOptional.get();
        List<Medication> existingList = existingMedication.getMedicationList();
        existingList.remove(medication);
        existingMedication.setMedicationList(existingList);

        medicationRepository.save(existingMedication);
    }

    public void updateMedicationInList(String id, Medication oldMedication, Medication newMedication) {
        Optional<Medication> medicationOptional = medicationRepository.findById(id);
        if (!medicationOptional.isPresent()) {
            throw new RuntimeException("Medication not found");
        }

        Medication existingMedication = medicationOptional.get();
        List<Medication> existingList = existingMedication.getMedicationList();

        int index = existingList.indexOf(oldMedication);
        if (index == -1) {
            throw new RuntimeException("Medication not found in list");
        }

        existingList.set(index, newMedication);
        existingMedication.setMedicationList(existingList);

        medicationRepository.save(existingMedication);
    }
}