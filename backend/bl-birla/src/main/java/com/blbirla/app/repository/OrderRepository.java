package com.blbirla.app.repository;

import com.blbirla.app.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserIdOrderByOrderDateDesc(Long userId);
}
