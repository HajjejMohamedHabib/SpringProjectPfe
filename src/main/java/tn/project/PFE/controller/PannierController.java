package tn.project.PFE.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.entities.ModelPannier_Produit;
import tn.project.PFE.entities.Pannier;
import tn.project.PFE.entities.Pannier_Produit;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;
import tn.project.PFE.services.IPannierServices;
import tn.project.PFE.services.IProduitServices;
import tn.project.PFE.services.IUserServices;

@RestController
@CrossOrigin
public class PannierController {
@Autowired
IPannierServices pannierServ;
@Autowired
IUserServices userServ;
@Autowired
IProduitServices prodServ;
@GetMapping("/getPannier/{idClient}")
public List<ModelPannier_Produit> getPannierProduit(@PathVariable("idClient")int idClient){
	User client=new User();
	List<ModelPannier_Produit> listModelPP=new ArrayList<ModelPannier_Produit>();
	List<Pannier_Produit> listPannierProduits=new ArrayList<Pannier_Produit>();
	try {
	client=userServ.retrouveUser(idClient);
	listPannierProduits= pannierServ.getPannierClient(client);
	for(Pannier_Produit pp:listPannierProduits) {
		ModelPannier_Produit modelPP=new ModelPannier_Produit();
		modelPP.setId(pp.getProduit().getId());
		modelPP.setPannier(pp.getPannier().getId());
		modelPP.setProduit(pp.getProduit().getId());
		modelPP.setDescription(pp.getProduit().getDescription());
		modelPP.setMarque(pp.getProduit().getMarque());
		modelPP.setImage(pp.getProduit().getImage());
		modelPP.setPrix(pp.getProduit().getPrix());
		modelPP.setVendeur(pp.getProduit().getVendeur().getId());
		modelPP.setIdPannierProduit(pp.getId());
		modelPP.setQuantite(pp.getQuantite());
		listModelPP.add(modelPP);
	}
	}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return listModelPP;
}
@PostMapping("/ajouterPannier")
public ModelPannier_Produit ajouterPannier(@RequestBody ModelPannier_Produit pannier_produit) {
	User client=new User();
	Produit produit=new Produit();
	ModelPannier_Produit modelpp=new ModelPannier_Produit();
	Pannier pannier=new Pannier();
	Pannier_Produit retourpp=new Pannier_Produit();
	Pannier_Produit pp=new Pannier_Produit();
	
	try {
		produit=prodServ.retrouveProduit(pannier_produit.getProduit());
	    client =userServ.retrouveUser(pannier_produit.getIdClient());
	    pannier=pannierServ.retrivePannier(client);
	    pp=pannierServ.retrivePannierProduit(pannier, produit);
	    if(produit!=null && client!=null) {
	     if(pp==null) {
	    retourpp=pannierServ.ajouterPannier(client, produit,1);
	    modelpp.setId(retourpp.getId());
	    modelpp.setPannier(retourpp.getPannier().getId());
	    modelpp.setProduit(retourpp.getProduit().getId());
	    modelpp.setQuantite(1);
	}}}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return modelpp;
}
@DeleteMapping("/deletePannierItem/{id}")
public String deletePannierItem(@PathVariable("id")int id) {
	try {
	pannierServ.deletePannierItem(id);
	return "succes";
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return "pannier item non supprimé";
}
@PutMapping("/updateQuantitePlus/{id}")
public String updateQuantitePlus(@PathVariable("id")int id) {
	 pannierServ.updateQuantitePlus(id);
	 return "succes update";
}
@PutMapping("/updateQuantiteMoins/{id}")
public String updateQuantiteMoins(@PathVariable("id")int id) {
	 pannierServ.updateQuantiteMoins(id);
	 return "succes update";
}
}
