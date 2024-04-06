package com.se.ReminderManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.ReminderManagement.entity.Category;
import com.se.ReminderManagement.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
		@Autowired
		private CategoryService categoryService;
		
		@GetMapping("/getAllCategories")
		public ResponseEntity<List<Category>> entity() {
			List<Category> categories = categoryService.getAllCategories();
//			if (customers.isEmpty())
//				throw new CustomerNotFoundException("Records not available");
			return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);

		}
}