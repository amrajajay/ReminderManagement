package com.se.ReminderManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@Column(name = "task_id", nullable = false, length = 12)
	private String taskId;

	@Column(name = "category_id", nullable = false, length = 12)
	private String categoryId;

	@Column(name = "title", nullable = false, length = 20)
	private String title;

	@Column(name = "description", length = 50)
	private String description;

	@Column(name = "dueDate", nullable = false)
	private Date dueDate;

	@Lob
	@Column(name = "attachments")
	private byte[] attachments;

	@Column(name = "email", nullable = false, length = 30)
	private String email;

	@ManyToOne
	@JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
	private User user;

	// Getters and Setters
}
