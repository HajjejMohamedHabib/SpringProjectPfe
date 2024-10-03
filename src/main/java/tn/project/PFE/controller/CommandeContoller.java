package tn.project.PFE.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javassist.expr.NewArray;
import tn.project.PFE.entities.Commande;
import tn.project.PFE.entities.CommandeItem;
import tn.project.PFE.entities.ModelCommande;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;
import tn.project.PFE.services.ICommandeItemServices;
import tn.project.PFE.services.ICommandeServices;
import tn.project.PFE.services.IUserServices;
import tn.project.PFE.services.ProduitServices;

@RestController
@CrossOrigin
public class CommandeContoller {
@Autowired
ICommandeServices IComServ;
@Autowired
ICommandeItemServices comItemServ;
@Autowired
 ProduitServices produitServ;
@Autowired
IUserServices userServ;
@PostMapping("/ajoutCommande")
public ModelCommande ajoutCommande(@RequestBody ModelCommande modelCommande) {
	Commande com=new Commande();
	ModelCommande modelCom=new ModelCommande();
	Commande commande=new Commande();
	User client=new User();
	try {
	client=userServ.retrouveUser(modelCommande.getClient());
	commande.setFirst_name(modelCommande.getFirst_name());
	commande.setLast_name(modelCommande.getLast_name());
	commande.setEmail(modelCommande.getEmail());
	commande.setPhone_number(modelCommande.getPhone_number());
    commande.setAddress(modelCommande.getAddress());
    commande.setCompany_name(modelCommande.getCompany_name());
    commande.setCity(modelCommande.getCity());
    commande.setCountry(modelCommande.getCountry());
    commande.setPost_code(modelCommande.getPost_code());
    commande.setDate_commande(new Date());
    commande.setClient(client);
	com=IComServ.ajoutCommande(commande);
	modelCom.setFirst_name(com.getFirst_name());
	modelCom.setLast_name(com.getLast_name());
	modelCom.setEmail(com.getEmail());
	modelCom.setPhone_number(com.getPhone_number());
	modelCom.setAddress(com.getAddress());
	modelCom.setCompany_name(com.getCompany_name());
	modelCom.setCity(com.getCity());
	modelCom.setCountry(com.getCountry());
	modelCom.setPost_code(com.getPost_code());
	modelCom.setDate_commande(new Date());
	modelCom.setEtat(com.isEtat());
	modelCom.setClient(com.getClient().getId());
	modelCom.setId(com.getId());
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return modelCom;
}
@GetMapping("/dispalyCommande")
public List<ModelCommande> displayCommande(){
	List<ModelCommande> listCom=new ArrayList<ModelCommande>();
	List<CommandeItem> comItems=new ArrayList<CommandeItem>();
	try {
for(Commande com:IComServ.dispalyCommande()) {
	//calculPrix
	comItems=comItemServ.displayCommandeItem(com);
	float prixCom=0;
	for(CommandeItem comItem:comItems) {
		Produit p=new Produit();
		p=produitServ.retrouveProduit(comItem.getProduit().getId());
		float prixProduit=p.getPrix();
		prixCom=prixCom+prixProduit*comItem.getQuantite();
	}
	//calculPrix
	ModelCommande modelCom=new ModelCommande();
	modelCom.setId(com.getId());
	modelCom.setFirst_name(com.getFirst_name());
	modelCom.setLast_name(com.getLast_name());
	modelCom.setEmail(com.getEmail());
	modelCom.setPhone_number(com.getPhone_number());
	modelCom.setAddress(com.getAddress());
	modelCom.setCompany_name(com.getCompany_name());
	modelCom.setCity(com.getCity());
	modelCom.setCountry(com.getCountry());
	modelCom.setPost_code(com.getPost_code());
	modelCom.setDate_commande(com.getDate_commande());
	modelCom.setPrix(prixCom);
	modelCom.setEtat(com.isEtat());
	modelCom.setClient(com.getClient().getId());
	listCom.add(modelCom);
}	}catch (Exception e) {
	System.out.println(e.getMessage());
}
return listCom;
}
@GetMapping("/retrouveCommande")
public Commande retrouveCommande(@PathVariable("id") int id) {
	return IComServ.retriveCommande(id);
}
@PutMapping("/approveCommande/{id}")
public Commande approveCommande(@PathVariable("id")int id) {
	Commande commande=new Commande();
	IComServ.approveCommande(id);
	commande=IComServ.retriveCommande(id);
	return commande;
}
@GetMapping("/getCommandesParClient/{idClient}")
public List<ModelCommande> getCommandesParClient(@PathVariable("idClient")int idClient){
	User client=new User();
	List<CommandeItem> comItems=new ArrayList<CommandeItem>();
	List<Commande> commandes=new ArrayList<Commande>();
	List<ModelCommande> modelCommandes=new ArrayList<ModelCommande>();
	 DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
		        DateFormat.SHORT,
		        DateFormat.SHORT);
	try {
	client=userServ.retrouveUser(idClient);
	commandes=IComServ.getCommandesParClient(client);
	for(Commande c:commandes) {
		//calculPrix
				comItems=comItemServ.displayCommandeItem(c);
				float prixCom=0;
				for(CommandeItem comItem:comItems) {
					Produit p=new Produit();
					p=produitServ.retrouveProduit(comItem.getProduit().getId());
					float prixProduit=p.getPrix();
					prixCom=prixCom+prixProduit*comItem.getQuantite();
				}
				//calculPrix
		ModelCommande mc=new ModelCommande();
		mc.setId(c.getId());
		mc.setFirst_name(c.getFirst_name());
		mc.setLast_name(c.getLast_name());
		mc.setEmail(c.getEmail());
		mc.setPhone_number(c.getPhone_number());
		mc.setAddress(c.getAddress());
		mc.setCompany_name(c.getCompany_name());
		mc.setCity(c.getCity());
		mc.setCountry(c.getCountry());
		mc.setPost_code(c.getPost_code());
		mc.setDate_commande(c.getDate_commande());
		mc.setDate_com(shortDateFormat.format(c.getDate_commande()));
		mc.setPrix(prixCom);
		mc.setEtat(c.isEtat());
		mc.setClient(c.getClient().getId());
		modelCommandes.add(mc);
	}}catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return modelCommandes;
}
}
