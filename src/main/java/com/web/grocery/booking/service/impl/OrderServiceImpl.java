package com.web.grocery.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.grocery.booking.entity.GroceryItem;
import com.web.grocery.booking.entity.OrderItem;
import com.web.grocery.booking.exception.GroceryException;
import com.web.grocery.booking.repository.GroceryRepository;
import com.web.grocery.booking.repository.OrderItemRepository;
import com.web.grocery.booking.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private GroceryRepository groceryRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = GroceryException.class)
	public void placeOrder(List<OrderItem> orderItems) {
		// Validate and process the order
		for (OrderItem orderItem : orderItems) {
			GroceryItem groceryItem = groceryRepository.findById(orderItem.getItemId())
					.orElseThrow(() -> new GroceryException("Grocery item not found"));

			if (groceryItem.getGroceryQuantity() < orderItem.getQuantity()) {
				throw new GroceryException("Not enough inventory for item: " + groceryItem.getGroceryName());
			}

			// Update inventory
			groceryItem.setGroceryQuantity(groceryItem.getGroceryQuantity() - orderItem.getQuantity());
			groceryRepository.save(groceryItem);

			// Record order item
			OrderItem newOrderItem = new OrderItem();
			newOrderItem.setItemId(orderItem.getItemId());
			newOrderItem.setQuantity(orderItem.getQuantity());
			orderItemRepository.save(newOrderItem);
		}
	}

}
