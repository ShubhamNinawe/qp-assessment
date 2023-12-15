package com.web.grocery.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.grocery.booking.entity.GroceryItem;
import com.web.grocery.booking.entity.OrderItem;
import com.web.grocery.booking.service.GroceryService;
import com.web.grocery.booking.service.OrderService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private GroceryService groceryService;

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/groceries")
    public List<GroceryItem> getAvailableGroceries() {
        return groceryService.getAllGroceryItems();
    }
	
	@PostMapping("/orders")
    public ResponseEntity<Void> placeOrder(@RequestBody List<OrderItem> orderItems) {
        orderService.placeOrder(orderItems);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
