package com.web.grocery.booking.dto;

public class GroceryItemDto {

	private String groceryName;
	
	private Double groceryPrice;

	private Integer groceryQuantity;

	public String getGroceryName() {
		return groceryName;
	}

	public void setGroceryName(String groceryName) {
		this.groceryName = groceryName;
	}

	public Double getGroceryPrice() {
		return groceryPrice;
	}

	public void setGroceryPrice(Double groceryPrice) {
		this.groceryPrice = groceryPrice;
	}

	public Integer getGroceryQuantity() {
		return groceryQuantity;
	}

	public void setGroceryQuantity(Integer groceryQuantity) {
		this.groceryQuantity = groceryQuantity;
	}

}
