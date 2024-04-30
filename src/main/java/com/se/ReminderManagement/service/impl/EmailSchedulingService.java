package com.se.ReminderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import com.se.ReminderManagement.service.EmailSender;

import jakarta.mail.MessagingException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class EmailSchedulingService {

	@Autowired
	private EmailSender mailSender;

	@Autowired
	private TaskScheduler taskScheduler;

	public void scheduleTaskEmail(LocalDateTime dueDate, String email, String subject, String body,
			byte[] attachmentData, String attachmentName) {

		Runnable emailTask = () -> {
			try {
				mailSender.sendEmailWithAttachment(email, subject, body, attachmentData, attachmentName);
			} catch (MessagingException e) {
				e.printStackTrace(); // Proper error handling should be implemented
			}
		};

		// Schedule the task for the due date
		Date startTime = Date.from(dueDate.atZone(ZoneId.systemDefault()).toInstant());
		taskScheduler.schedule(emailTask, startTime);
	}
}
