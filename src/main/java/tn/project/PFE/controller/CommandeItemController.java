package tn.project.PFE.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.project.PFE.entities.Commande;
import tn.project.PFE.entities.CommandeItem;
import tn.project.PFE.entities.ModelCommandeItem;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.services.ICommandeItemServices;
import tn.project.PFE.services.ICommandeServices;
import tn.project.PFE.services.IProduitServices;

@RestController
@CrossOrigin
public class CommandeItemController {
@Autowired
ICommandeItemServices comItemSer;
@Autowired
ICommandeServices comSer;
@Autowired
IProduitServices prodServ;
@GetMapping("/displayCommandeItem/{idCom}")
public List<ModelCommandeItem> displayCommandeItem(@PathVariable("idCom")int idCom)
{
 List<ModelCommandeItem> listCI=new ArrayList<ModelCommandeItem>();
 Commande com=new Commande();
 com=comSer.retriveCommande(idCom);
 for(CommandeItem ci:comItemSer.displayCommandeItem(com)) {
	 ModelCommandeItem comItem=new ModelCommandeItem();
     comItem.setId(ci.getId());
     comItem.setQuantite(ci.getQuantite());
     comItem.setCommande(ci.getCommande().getId());
     comItem.setProduit(ci.getProduit().getId());
     comItem.setMarque(ci.getProduit().getMarque());
     comItem.setNomProduit(ci.getProduit().getNomProduit());
     comItem.setPrix(ci.getProduit().getPrix());
     comItem.setDescription(ci.getProduit().getDescription());
     listCI.add(comItem);
 }
 return listCI;
}
@PostMapping("/ajouterCommandeItem/{idCom}")
public ModelCommandeItem ajouterCommandeItem(@RequestBody ModelCommandeItem comItem,@PathVariable("idCom")int idCom) {
	int idProduit=comItem.getProduit();
	ModelCommandeItem modelComItem=new ModelCommandeItem();
	CommandeItem commandeItem=new CommandeItem();
	CommandeItem retourCommandeItem=new CommandeItem();
	Commande commande=new Commande();
	Produit produit=new Produit();
	try {
		commande=comSer.retriveCommande(idCom);
		produit=prodServ.retrouveProduit(idProduit);
		if(comItemSer.retriveCommandeItemParProduit(produit, commande)==null) {
		commandeItem.setProduit(produit);
		commandeItem.setCommande(commande);
		commandeItem.setQuantite(comItem.getQuantite());
		retourCommandeItem= comItemSer.ajoutCommandeItem(commandeItem);
		modelComItem.setId(retourCommandeItem.getId());
		modelComItem.setQuantite(retourCommandeItem.getQuantite());
		modelComItem.setProduit(retourCommandeItem.getProduit().getId());
		modelComItem.setCommande(retourCommandeItem.getCommande().getId());}
		
	}
	catch (Exception e) {
		System.out.println(e.getMessage()+"block catch");
	}
	return modelComItem;
 	
}
@GetMapping("/getCommandeItems")
public List<CommandeItem> getCommandeItems(){
return comItemSer.getListItems();
}
}

