package com.se.ReminderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.se.ReminderManagement.entity.User;
import com.se.ReminderManagement.exception.UserValidationException;
import com.se.ReminderManagement.repository.UserRepository;
import com.se.ReminderManagement.service.EmailSender;
import com.se.ReminderManagement.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailSender mailSender;
	
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public User createUser(User user) {
		if (userRepository.existsById(user.getEmail())) {
			throw new UserValidationException("User already exists");
		}
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		User newUser =  userRepository.save(user);
		mailSender.sendEmail(user.getEmail(), "Registation Sucessfull", "Thank you for registering into csrms application");
		
		return newUser;
	}

	@Override
	public String getCurrentUserEmail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof User) {
				User userDetails = (User) principal;
				return userDetails.getEmail(); // From your custom UserDetails
			} else if (principal instanceof String) {
				return (String) principal; // In simpler configurations, the principal might be a string.
			}
		}
		return null;
	}

	@Override
	public boolean userExists(String email) {
		return  (userRepository.existsById(email));
			
	}

}
