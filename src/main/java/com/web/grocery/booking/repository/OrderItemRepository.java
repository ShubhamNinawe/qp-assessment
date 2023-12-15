package com.web.grocery.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.grocery.booking.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
