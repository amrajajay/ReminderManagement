package com.se.ReminderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.se.ReminderManagement.repository.UserRepository;
import com.se.ReminderManagement.entity.User;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("foolishuser".equals(username)) {
//            return new User("foolishuser", "$2a$10$7pXT3Q6xnONfSdBNlxvqk.j4lc6dGi.TuSxRez7ddia6PyRKBn3TK",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }

		User user = userRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());

	}
}