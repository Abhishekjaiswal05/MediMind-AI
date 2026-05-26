package com.medimind.medimindbackend.repository;

import com.medimind.medimindbackend.model.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MedicineRepository
        extends MongoRepository<Medicine, String> {

    List<Medicine> findByUserEmail(String userEmail);
}