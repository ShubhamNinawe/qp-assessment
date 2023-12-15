package com.web.grocery.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grocery_item")
public class GroceryItem {

	@Id
	@Column(name = "item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;

	@Column(name = "grocery_name")
	private String groceryName;

	@Column(name = "grocery_price")
	private Double groceryPrice;

	@Column(name = "grocery_quantity")
	private Integer groceryQuantity;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

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
