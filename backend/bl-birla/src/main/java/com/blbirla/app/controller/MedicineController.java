package com.blbirla.app.controller;

import com.blbirla.app.entity.Medicine;
import com.blbirla.app.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin
public class MedicineController {

    private final MedicineService medService;

    @Autowired
    public MedicineController(MedicineService medService) {
        this.medService = medService;
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        return ResponseEntity.ok(this.medService.getAllMedicines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicine(@PathVariable Long id) {
        return ResponseEntity.ok(medService.getMedicineById(id));
    }
}
