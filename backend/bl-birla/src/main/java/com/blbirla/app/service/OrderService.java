package com.blbirla.app.service;

import com.blbirla.app.entity.Order;
import com.blbirla.app.entity.OrderItem;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId, List<OrderItem> cartItems);
    List<Order> getOrdersByUser(Long userId);
}
