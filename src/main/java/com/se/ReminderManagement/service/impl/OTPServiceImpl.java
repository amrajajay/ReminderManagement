package com.se.ReminderManagement.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.se.ReminderManagement.exception.OTPException;
import com.se.ReminderManagement.exception.UserValidationException;
import com.se.ReminderManagement.repository.UserRepository;
import com.se.ReminderManagement.service.EmailSender;
import com.se.ReminderManagement.service.OTPService;
import com.se.ReminderManagement.service.UserService;

@Service
public class OTPServiceImpl implements OTPService {
	@Autowired
	private EmailSender mailSender;

	@Autowired
	private UserService user;

	@Autowired
	private UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private Map<String, String> otpStorage = new HashMap<>();

	@Override
	public void validateIdAndSendOTP(String email) throws UserValidationException {
		if (!user.userExists(email)) {
			throw new UserValidationException(email + " user not found");
		}
		String otp = generateOTP();
		otpStorage.put(email, otp);
		mailSender.sendEmail(email, "OTP for Password Reset", "Your OTP is: " + otp);

	}

	@Override
	public String generateOTP() {
		Random random = new Random();
		int num = 100000 + random.nextInt(900000);
		return String.valueOf(num);
	}

	@Override
	public boolean resetPassword(String email, String newPassword, String otp) throws OTPException {

		String storedOtp = otpStorage.getOrDefault(email, "");
		if (!storedOtp.equals(otp)) {
			throw new OTPException("Invalid OTP");
		}
		return userRepository.findById(email).map(user -> {
			String encodedPassword = passwordEncoder.encode(newPassword);
			user.setPassword(encodedPassword);
			userRepository.save(user);
			return true;
		}).orElse(false);

	}

}
