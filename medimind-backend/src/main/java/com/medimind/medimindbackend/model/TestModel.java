package com.medimind.medimindbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "test")
public class TestModel {

    @Id
    private String id;

    private String name;
}