package com.medimind.medimindbackend.repository;

import com.medimind.medimindbackend.model.TestModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends MongoRepository<TestModel, String> {
}