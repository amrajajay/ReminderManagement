package com.se.ReminderManagement.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import com.se.ReminderManagement.entity.User;

public interface UserService {

	public User createUser(User user);

	public String getCurrentUserEmail();
	
	public boolean userExists(String email);
}
