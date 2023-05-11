package com.fundatec.tcc.repository;

import com.fundatec.tcc.model.Medication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends MongoRepository<Medication, String> {
    Medication findByName(String name);

    void deleteByName(String medicationName);
}
