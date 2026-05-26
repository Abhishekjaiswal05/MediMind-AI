package com.medimind.medimindbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "medicines")
public class Medicine {

    @Id
    private String id;

    private String medicineName;

    private String dosage;

    private String timing;

    private boolean taken;

    private int missedCount;

    private String userEmail;
}