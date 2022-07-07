package com.springmail;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;

public interface EmailService {
	
	 void sendSimpleEmail(final String toAddress, final String subject, final String message)throws Exception;
     void sendEmailWithAttachment(final String toAddress, final String subject, final String message, final String attachment) throws MessagingException, FileNotFoundException;


}
