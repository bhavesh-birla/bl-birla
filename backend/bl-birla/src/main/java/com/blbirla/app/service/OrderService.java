package com.blbirla.app.service;

import com.blbirla.app.entity.Medicine;
import com.blbirla.app.entity.Order;
import com.blbirla.app.entity.OrderItem;
import com.blbirla.app.entity.User;
import com.blbirla.app.repository.MedicineRepository;
import com.blbirla.app.repository.OrderRepository;
import com.blbirla.app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final MedicineRepository medRepo;
    private final UserRepository userRepo;

    public OrderService(OrderRepository orderRepo, MedicineRepository medRepo, UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.medRepo = medRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public Order placeOrder(Long userId, List<OrderItem> cartItems) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setCity(user.getCity());

        double total = 0;

        for (OrderItem ci : cartItems) {

            // Fetch actual medicine from DB
            Medicine med = medRepo.findById(ci.getMedicine().getMedicineId())
                    .orElseThrow(() -> new RuntimeException("Medicine not found"));

            if (med.getStockQuantity() < ci.getQuantity()) {
                throw new RuntimeException("Insufficient stock for " + med.getName());
            }

            // Deduct stock
            med.setStockQuantity(med.getStockQuantity() - ci.getQuantity());
            medRepo.save(med);

            // Create OrderItem
            OrderItem item = new OrderItem();
            item.setMedicine(med);
            item.setQuantity(ci.getQuantity());
            item.setUnitPrice(med.getWholesalePrice());
            item.setAmount(ci.getQuantity() * med.getWholesalePrice());

            order.addItem(item);

            total += item.getAmount();
        }

        order.setTotalAmount(total);

        return orderRepo.save(order);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepo.findByUserIdOrderByOrderDateDesc(userId);
    }
}

