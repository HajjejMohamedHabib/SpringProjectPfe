package tn.project.PFE.services;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
/**
 * 
 * @author Mukuljaiswal
 *
 */
@Service
public class MailService {

	/*
	 * The Spring Framework provides an easy abstraction for sending email by
	 * using the JavaMailSender interface, and Spring Boot provides
	 * auto-configuration for it as well as a starter module.
	 */
	private JavaMailSender javaMailSender;

	/**
	 * 
	 * @param javaMailSender
	 */
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * This function is used to send mail without attachment.
	 * 
	 * @param user
	 * @throws MailException
	 */

	public void sendEmail(String email,String lienInit) throws MailException {

		/*
		 * This JavaMailSender Interface is used to send Mail in Spring Boot.
		 * This JavaMailSender extends the MailSender Interface which contains
		 * send() function. SimpleMailMessage Object is required because send()
		 * function uses object of SimpleMailMessage as a Parameter
		 */

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setSubject("Forgot Password");
		mail.setText("Init your password : "+lienInit);

		/*
		 * This send() contains an Object of SimpleMailMessage as an Parameter
		 */
		javaMailSender.send(mail);
	}

	/**
	 * This fucntion is used to send mail that contains a attachment.
	 * 
	 * @param user
	 * @throws MailException
	 * @throws MessagingException
	 */
}