package com.se.ReminderManagement.exception;

import java.net.BindException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(TaskException.class)
    public ResponseEntity<Object> handleTaskCreationException(TaskException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Task creation failed: " + ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(OTPException.class)
    public ResponseEntity<Object> handleOTPException(OTPException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "OTP Validatation failed: " + ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
	

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "An unexpected error occurred: " + ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Object> handleMethodArgumentNotValid(Exception ex, WebRequest wb) {
//		Map<String, String> errorMap = new HashMap<>();
//		((BindException) ex).getBindingResult().getFieldErrors().forEach(error -> {
//			errorMap.put(error.getField(), error.getDefaultMessage());
//		});
//		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
//
//	}

}
