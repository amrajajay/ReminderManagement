package com.se.ReminderManagement.service;

import com.se.ReminderManagement.entity.Reminder;
import com.se.ReminderManagement.entity.Task;

public interface ReminderService {
	public Reminder createDefaultReminderForTask(Task task);

	public void scheduleDefaultReminder(Reminder reminder);

}
