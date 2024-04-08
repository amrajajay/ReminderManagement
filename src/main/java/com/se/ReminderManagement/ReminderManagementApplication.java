package com.se.ReminderManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReminderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReminderManagementApplication.class, args);
	}

}
