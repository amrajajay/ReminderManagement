package com.se.ReminderManagement.service;

import java.util.Random;

import com.se.ReminderManagement.exception.OTPException;
import com.se.ReminderManagement.exception.UserValidationException;

public interface OTPService {
	 public void validateIdAndSendOTP(String email) throws UserValidationException;
	 public String generateOTP();
	 public boolean resetPassword(String email, String newPassword, String otp);
	
}
