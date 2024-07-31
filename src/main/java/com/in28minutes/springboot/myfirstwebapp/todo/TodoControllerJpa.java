package com.in28minutes.springboot.myfirstwebapp.todo;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.myfirstwebapp.Contact;
import com.in28minutes.springboot.myfirstwebapp.ContactDto;
import com.in28minutes.springboot.myfirstwebapp.repo.Repo;

import jakarta.mail.MessagingException;


@CrossOrigin("*")
@Controller
public class TodoControllerJpa {

		@Autowired
		private MailService notificationService;
	 
		@Autowired
		private User user;
		
		@Autowired
		private Contact contact;
		
		@Autowired
		private Repo repo;
		
		
		@RequestMapping("/testing")
		@ResponseBody
		public String nash() {
			return "working";
		}
		

		@RequestMapping("send-mail")
		@ResponseBody
		public ResponseEntity<String> send(@RequestBody User user)throws MessagingException {
              user.setName(user.getName());
              user.setEmailAddress(user.getEmailAddress());
              user.setText(user.getText());
              
              try {
      			notificationService.sendEmailWithAttachment(user);
      		} catch (MailException mailException) {
      			System.out.println(mailException);
      		}
  		
			return new ResponseEntity<String>("Kindly check your mail",HttpStatus.OK);
		}
		
		
		
		@RequestMapping("send-mail-attachment")
		@ResponseBody
		public String sendWithAttachment(@RequestParam("Name") String name,
		@RequestParam("Email") String emailAddress,
		@RequestParam("Message") String text,
		ModelMap model) throws MessagingException {
			user.setName(name);
            user.setEmailAddress(emailAddress);
            user.setText(text);
		
			try {
				notificationService.sendEmailWithAttachment(user);
			} catch (MailException mailException) {
				System.out.println(mailException);
			}
			return "welcome";
		}
	
		
		
		@RequestMapping("send-contact")
		@ResponseBody
		public ResponseEntity<String> sendWithAttachment(@RequestBody Contact contact)
		{
	       
			
			notificationService.sendEmail(contact);
//			ModelMapper modelMapper = new ModelMapper();
//			ContactDto cono= modelMapper.map(contact, ContactDto.class);
		
			
			repo.save(contact);
			
			
			return new ResponseEntity<String>("Your response has been send successfully",HttpStatus.OK);
		}
	


}