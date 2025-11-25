package com.ssg.gallery.orders.controller;

import com.ssg.gallery.account.helper.AccountHelper;
import com.ssg.gallery.orders.dto.OrderRead;
import com.ssg.gallery.orders.dto.OrderRequest;
import com.ssg.gallery.orders.entity.OrderItem;
import com.ssg.gallery.orders.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class OrderController {
    private final AccountHelper accountHelper;
    private final OrderService orderService;

    @GetMapping("/api/orders")
    public ResponseEntity<?> readAll(HttpServletRequest req){
        Integer memberId = accountHelper.getMemberId(req);
        List<OrderRead> orders = orderService.findAll(memberId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/api/orders/{id}")
    public ResponseEntity<?> read(HttpServletRequest req, @PathVariable Integer id) {
        Integer memberId = accountHelper.getMemberId(req);
        OrderRead order = orderService.find(id, memberId);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/api/orders")
    public ResponseEntity<?> add(HttpServletRequest req, @RequestBody OrderRequest orderReq) {
        Integer memberId = accountHelper.getMemberId(req);

        orderService.order(orderReq, memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
