package com.blbirla.app.repository;

import com.blbirla.app.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Long> {
    List<Medicine> findByNameContainingIgnoreCase(String name);
}
