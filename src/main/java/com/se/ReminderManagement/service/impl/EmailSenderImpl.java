package com.se.ReminderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.se.ReminderManagement.service.EmailSender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderImpl implements EmailSender {
	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);
	}

	@Override
	public void sendEmailWithAttachment(String to, String subject, String text, byte[] attachmentData,
			String attachmentName) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);

		// Attachment
		helper.addAttachment(attachmentName, new ByteArrayResource(attachmentData));

		mailSender.send(mimeMessage);

	}
}
