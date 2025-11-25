package com.ssg.gallery.Cart.controller;

import com.ssg.gallery.Cart.dto.CartRead;
import com.ssg.gallery.Cart.dto.CartRequest;
import com.ssg.gallery.Cart.repository.CartRepository;
import com.ssg.gallery.Cart.service.CartService;
import com.ssg.gallery.account.helper.AccountHelper;
import com.ssg.gallery.item.dto.ItemRead;
import com.ssg.gallery.item.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CartController {
    private final ItemService itemService;
    private final AccountHelper accountHelper;
    private final CartService cartService;

    @GetMapping("/api/cart/items")
    public ResponseEntity<?> readAll(HttpServletRequest req) {
        Integer memberId = accountHelper.getMemberId(req);
        List<CartRead> carts = cartService.findAll(memberId);
        List<Integer> itemIds = carts.stream().map(CartRead::getItemId).toList();
        List<ItemRead> items = itemService.findAll(itemIds);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/api/carts")
    public ResponseEntity<?> push(HttpServletRequest req, @RequestBody CartRequest cartReq) { // ⑧
        // 로그인 회원 아이디
        Integer memberId = accountHelper.getMemberId(req);

        // 장바구니 데이터 조회(특정 상품)
        CartRead cart = cartService.find(memberId, cartReq.getItemId());

        // 장바구니 데이터가 없다면
        if (cart == null) {
            cartService.save(cartReq.toEntity(memberId));
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/api/carts/items/{itemId}")
    public ResponseEntity<?> remove(HttpServletRequest req, @PathVariable Integer itemId) {
        Integer memberId = accountHelper.getMemberId(req);

        cartService.remove(memberId, itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
