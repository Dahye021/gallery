package com.ssg.gallery.item.repository;

import com.ssg.gallery.item.entity.Item;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    //여러 상품 아이디로 상품 데이터를 조회하는 메서드
            List<Item> findAllByIdIn(List<Integer> ids);
}
