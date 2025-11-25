package com.ssg.gallery.Cart.service;

import com.ssg.gallery.Cart.dto.CartRead;
import com.ssg.gallery.Cart.entity.Cart;

import java.util.List;

public interface CartService {
    List<CartRead> findAll(Integer memberId);
    CartRead find(Integer memberId, Integer itemId);
    void removeAll(Integer memberId);
    void remove (Integer memberId, Integer itemId);
    void save(Cart cart);
}
