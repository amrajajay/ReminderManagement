package com.se.ReminderManagement.entity;

import java.util.UUID;

import com.se.ReminderManagement.enumerators.NotificationMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;

@Entity
@Table(name = "remainder")
public class Reminder {

	@Id
	@Column(name = "remainderId", nullable = false, length = 32)
	private String remainderId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id", referencedColumnName = "task_id", nullable = false, foreignKey = @ForeignKey(name = "FK_remainder_task"))
	private Task task;

	@Enumerated(EnumType.STRING)
	@Column(name = "notificationMethod", nullable = false)
	private NotificationMethod notificationMethod = NotificationMethod.email;

	@Column(name = "time_interval", length = 100)
	private String timeInterval;

	@Column(name = "is_active")
	private Boolean isActive;

	@PrePersist
	protected void onCreate() {
		remainderId = UUID.randomUUID().toString();
		if (remainderId.length() > 99) {
			remainderId = remainderId.substring(0, 98);
		}
	}

	public String getRemainderId() {
		return remainderId;
	}

	public void setRemainderId(String remainderId) {
		this.remainderId = remainderId;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public NotificationMethod getNotificationMethod() {
		return notificationMethod;
	}

	public void setNotificationMethod(NotificationMethod notificationMethod) {
		this.notificationMethod = notificationMethod;
	}

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Reminder(String remainderId, Task task, NotificationMethod notificationMethod, String timeInterval,
			Boolean isActive) {
		super();
		this.remainderId = remainderId;
		this.task = task;
		this.notificationMethod = notificationMethod;
		this.timeInterval = timeInterval;
		this.isActive = isActive;
	}

	public Reminder() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Reminder [remainderId=" + remainderId + ", task=" + task + ", notificationMethod=" + notificationMethod
				+ ", timeInterval=" + timeInterval + ", isActive=" + isActive + "]";
	}

}