package tn.project.PFE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.entities.SecretPaiment;
import tn.project.PFE.services.ISecretPaimentServices;

@RestController
@CrossOrigin
public class SecretPaiementController {
@Autowired
ISecretPaimentServices ss;
@PostMapping("/ajouterSecretPaiement")
public SecretPaiment ajouterSecretPaiement(@RequestBody SecretPaiment secPaiement) {
	SecretPaiment secp=new SecretPaiment();
	try {
	secp= ss.ajouterSecretPaiement(secPaiement);
		
}catch (Exception e) {
	System.out.println(e.getMessage());
	
}
	return secp;
	}
@GetMapping("/retriveSecretPaiement/{email}")
public SecretPaiment retriveSecretPaiement(@PathVariable("email")String email) {
	SecretPaiment secp=new SecretPaiment();
	try {
	secp= ss.retriveSecretPaiement(email);
}catch (Exception e) {
	System.out.println(e.getMessage());
	
}
	return secp;
	}
@GetMapping("/retriveSecretPaiementCle/{email}/{cle}")
public SecretPaiment retriveSecretPaiementCle(@PathVariable("email")String email,@PathVariable("cle")long cle) {
	SecretPaiment secp=new SecretPaiment();
	try {
	secp= ss.retriveSecretPaiementCle(email, cle);
}catch (Exception e) {
	System.out.println(e.getMessage());
	
}
	return secp;
	}
}
