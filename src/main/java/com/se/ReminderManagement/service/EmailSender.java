package com.se.ReminderManagement.service;

import jakarta.mail.MessagingException;

public interface EmailSender {
	
	public void sendEmail(String to, String subject, String text);
	public void sendEmailWithAttachment(String to, String subject, String text, byte[] attachmentData, String attachmentName) throws MessagingException;
			
	

}
