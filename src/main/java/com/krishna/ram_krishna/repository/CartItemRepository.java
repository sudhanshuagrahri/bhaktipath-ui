package com.krishna.ram_krishna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krishna.ram_krishna.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserUniqueId(String userUniqueId);
    void deleteByUserUniqueId(String userUniqueId);
}
