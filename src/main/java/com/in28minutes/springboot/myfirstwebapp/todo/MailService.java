package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.myfirstwebapp.Contact;
 
 
 
/**
* 
* @author Mukuljaiswal
*
*/
@Service
public class MailService {
 
	/*
	 * The Spring Framework provides an easy abstraction for sending email by using
	 * the JavaMailSender interface, and Spring Boot provides auto-configuration for
	 * it as well as a starter module.
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
	 * @param user
	 * @throws MailException
	 */
 
	public void sendEmail(Contact contact) throws MailException {
 
		/*
		 * This JavaMailSender Interface is used to send Mail in Spring Boot. This
		 * JavaMailSender extends the MailSender Interface which contains send()
		 * function. SimpleMailMessage Object is required because send() function uses
		 * object of SimpleMailMessage as a Parameter
		 */
 
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(contact.getEmail());
		mail.setSubject("Mail from Nandhu Suresh");
		mail.setText("Dear "+contact.getName()+","+"\n"
		        +"........................................................................"+"\n"
                + "Your Message: "+"\n"+contact.getText()+"\n"
                +"Thanks for your interest . I’ll get back to you with a quote within the hour."+"\n"
                +"........................................................................"+"\n"
                
                +"Thank you"+"\n"
                +"Nandhu Suresh❤");
 
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
	public void sendEmailWithAttachment(User user) throws MailException, MessagingException {
 
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setTo(user.getEmailAddress());
		helper.setSubject("Nandhu Resume");
        helper.setText("Dear Recruiter,"+"\n"
                + "Please find the attached document below."+"\n"
                +"Thank you"+"\n"
                +"Nandhu Suresh❤");  	
 
       ClassPathResource classPathResource = new ClassPathResource("resume.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);
 
		javaMailSender.send(mimeMessage);
	}
 
}