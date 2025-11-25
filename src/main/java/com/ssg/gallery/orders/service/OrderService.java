package com.ssg.gallery.orders.service;

import com.ssg.gallery.orders.dto.OrderRead;
import com.ssg.gallery.orders.dto.OrderRequest;

import java.util.List;

public interface OrderService {
    List<OrderRead> findAll(Integer memberId);
    OrderRead find(Integer id, Integer memberId);
    void order(OrderRequest orderReq, Integer memberId);
}
