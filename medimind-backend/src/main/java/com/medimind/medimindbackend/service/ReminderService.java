package com.medimind.medimindbackend.service;

import com.medimind.medimindbackend.model.Medicine;
import com.medimind.medimindbackend.repository.MedicineRepository;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReminderService {

    private final MedicineRepository medicineRepository;

    private final EmailService emailService;

    public ReminderService(
            MedicineRepository medicineRepository,
            EmailService emailService
    ) {

        this.medicineRepository = medicineRepository;

        this.emailService = emailService;
    }

    // =========================================
    // RUN EVERY 1 MINUTE
    // =========================================

    @Scheduled(fixedRate = 60000)
    public void checkMedicineReminders() {

        System.out.println("⏰ Reminder Scheduler Running...");

        // Current time HH:mm
        String currentTime = LocalTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm"));

        // Fetch medicines
        List<Medicine> medicines =
                medicineRepository.findAll();

        for (Medicine medicine : medicines) {

            // =====================================
            // CHECK REMINDER TIMES
            // =====================================

            if (
                    medicine.getTiming() != null &&
                            medicine.getTiming().equals(currentTime) &&
                            !medicine.isTaken()
            ) {

                        // =================================
                        // EMAIL ALERT
                        // =================================

                        emailService.sendReminderEmail(
                                medicine.getUserEmail(),
                                medicine.getMedicineName(),
                                medicine.getDosage()
                        );

                        System.out.println(
                                "📩 Reminder sent to: "
                                        + medicine.getUserEmail()
                        );

                        // =================================
                        // MISSED COUNT UPDATE
                        // =================================

                medicine.setMissedCount(
                        medicine.getMissedCount() + 1
                );

                        // SAVE DATABASE
                        medicineRepository.save(medicine);


                }
            }
        }
    }
