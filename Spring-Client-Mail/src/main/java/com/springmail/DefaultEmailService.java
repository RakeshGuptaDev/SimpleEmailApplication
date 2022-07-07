package com.springmail;

import java.io.File;
import java.io.FileNotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmailService implements EmailService {

	@Autowired
	public JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public void sendSimpleEmail(String toAddress, String subject, String message) {

		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(sender);
			
			simpleMailMessage.setTo(toAddress);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(message);
			System.out.println(simpleMailMessage);
			javaMailSender.send(simpleMailMessage);
			System.out.println("Mail Sent Successfully...");

		} catch (Exception e) {
			System.out.println("Error while Sending Mail");
		}
	}

	@Override
	public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment)
			throws MessagingException, FileNotFoundException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		messageHelper.setFrom(sender);
		messageHelper.setTo(toAddress);
		messageHelper.setSubject(subject);
		messageHelper.setText(message);
		FileSystemResource file = new FileSystemResource(new File(attachment));
		// boolean check=file.exists();
		// System.out.println(check);
		messageHelper.addAttachment("Purchase Order", file);
		javaMailSender.send(mimeMessage);
	}
}