package tn.project.PFE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.services.MailService;

@RestController
@CrossOrigin
public class MailController {
	@Autowired
	private MailService notificationService;
	@PostMapping("/send-mail/{email}")
	public String send(@PathVariable("email")String email,@RequestParam("lienInit")String lienInit) {
		try {
			notificationService.sendEmail(email,lienInit);
			System.out.println("send...");
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}
}
