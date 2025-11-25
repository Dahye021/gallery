package com.ssg.gallery.Cart.dto;

import com.ssg.gallery.Cart.entity.Cart;
import lombok.Getter;

@Getter
public class CartRequest {
    private Integer itemId;
    public Cart toEntity(Integer memberId) {
        return new Cart(memberId, itemId);
    }
}
