package tn.project.PFE.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.entities.Interaction;
import tn.project.PFE.entities.ModelInteraction;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.entities.User;
import tn.project.PFE.services.IInteractionServices;
import tn.project.PFE.services.IProduitServices;
import tn.project.PFE.services.IUserServices;
@RestController
public class InteractionController {
	@Autowired
	IInteractionServices is;
	@Autowired
	IProduitServices ps;
	@Autowired
	IUserServices us;
    @PostMapping("/ajouterInteraction")
    public ModelInteraction ajouterInteraction(@RequestBody ModelInteraction interaction) {
    	User client =new User();
    	Produit p =new Produit();
    	List<Interaction> li=new ArrayList<Interaction>();
    	boolean existe=false;
    	int idInter=0;
    	try {
    		client=us.retrouveUser(interaction.getClient());
    		p=ps.retrouveProduit(interaction.getProduit());
    		li=is.getAllInteractions();
    		for(Interaction i :li) {
    			if(i.getProduit()==p && i.getClient()==client) {
    				idInter=i.getId();
    				existe=true;
    			}
    		}
    		if(existe==false) {
    			Interaction i =new Interaction();
    			i.setClient(client);
    			i.setProduit(p);
    			i.setDate_interaction(interaction.getDate_interaction());
    			i.setNbreEtoile(interaction.getNbreEtoile());
    			is.ajouterInteraction(i);
    		}else {
    			is.updateInteraction(idInter, interaction.getNbreEtoile());
    		}
    		
    	}catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	return interaction;
    }
    @GetMapping("/getInteraction/{idProduit}")
    public List<ModelInteraction> getInteractionByProduct(@PathVariable("idProduit")int idProduit)
    {
    	Produit produit=new Produit();
    	List<Interaction> li= new ArrayList<Interaction>();
    	List<ModelInteraction> lmi= new ArrayList<ModelInteraction>();
    	try {
    	produit=ps.retrouveProduit(idProduit);
    	li= is.getAllInteractionProduct(produit);
    	for(Interaction i:li) {
    		ModelInteraction mi= new ModelInteraction();
    		mi.setId(i.getId());
    		mi.setClient(i.getClient().getId());
    		mi.setProduit(i.getProduit().getId());
    		mi.setDate_interaction(i.getDate_interaction());
    		mi.setNbreEtoile(i.getNbreEtoile());
    		lmi.add(mi);
    	}
    	}catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	return lmi;
    }
}
