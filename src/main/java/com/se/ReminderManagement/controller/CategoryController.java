package com.se.ReminderManagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.se.ReminderManagement.entity.Category;
import com.se.ReminderManagement.entity.Task;
import com.se.ReminderManagement.exception.TaskException;
import com.se.ReminderManagement.exception.ValidationError;
import com.se.ReminderManagement.exception.ValidationErrorResponse;
import com.se.ReminderManagement.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/getAllCategories")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);

	}

	@PostMapping("/createCategory")
//	public ResponseEntity<?> createCategory(@RequestParam(value = "name", required = true) String name,
//			@RequestParam(value  = "description", required = false) String description) {
	public ResponseEntity<?> createCategory(@Valid @RequestBody Category cat, BindingResult result) {
		try {
			if (result.hasErrors()) {
				List<ValidationError> errors = result.getAllErrors().stream()
						.map(error -> new ValidationError(((FieldError) error).getField(), error.getDefaultMessage()))
						.collect(Collectors.toList());
				return ResponseEntity.badRequest().body(new ValidationErrorResponse(errors));
			}
			Category createdCat = categoryService.createCategory(cat);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdCat);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}

	}

}