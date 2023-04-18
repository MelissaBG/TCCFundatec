package com.fundatec.tcc.repository;

import com.fundatec.tcc.model.Medication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicationRepository extends MongoRepository<Medication, String> {
}
