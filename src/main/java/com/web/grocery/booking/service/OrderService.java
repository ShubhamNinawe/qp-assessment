package com.web.grocery.booking.service;

import java.util.List;

import com.web.grocery.booking.entity.OrderItem;

public interface OrderService {
	
	public void placeOrder(List<OrderItem> orderItems);

}
