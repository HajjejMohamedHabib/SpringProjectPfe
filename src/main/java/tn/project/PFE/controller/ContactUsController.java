package tn.project.PFE.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.entities.ContactUs;
import tn.project.PFE.services.IContactUsServices;

@RestController
@CrossOrigin
public class ContactUsController {
@Autowired IContactUsServices contServ;
@PostMapping("/ajoutContact")
public ContactUs ajouterContact(@RequestBody ContactUs contact) {
	contact.setDate(new Date());
	return contServ.ajouterContact(contact);
}
@GetMapping("/getContact")
public List<ContactUs> getContact(){
	return contServ.getContact();
}

}
