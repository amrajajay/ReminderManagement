package com.se.ReminderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class EmailSchedulingService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TaskScheduler taskScheduler;

	public void scheduleTaskEmail(Date dueDate, String email, String subject, String body) {
		Runnable emailTask = () -> sendEmail(email, subject, body);

		// Schedule the task for the due date
		taskScheduler.schedule(emailTask, dueDate);
	}

	private void sendEmail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);
	}
}
