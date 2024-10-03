package tn.project.PFE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.entities.CleForgotPassword;
import tn.project.PFE.services.ICleForgotPasswordServices;

@RestController
@CrossOrigin
public class CleForgotPasswordController {
@Autowired
ICleForgotPasswordServices cs;
@PostMapping("/ajouterCleForgotPassword")
public CleForgotPassword ajouterCleForgotPassword(@RequestBody CleForgotPassword cleF) {
	CleForgotPassword cf =new CleForgotPassword();
	try {
	cf= cs.ajouterCleForgotPassword(cleF);}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return cf;
}
@GetMapping("/getCleForgotPassword/{id}")
public CleForgotPassword getCleForgotPassword(@PathVariable("id")int id) {
	CleForgotPassword cf=new CleForgotPassword();
	try {
	  cf=cs.retriveCleForgotPassword(id);
	}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return cf;
}
}
