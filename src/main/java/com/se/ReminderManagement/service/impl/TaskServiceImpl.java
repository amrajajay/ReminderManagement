package com.se.ReminderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.ReminderManagement.entity.Task;
import com.se.ReminderManagement.repository.TaskRepository;
import com.se.ReminderManagement.service.ReminderService;
import com.se.ReminderManagement.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ReminderService reminderService;

	@Override
	public Task createTask(Task task) {
		Task savedTask = taskRepository.save(task);
		reminderService.createDefaultReminderForTask(savedTask);

		return savedTask;
	}

}
