package com.ssg.gallery.orders.service;


import com.ssg.gallery.Cart.service.CartService;
import com.ssg.gallery.item.dto.ItemRead;
import com.ssg.gallery.item.service.ItemService;
import com.ssg.gallery.orders.dto.OrderRead;
import com.ssg.gallery.orders.dto.OrderRequest;
import com.ssg.gallery.orders.entity.Order;
import com.ssg.gallery.orders.entity.OrderItem;
import com.ssg.gallery.orders.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BaseOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final ItemService itemService;
    private final CartService cartService;


    @Override
    public List<OrderRead> findAll(Integer memberId) {
        return orderRepository.findAllByMemberIdOrderByIdDesc(memberId).stream().map(Order::toRead).toList();
    }

    @Override
    public OrderRead find(Integer id, Integer memberId) {
        Optional<Order> orderOptional = orderRepository.findByIdAndMemberId(id, memberId);

        if (orderOptional.isPresent()) {
            OrderRead order = orderOptional.get().toRead();

            List<OrderItem> orderItems = orderItemService.findAll(order.getId());

            List<Integer> orderItemIds = orderItems.stream().map(OrderItem::getId).toList();

            List<ItemRead> items = itemService.findAll(orderItemIds);

            order.setItems(items);

            return order;
        }
        return null;
    }

    @Override
    @Transactional
    public void order(OrderRequest orderReq, Integer memberId) {
        List<ItemRead> items = itemService.findAll(orderReq.getItemIds());
        long amount = 0L;

        for (ItemRead item : items) {
            amount += item.getPrice() - item.getPrice().longValue() * item.getDiscountPer() / 100;
        }

        orderReq.setAmount(amount);

        Order order = orderRepository.save(orderReq.toEntity(memberId));

        List<OrderItem> newOrderItems = new ArrayList<>();

        orderReq.getItemIds().forEach((itemId) -> {
            OrderItem newOrderItem = new OrderItem(order.getId(), itemId);
            newOrderItems.add(newOrderItem);
        });

        orderItemService.saveAll(newOrderItems);

        cartService.removeAll(order.getMemberId());
    }
}
