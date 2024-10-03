package tn.project.PFE.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.entities.Abonnement;
import tn.project.PFE.entities.ModelAbonnement;
import tn.project.PFE.entities.User;
import tn.project.PFE.services.IAbonnementServices;
import tn.project.PFE.services.IUserServices;

@RestController
@CrossOrigin
public class AbonnementController {
@Autowired
IAbonnementServices as;
@Autowired
IUserServices us;
@PostMapping("/ajouterAbonnement")
public ModelAbonnement ajouterAbonnement(@RequestBody ModelAbonnement abonnement) {
	Abonnement ab=new Abonnement();
	Abonnement ab1=new Abonnement();
	ModelAbonnement ma=new ModelAbonnement();
	try {
	User vendeur=us.retrouveUser(abonnement.getVendeur());
	ab.setId(abonnement.getId());
	ab.setVendeur(vendeur);
	ab.setTypeAbonnement(abonnement.getTypeAbonnement());
	ab.setDateFinAbonnement(abonnement.getDateFinAbonnement());
	if(as.retriveAbonnement(vendeur)==null) {
	ab1=as.ajouterAbonnement(ab);
	ma.setId(ab1.getId());
	ma.setVendeur(ab1.getVendeur().getId());
	ma.setTypeAbonnement(ab1.getTypeAbonnement());
	ma.setDateFinAbonnement(ab1.getDateFinAbonnement());
	}}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return ma; 
}
@GetMapping("/getAbonnements")
public List<ModelAbonnement> getAbonnements(){
	List<ModelAbonnement> lma=new ArrayList<ModelAbonnement>();
	List<Abonnement> la=new ArrayList<Abonnement>();
	try {
	la=as.displayAbonnement();
	for(Abonnement a:la) {
		ModelAbonnement ma=new ModelAbonnement();
		ma.setId(a.getId());
		ma.setTypeAbonnement(a.getTypeAbonnement());
		ma.setDateFinAbonnement(a.getDateFinAbonnement());
		ma.setVendeur(a.getVendeur().getId());
		lma.add(ma);
	}}catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return lma;
}
@GetMapping("/retriveAbonnement/{idVend}")
public ModelAbonnement retriveAbonnement(@PathVariable("idVend")int idVend) {
	User vendeur =new User();
	Abonnement ab=new Abonnement();
	ModelAbonnement ma=new ModelAbonnement();
	try {
		vendeur=us.retrouveUser(idVend);
		ab=as.retriveAbonnement(vendeur);
		ma.setId(ab.getId());
		ma.setVendeur(ab.getVendeur().getId());
		ma.setTypeAbonnement(ab.getTypeAbonnement());
		ma.setDateFinAbonnement(ab.getDateFinAbonnement());
	}
	catch (Exception e) {
		System.err.println(e.getMessage());
	}
	return ma;
}
@PutMapping("/updateDateFinAbonnement/{idVend}")
public Abonnement updateDateFinAbonnement(@RequestBody Abonnement abonnement) {
	User vendeur =new User();
	Abonnement ab=new Abonnement();
	try {
	vendeur=us.retrouveUser(abonnement.getVendeur().getId());
	as.mettreAjourDateFinAbonnement(vendeur, abonnement.getDateFinAbonnement(),abonnement.getTypeAbonnement());
	ab=abonnement;
	}catch (Exception e) {
		System.err.println(e.getMessage());
	}
	return ab;
}
}
