package com.se.ReminderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.se.ReminderManagement.entity.User;
import com.se.ReminderManagement.exception.OTPException;
import com.se.ReminderManagement.exception.UserValidationException;
import com.se.ReminderManagement.service.OTPService;
import com.se.ReminderManagement.service.UserService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private OTPService otpService;

	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		try {
			User savedUser = userService.createUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		} catch (UserValidationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
		}
	}

	@PostMapping("/generate-otp")
	public ResponseEntity<?> generateOTP(@RequestParam("email") String email) {
		try {
			otpService.validateIdAndSendOTP(email);
			return ResponseEntity.ok("OTP sent to your registered email.");
		} catch (UserValidationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to send OTP.");
		}
	}

	@PostMapping("/validate-otp-reset-password")
	public ResponseEntity<?> validateOtpResetPassword(@RequestParam("email") String email,
			@RequestParam("newPassword") String newPassword, @RequestParam("otp") String otp) {
		try {
			boolean resetted = otpService.resetPassword(email, newPassword, otp);
			if(resetted) {
				return ResponseEntity.ok("Password updated");	
			}
			else {
				throw new OTPException("Failed to reset password.");
			}
		} catch (OTPException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to reset password.");

		}


	}

}
