package com.medimind.medimindbackend.controller;

import com.medimind.medimindbackend.model.Medicine;
import com.medimind.medimindbackend.service.MedicineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine")
@CrossOrigin(origins = "http://localhost:5173")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(
            MedicineService medicineService
    ) {
        this.medicineService = medicineService;
    }

    @PostMapping
    public Medicine addMedicine(
            @RequestBody Medicine medicine
    ) {
        return medicineService.addMedicine(medicine);
    }

    @GetMapping("/{email}")
    public List<Medicine> getMedicines(
            @PathVariable String email
    ) {
        return medicineService.getAllMedicines(email);
    }

    @DeleteMapping("/{id}")
    public String deleteMedicine(
            @PathVariable String id
    ) {

        medicineService.deleteMedicine(id);

        return "Deleted";
    }

    @PutMapping("/{id}/take")
    public Medicine takeMedicine(
            @PathVariable String id
    ) {
        return medicineService.markAsTaken(id);
    }
}