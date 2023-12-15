package com.web.grocery.booking.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.grocery.booking.contants.GroceryConstants;
import com.web.grocery.booking.entity.GroceryItem;
import com.web.grocery.booking.exception.GroceryException;
import com.web.grocery.booking.repository.GroceryRepository;
import com.web.grocery.booking.service.GroceryService;

@Service
public class GroceryServiceImpl implements GroceryService {

	public static final Logger logger = LoggerFactory.getLogger(GroceryServiceImpl.class);

	@Autowired
	private GroceryRepository groceryRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<GroceryItem> getAllGroceryItems() {

		logger.info("Inside GroceryServiceImpl class, executing getAllGroceryItems method");
		List<GroceryItem> groceryItems = null;

		try {

			logger.info("Finding all Grocery");
			groceryItems = groceryRepository.findAll();

		} catch (Exception e) {

			logger.error("Exception in getAllGroceryItems service" + " " + e.getMessage(), e);
			throw new GroceryException(e.getMessage());
		}

		return groceryItems;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = GroceryException.class)
	public GroceryItem addGroceryItem(GroceryItem newGroceryItem) {

		logger.info("Inside addGroceryItem Service {} ", newGroceryItem.getClass());
		try {

			if (newGroceryItem.getGroceryName() != null && newGroceryItem.getGroceryPrice() != null
					&& newGroceryItem.getGroceryQuantity() != null) {

				logger.info("saving GroceryItem into db");
				groceryRepository.save(newGroceryItem);

			} else {

				logger.error("Exception in addGroceryItem service GroceryItem cannot be null");
				throw new GroceryException("Exception in addGroceryItem service GroceryItem cannot be null");

			}

		} catch (Exception e) {

			logger.error("Exception in addGroceryItem service" + " " + e.getMessage(), e);
			throw new GroceryException(e.getMessage());
		}

		return newGroceryItem;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = GroceryException.class)
	public GroceryItem updateGroceryItem(GroceryItem updatedItem) {

		logger.info("Inside updateEmployee Service {} ", updatedItem.getClass());
		GroceryItem existingroceryItem = null;
		try {

			logger.info("Getting GroceryItem with Id {} ", updatedItem.getItemId());
			existingroceryItem = groceryRepository.findByItemId(updatedItem.getItemId());

			if (existingroceryItem != null) {

				logger.info("updating groceryItem with id {}", updatedItem.getItemId());
				existingroceryItem.setGroceryName(updatedItem.getGroceryName());
				existingroceryItem.setGroceryPrice(updatedItem.getGroceryPrice());
				existingroceryItem.setGroceryQuantity(updatedItem.getGroceryQuantity());
				groceryRepository.save(existingroceryItem);
			}

			else {

				logger.warn("groceryItem doesnot exist with Id {} ", updatedItem.getItemId());
				throw new GroceryException("groceryItem doesnot exist with Id {} " + " " + updatedItem.getItemId());

			}

		} catch (Exception e) {

			logger.error("Exception in updateGroceryItem service" + " " + e.getMessage(), e);
			throw new GroceryException(e.getMessage());
		}

		return existingroceryItem;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = GroceryException.class)
	public String removeGroceryItem(Long itemId) {

		logger.info("Inside removeGroceryItem Service ItemId{} ", itemId);

		try {

			logger.info("Getting GroceryItem with Id {} ", itemId);
			Optional<GroceryItem> groceryItem = groceryRepository.findById(itemId);

			if (groceryItem.isPresent()) {

				logger.info("If found removing with Id {}", itemId);
				groceryRepository.deleteById(itemId);

			} else {

				logger.warn("groceryItem doesnot exist with Id {} ", itemId);
				throw new GroceryException("groceryItem doesnot exist with Id {} " + " " + itemId);

			}

		} catch (Exception e) {

			logger.error("Exception in removeGroceryItem service" + " " + e.getMessage(), e);
			throw new GroceryException(e.getMessage());
		}

		return GroceryConstants.SUCCESS;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = GroceryException.class)
	public void updateInventory(Long itemId, Integer quantity) {
		logger.info("Inside updateInventory Service ItemId{} ", itemId);
		GroceryItem existingItem = groceryRepository.findById(itemId)
				.orElseThrow(() -> new GroceryException("Grocery item not found"));

		existingItem.setGroceryQuantity(existingItem.getGroceryQuantity() + quantity);

		groceryRepository.save(existingItem);
	}

}
