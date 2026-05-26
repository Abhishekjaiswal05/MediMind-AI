package com.medimind.medimindbackend.service;

import com.medimind.medimindbackend.model.Medicine;
import com.medimind.medimindbackend.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

  private final MedicineRepository medicineRepository;

  public MedicineService(
          MedicineRepository medicineRepository
  ) {
    this.medicineRepository = medicineRepository;
  }

  public Medicine addMedicine(
          Medicine medicine
  ) {
    return medicineRepository.save(medicine);
  }

  public List<Medicine> getAllMedicines(
          String email
  ) {
    return medicineRepository
            .findByUserEmail(email);
  }

  public void deleteMedicine(String id) {
    medicineRepository.deleteById(id);
  }

  public Medicine markAsTaken(String id) {

    Medicine medicine =
            medicineRepository.findById(id)
                    .orElseThrow();

    medicine.setTaken(true);

    return medicineRepository.save(medicine);
  }
}