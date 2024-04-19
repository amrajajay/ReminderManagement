package com.se.ReminderManagement.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.se.ReminderManagement.entity.Task;
import com.se.ReminderManagement.exception.TaskException;
import com.se.ReminderManagement.service.TaskService;
import com.se.ReminderManagement.service.UserService;

@RestController
@RequestMapping("/v2/task")
public class TaskControllerV2 {
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;

	@PostMapping(value = "/createTask", consumes = "multipart/form-data")
	public ResponseEntity<?> createTask(@RequestParam("categoryId") String categoryId,
			@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("dueDate")   @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm") LocalDateTime dueDate,
			@RequestParam("email") String email, @RequestParam(value = "file", required = true) MultipartFile file
			) {
		try {
			
//			LocalDateTime now = LocalDateTime.now();
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//			formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss", Locale.US);
//	        formattedDateTime = now.format(formatter);
			
			
			Task task = new Task();
			task.setCategoryId(categoryId);
			task.setTitle(title);
			task.setDescription(description);
			task.setDueDate(dueDate);

			task.setEmail(email);
			task.setAttachments(file.getBytes());
			task.setAttachmentsName(file.getOriginalFilename());
			Task createdTask = taskService.createTask(task);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
		} catch (TaskException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");

		}
	}
}
