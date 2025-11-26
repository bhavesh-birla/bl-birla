package com.blbirla.app.service.impl;

import com.blbirla.app.entity.Medicine;
import com.blbirla.app.repository.MedicineRepository;
import com.blbirla.app.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medRepo;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medRepo) {
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

    public List<Medicine> findByName(String name) {
        return  this.medRepo.findByNameContainingIgnoreCase(name);
    }
}