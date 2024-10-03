package tn.project.PFE.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.entities.Favoris;
import tn.project.PFE.entities.ModelFavoris;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;
import tn.project.PFE.services.IFavorisServices;
import tn.project.PFE.services.IProduitServices;
import tn.project.PFE.services.IUserServices;

@RestController
@CrossOrigin
public class FavorisController {
@Autowired
IFavorisServices favServ;
@Autowired
IUserServices userServ;
@Autowired
IProduitServices prodServ;
@GetMapping("/dispalyFavoris/{idClient}")
public List<ModelFavoris> dispalyFavoris(@PathVariable("idClient")int idClient){
	List<ModelFavoris> modelFavs=new ArrayList<ModelFavoris>();
	User client=new User();
	try {
	client=userServ.retrouveUser(idClient);
for(Favoris favoris:favServ.displayFavoris(client)) {
	ModelFavoris modelFav=new ModelFavoris();
	modelFav.setId(favoris.getId());
	modelFav.setLibellé(favoris.getLibellé());
	modelFav.setClient(favoris.getClient().getId());
	modelFav.setProduit(favoris.getProduit().getId());
	modelFav.setDescription(favoris.getProduit().getDescription());
	modelFav.setMarque(favoris.getProduit().getMarque());
	modelFav.setPrix(favoris.getProduit().getPrix());
	modelFav.setImage(favoris.getProduit().getImage());
	modelFav.setNomProduit(favoris.getProduit().getNomProduit());
	modelFav.setNbre_piece(favoris.getProduit().getNbre_piece());
	modelFavs.add(modelFav);}}
catch (Exception e) {
	System.out.println(e.getMessage());
}
return modelFavs;
}
@PostMapping("/ajouterFavoris/{idClient}/{idProduit}")
public ModelFavoris ajouterFavoris(@RequestBody Favoris favoris,@PathVariable("idClient")int idClient,@PathVariable("idProduit")int idProduit) {
	Favoris fav=new Favoris();
	ModelFavoris modelFav=new ModelFavoris();
	User user=new User();
	Produit p=new Produit();
	user=userServ.retrouveUser(idClient);
	p=prodServ.retrouveProduit(idProduit);
	try {
		if(user!=null && p!=null) {
	fav.setClient(user);
	fav.setProduit(p);
	fav.setLibellé(favoris.getLibellé());
	if(favServ.retrouveFavoris(user, p)==null) {
	favServ.ajouterFavoris(fav);
	modelFav.setId(fav.getId());
	modelFav.setLibellé(fav.getLibellé());
	modelFav.setClient(fav.getClient().getId());
	modelFav.setProduit(fav.getProduit().getId());}}}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return modelFav;
}
@DeleteMapping("/deleteFavoris/{id}")
public String deleteFavoris(@PathVariable("id")int id) {
	favServ.supprimeFavoris(id);
	return "succes";
}
@GetMapping("/retrouveFavoris/{idClient}/{idProduit}")
public ModelFavoris retrouveFavoris(@PathVariable("idClient")int idClient,@PathVariable("idProduit")int idProduit) {
	Favoris favoris=new Favoris();
	ModelFavoris modelFav=new ModelFavoris();
	User u=new User();
	Produit p=new Produit();
	try {
	u=userServ.retrouveUser(idClient);
	p=prodServ.retrouveProduit(idProduit);
	if(u!=null && p!=null) {
	favoris=favServ.retrouveFavoris(u, p);
	if(favoris!=null) {
	modelFav.setClient(favoris.getClient().getId());
	modelFav.setId(favoris.getId());
	modelFav.setProduit(favoris.getProduit().getId());
	modelFav.setLibellé(favoris.getLibellé());
	}
	}
	}
	catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return modelFav ;
}
}
