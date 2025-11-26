package com.blbirla.app.service;

import com.blbirla.app.entity.Medicine;

import java.util.List;

public interface MedicineService {

    List<Medicine> getAllMedicines();
    public Medicine getMedicineById(Long id);
    public Medicine reduceStock(Long medicineId, int quantity);
    public List<Medicine> findByName(String name);
}