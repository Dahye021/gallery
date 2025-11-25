package com.ssg.gallery.orders.service;

import com.ssg.gallery.orders.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findAll(Integer orderId);
    void saveAll(List<OrderItem> orderItems);
}
