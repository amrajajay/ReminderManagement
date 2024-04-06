package com.se.ReminderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.se.ReminderManagement.entity.User;
import com.se.ReminderManagement.exception.UserValidationException;
import com.se.ReminderManagement.repository.UserRepository;
import com.se.ReminderManagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public User createUser(User user) {
		if (userRepository.existsById(user.getEmail())) {
			throw new UserValidationException("User already exists");
		}
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		return userRepository.save(user);
	}

}
