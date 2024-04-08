package com.se.ReminderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import com.se.ReminderManagement.entity.Reminder;
import com.se.ReminderManagement.entity.Task;
import com.se.ReminderManagement.enumerators.NotificationMethod;
import com.se.ReminderManagement.repository.ReminderRepository;
import com.se.ReminderManagement.service.ReminderService;

@Service
public class ReminderServiceImpl implements ReminderService {
	@Autowired
	ReminderRepository reminderRepository;

	@Autowired
	private EmailSchedulingService emailSchedulingService;

	@Override
	public Reminder createDefaultReminderForTask(Task task) {
		Reminder remainder = new Reminder();
		remainder.setTask(task);
		remainder.setNotificationMethod(NotificationMethod.email);
		remainder.setTimeInterval("On due date");
		remainder.setIsActive(true);
		// Save this remainder to your database
		scheduleDefaultReminder(remainder);
		return reminderRepository.save(remainder);
	}

	@Override
	public void scheduleDefaultReminder(Reminder reminder) {
		emailSchedulingService.scheduleTaskEmail(reminder.getTask().getDueDate(), reminder.getTask().getEmail(),
				reminder.getTask().getTitle(),
				"This is a reminder that your task '\n" + reminder.getTask() + "' is due.");

	}

}
