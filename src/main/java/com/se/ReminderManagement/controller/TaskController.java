package com.se.ReminderManagement.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.se.ReminderManagement.entity.Task;
import com.se.ReminderManagement.exception.TaskException;
import com.se.ReminderManagement.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@PostMapping("/createTask/v1")
	public ResponseEntity<?> createTask(@RequestBody Task task) {
		try {
			Task createdTask = taskService.createTask(task);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);

		} catch (TaskException e) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");

		}
		return null;
	}

	@PostMapping(value = "/createTask", consumes = "multipart/form-data")
	public ResponseEntity<?> createTask(@RequestParam("categoryId") String categoryId,
			@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate,
			@RequestParam("email") String email, @RequestParam("file") MultipartFile file) {
		try {
//			String fileReference = fileStorageService.store(file);
			Task task = new Task();
			task.setCategoryId(categoryId);
			task.setTitle(title);
			task.setDescription(description);
			task.setDueDate(java.sql.Timestamp.valueOf(dueDate));
			task.setEmail(email);
			Task createdTask = taskService.createTask(task);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);

		} catch (TaskException e) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");

		}
		return null;
	}

}