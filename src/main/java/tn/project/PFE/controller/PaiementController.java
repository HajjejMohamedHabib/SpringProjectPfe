package tn.project.PFE.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.entities.Commande;
import tn.project.PFE.entities.ModelPaiement;
import tn.project.PFE.entities.Paiement;
import tn.project.PFE.services.ICommandeServices;
import tn.project.PFE.services.IPaiementServices;

@RestController
@CrossOrigin
public class PaiementController {
@Autowired
IPaiementServices ps;
@Autowired
ICommandeServices cs;
@PostMapping("/ajouterPaiement/{idCom}")
public ModelPaiement ajouterPaiement(@PathVariable("idCom")int idCom) {
	Commande com=new Commande();
	Paiement paiement=new Paiement();
	ModelPaiement modelPaiement=new ModelPaiement();
	try {
	com=cs.retriveCommande(idCom);
	if(ps.retrivePaiement(com)==null) {
    paiement=ps.ajouterPaiement(new Paiement(com));
    modelPaiement.setId(paiement.getId());
    modelPaiement.setCommande(paiement.getCommande().getId());
    }}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return modelPaiement;
}
@GetMapping("/getPaiement")
public List<ModelPaiement> getPaiement(){
	List<ModelPaiement> listMP=new ArrayList<ModelPaiement>();
	List<Paiement> listP=new ArrayList<Paiement>();
	try {
	listP=ps.getPaiement();
	for(Paiement p:listP) {
		ModelPaiement mp=new ModelPaiement();
		mp.setId(p.getId());
		mp.setCommande(p.getCommande().getId());
		listMP.add(mp);
	}
	}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return listMP;
}
@GetMapping("retrivePaiement/{idCom}")
public ModelPaiement retrivePaiement(@PathVariable("idCom")int idCom) {
	ModelPaiement mp=new ModelPaiement();
	Paiement p=new Paiement();
	Commande commande=new Commande();
	try {
		commande=cs.retriveCommande(idCom);
		p=ps.retrivePaiement(commande);
		mp.setId(p.getId());
		mp.setCommande(p.getCommande().getId());
		
	}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return mp;
}
}
