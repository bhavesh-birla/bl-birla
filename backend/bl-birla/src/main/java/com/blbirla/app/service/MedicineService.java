package com.blbirla.app.service;

import com.blbirla.app.entity.Medicine;
import com.blbirla.app.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medRepo;

    @Autowired
    public MedicineService(MedicineRepository medRepo) {
        this.medRepo = medRepo;
    }

    public List<Medicine> getAllMedicines() {
        return medRepo.findAll();
    }

    public Medicine getMedicineById(Long id) {
        return medRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));
    }

    public Medicine reduceStock(Long medicineId, int quantity) {
        Medicine med = getMedicineById(medicineId);
        if (med.getStockQuantity() < quantity) {
            throw new RuntimeException("Not enough stock for: " + med.getName());
        }

        med.setStockQuantity(med.getStockQuantity() - quantity);
        return medRepo.save(med);
    }
}