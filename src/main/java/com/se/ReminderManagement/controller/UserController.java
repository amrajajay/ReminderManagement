package com.se.ReminderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.ReminderManagement.entity.User;
import com.se.ReminderManagement.exception.UserValidationException;
import com.se.ReminderManagement.service.UserService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {
	    @Autowired
	    private UserService userService;

	    @PostMapping("/createUser")
	    public ResponseEntity<?> createUser(@RequestBody User user) {
	    	 try {
	             User savedUser = userService.createUser(user);
	             return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	         } catch (UserValidationException e) {
	             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	         }
	    	 catch (Exception e) {
	             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
	         }
	    }

}
