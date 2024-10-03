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
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.entities.Categorie;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;
import tn.project.PFE.services.ICategorieServices;
import tn.project.PFE.services.IProduitServices;
import tn.project.PFE.services.IUserServices;
@RestController
@CrossOrigin
public class CategorieController {
@Autowired
ICategorieServices catserv;
@Autowired
IUserServices userserv;
@Autowired
IProduitServices prodserv;
@GetMapping("/displayCat")
public List<Categorie> dispalyCategories(){
	List<Categorie> cats=new ArrayList<Categorie>();
	for(Categorie categorie: catserv.displayCategories()) {
		Categorie cat=new Categorie();
		cat.setId(categorie.getId());
		cat.setNbre_piece(categorie.getNbre_piece());
		cat.setNom_categorie(categorie.getNom_categorie());
		cats.add(cat);
	}
	return cats;
}
@PostMapping("/addCat")
	public Categorie ajouterCategorie(@RequestBody Categorie categorie) {
	return catserv.ajouterCategorie(categorie);
}
@PutMapping("/modifCat/{id}")
public Categorie modifierCategorie(@RequestBody Categorie categorie ,@PathVariable("id")int id) {
	catserv.modifiderCategorie(categorie, id);
	Categorie cat =new Categorie();
	Categorie cat1 =new Categorie();
     cat=catserv.retrouveCategorie(id);
     cat1.setId(cat.getId());
     cat1.setNom_categorie(cat.getNom_categorie());
     cat1.setNbre_piece(cat.getNbre_piece());
     return cat1;
}
@DeleteMapping("/deleteCat/{id}")
public String deleteCategorie(@PathVariable("id")int id) {
	catserv.supprimeCategorie(id);
	return "categorie supprimé";
}
@GetMapping("/retrouveCat/{id}")
public Categorie retrouveCat(@PathVariable("id")int id) {
	Categorie cat =new Categorie();
	Categorie cat1 =new Categorie();
     cat=catserv.retrouveCategorie(id);
     cat1.setId(cat.getId());
     cat1.setNom_categorie(cat.getNom_categorie());
     cat1.setNbre_piece(cat.getNbre_piece());
     return cat1;
}
@GetMapping("/displayCatParVendeur/{idVend}")
public List<Categorie> displayCatParVendeur(@PathVariable("idVend")int idVend) {
	User vendeur=new User();
	List<Categorie> listCat=new ArrayList<Categorie>();
	   List<Produit> produits= new ArrayList<Produit>();
	   try {
		   vendeur=userserv.retrouveUser(idVend);
	   produits=prodserv.displayProduitsParVendeur(vendeur);
	   for(Produit p:produits) {
		   boolean existe=false;
		   for(Categorie c:listCat) {
			   if(p.getCategorie().getId()==c.getId()) {
				   c.setNbre_piece(c.getNbre_piece()+p.getNbre_piece());
			       existe=true;
			   }
			   }
		   if(existe==false) {
			   Categorie categorie=new Categorie();
			   categorie.setId(p.getCategorie().getId());
			   categorie.setNbre_piece(p.getNbre_piece());
			   categorie.setNom_categorie(p.getCategorie().getNom_categorie());
			   listCat.add(categorie);
		   }
		  
	   }
	   }
	   catch (Exception e) {
		System.out.println(e.getMessage());
	}
	   return listCat;
}
}

