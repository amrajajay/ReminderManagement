package com.se.ReminderManagement.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
//	@ExceptionHandler(TaskException.class)
//	public ResponseEntity<Object> handleTaskCreationException(TaskException ex, WebRequest request) {
//		Map<String, Object> body = new LinkedHashMap<>();
//		body.put("timestamp", LocalDateTime.now());
//		body.put("message", "Task creation failed: " + ex.getMessage());
//		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
		// Generic exception handler for unexpected errors
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", " failed: " + ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}
