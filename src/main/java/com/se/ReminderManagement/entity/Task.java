package com.se.ReminderManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@Column(name = "task_id", nullable = false)
	private String taskId;

	@Column(name = "category_id", nullable = false, length = 12)
	private String categoryId;

	@Column(name = "title", nullable = false, length = 20)
	private String title;

	@Column(name = "description", length = 50)
	private String description;

	@Column(name = "dueDate", nullable = false)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dueDate;

	@Lob
	@Column(name = "attachments")
	private byte[] attachments;

	@Transient
	private String attachmentsName;

	public String getAttachmentsName() {
		return attachmentsName;
	}

	public void setAttachmentsName(String attachmentsName) {
		this.attachmentsName = attachmentsName;
	}

	@Column(name = "email", nullable = false, length = 30)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String email;

	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
	private User user;

	@PrePersist
	protected void onCreate() {
		taskId = UUID.randomUUID().toString();
		if (taskId.length() > 99) {
			taskId = taskId.substring(0, 98);
		}
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public byte[] getAttachments() {
		return attachments;
	}

	public void setAttachments(byte[] attachments) {
		this.attachments = attachments;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task(String taskId, String categoryId, String title, String description, LocalDateTime dueDate, byte[] attachments,
			String attachmentsName, String email, User user) {
		super();
		this.taskId = taskId;
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.attachments = attachments;
		this.attachmentsName = attachmentsName;
		this.email = email;
		this.user = user;
	}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", categoryId=" + categoryId + ", title=" + title + ", description="
				+ description + ", dueDate=" + dueDate + ", attachments=" + new ByteArrayResource(attachments)
				+ ", attachmentsName=" + attachmentsName + ", email=" + email + ", user=" + user + "]";
	}

}
