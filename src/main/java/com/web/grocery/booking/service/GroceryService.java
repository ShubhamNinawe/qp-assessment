package com.web.grocery.booking.service;

import java.util.List;

import com.web.grocery.booking.entity.GroceryItem;

public interface GroceryService {
	
	public List<GroceryItem> getAllGroceryItems();
	
	public GroceryItem addGroceryItem(GroceryItem newGroceryItem);
	
	public GroceryItem updateGroceryItem(GroceryItem updatedItem);
	
	 public String removeGroceryItem(Long itemId);
	 
	 public void updateInventory(Long itemId, Integer quantity);

}
