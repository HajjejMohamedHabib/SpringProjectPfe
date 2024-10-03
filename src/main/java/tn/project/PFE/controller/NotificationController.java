package tn.project.PFE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.entities.ContactUs;
import tn.project.PFE.entities.Hello;
import tn.project.PFE.entities.Notification;
import tn.project.PFE.repository.NotificationRepository;

@RestController
@CrossOrigin
public class NotificationController {
	@Autowired
	NotificationRepository notifrep;
	Hello hello=new Hello();
	@MessageMapping("/hello")
	@SendTo("/topic/hi")
	public Hello greeting(Notification not) throws Exception {
		hello.setGreeting("nouvelle commande de "+not.getContent());
		return hello;
	}
	@MessageMapping("/messageNotif")
	@SendTo("/topic/messageNotif")
	public Hello messageNotif(ContactUs contact) throws Exception {
		String str="\"How to add doublequotes\"";
		System.out.println(str);
		hello.setGreeting("{"+"\"first_name\""+":"+"\""+contact.getFirst_name()+"\""+","+"\"last_name\""+":"+"\""+contact.getLast_name()+"\""+","+"\"phone_number\""+":"+"\""+contact.getPhone_number()+"\""
		+","+"\"email\""+":"+"\""+contact.getEmail()+"\""+","+"\"message\""+":"+"\""+contact.getMessage()+"\""+"}");
		System.out.println(hello);
		return hello;
	}
	@PostMapping("/ajouterNotification")
	public Notification addNotif() {
		 return notifrep.save(new Notification(hello.getGreeting()));
        
	}
	@GetMapping("/getNotification")
	public List<Notification> getNotif() {
	return (List<Notification>)notifrep.findAll();
	}
}
