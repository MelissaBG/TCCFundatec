package com.fundatec.tcc.service.Medicamento;

import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public Medication createMedication(Medication medication) {

        return medicationRepository.save(medication);
    }

    public List<Medication> getAllMedications() {

        return medicationRepository.findAll();
    }
    //busca uma medicação específica pelo seu ID,
    // caso seja necessário fazer alguma operação específica com essa medicação.
   public Optional<Medication> getMedicationById(String id) {

        return medicationRepository.findById(id);
    }

    public void deleteMedication(String id) {

        medicationRepository.deleteById(id);
    }

//    public Medication updateMedication(String id, Medication medication) {
//        Optional<Medication> medicationOptional = medicationRepository.findById(id);

//        if (!medicationOptional.isPresent()) {
//            throw new RuntimeException("Medication not found");
//        }

//        Medication existingMedication = medicationOptional.get();

//        existingMedication.setName(medication.getName());
//        existingMedication.setDosage(medication.getDosage());
//        existingMedication.setAmount(medication.getAmount());
//        existingMedication.setDateRegister(medication.getDateRegister());
//        existingMedication.setListTime(medication.getListTime());

//        return medicationRepository.save(existingMedication);
//    }

    public Medication updateMedication(String id, Medication medication) {
        Medication existingMedication = findMedicationById(id);
        updateExistingMedication(existingMedication, medication);
        return medicationRepository.save(existingMedication);
    }
    //o método findMedicationById é responsável por buscar uma medicação pelo seu id
    // no repositório e lançar uma exceção caso a medicação não seja encontrada.
    private Medication findMedicationById(String id) {
        Optional<Medication> medicationOptional = medicationRepository.findById(id);

        if (!medicationOptional.isPresent()) {
            throw new RuntimeException("Medication not found");
        }

        return medicationOptional.get();
    }

    private void updateExistingMedication(Medication existingMedication, Medication medication) {
        existingMedication.setName(medication.getName());
        existingMedication.setDosage(medication.getDosage());
        existingMedication.setAmount(medication.getAmount());
        existingMedication.setDateRegister(medication.getDateRegister());
        existingMedication.setListTime(medication.getListTime());
    }
//    public void addMedicationsToList(String id, List<Medication> listaMedicamentos) {
//        Optional<Medication> medicationOptional = medicationRepository.findById(id);

//        if (!medicationOptional.isPresent()) {
//            throw new RuntimeException("Medication not found");
//        }

//        Medication existingMedication = medicationOptional.get();
//        List<Medication> existingList = existingMedication.getMedicationList();
//        existingList.addAll(listaMedicamentos);
//        existingMedication.setMedicationList(existingList);

///        medicationRepository.save(existingMedication);
//    }

    public void addMedicationsToList(String id, List<Medication> listaMedicamentos) {
        Medication existingMedication = findMedicationById(id);
        addMedicationList(existingMedication, listaMedicamentos);
        medicationRepository.save(existingMedication);
    }
    private void addMedicationList(Medication existingMedication, List<Medication> listaMedicamentos) {
        List<Medication> existingList = existingMedication.getMedicationList();
        existingList.addAll(listaMedicamentos);
        existingMedication.setMedicationList(existingList);
    }
//Remover um medicamento da lista de medicamentos de uma determinada medicação.
// O método apenas busca a medicação no repositório,
// remove o medicamento da lista de medicamentos e salva a medicação atualizada.
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
// atualizar um medicamento na lista de medicamentos de uma determinada medicação.
// O método busca a medicação no repositório, verifica se o medicamento a ser atualizado está presente na lista,
// atualiza o medicamento e salva a medicação atualizada.
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