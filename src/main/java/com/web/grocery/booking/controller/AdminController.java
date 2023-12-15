package com.web.grocery.booking.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.grocery.booking.entity.GroceryItem;
import com.web.grocery.booking.service.GroceryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Admin Microservice Controller", description = "Grocery Microservice CRUD operations")
@RestController
@RequestMapping("/admin")
public class AdminController {

	public static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private GroceryService groceryService;

	@Operation(summary = "GET All GroceryItems RestAPI", description = "API for getting all GroceryItems List.")
	@ApiResponse(responseCode = "200")
	@GetMapping("/getAllGroceryItems")
	public ResponseEntity getAllGroceryItems() {
		logger.info("Inside AdminController, Executing getAllGroceryItems method ");
		List<GroceryItem> groceryItems = groceryService.getAllGroceryItems();
		return ResponseEntity.status(HttpStatus.OK).body(groceryItems);
	}

	@Operation(summary = "Add GroceryItem RestAPI", description = "API for Adding GroceryItem.")
	@ApiResponse(responseCode = "201")
	@PostMapping("/addGroceryItem")
	public ResponseEntity addGroceryItem(@RequestBody GroceryItem groceryItem) {
		logger.info("Inside AdminController, Executing addGroceryItem method ");
		GroceryItem groceryItems = groceryService.addGroceryItem(groceryItem);
		return ResponseEntity.status(HttpStatus.CREATED).body(groceryItems);
	}

	@Operation(summary = "Update GroceryItem RestAPI", description = "API for updating GroceryItem.")
	@ApiResponse(responseCode = "200")
	@PutMapping("/updateGroceryItem")
	public ResponseEntity updateGroceryItem(@RequestBody GroceryItem updatedItem) {
		logger.info("Inside AdminController, Executing updateGroceryItem method ");
		GroceryItem updatedItems = groceryService.updateGroceryItem(updatedItem);
		return ResponseEntity.status(HttpStatus.OK).body(updatedItems);
	}

	@Operation(summary = "Remove GroceryItem RestAPI", description = "API for Removing GroceryItem.")
	@ApiResponse(responseCode = "200")
	@DeleteMapping("/removeGroceryItem/{id}")
	public ResponseEntity removeGroceryItem(@PathVariable("id") Long itemId) {
		logger.info("Inside AdminController, Executing removeGroceryItem method ");
		String removeGroceryItem = groceryService.removeGroceryItem(itemId);
		return ResponseEntity.status(HttpStatus.OK).body(removeGroceryItem);
	}

	@Operation(summary = "Update Inventory RestAPI", description = "API for updating Inventory.")
	@ApiResponse(responseCode = "200")
	@PatchMapping("/updateInventory/{id}/inventory")
	public ResponseEntity updateInventory(@PathVariable("id") Long itemId,
			@RequestBody Map<String, Integer> request) {
		logger.info("Inside AdminController, Executing updateInventory method ");
		groceryService.updateInventory(itemId, request.get("quantity"));
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
