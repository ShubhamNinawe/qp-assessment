package com.web.grocery.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.grocery.booking.entity.GroceryItem;

public interface GroceryRepository extends JpaRepository<GroceryItem, Long> {

	public GroceryItem findByItemId(Long itemId);
}
