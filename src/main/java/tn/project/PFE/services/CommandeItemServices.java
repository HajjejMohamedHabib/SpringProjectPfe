package tn.project.PFE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.project.PFE.entities.Commande;
import tn.project.PFE.entities.CommandeItem;
import tn.project.PFE.entities.Produit;
import tn.project.PFE.repository.CommandeItemRepository;

@Service
public class CommandeItemServices implements ICommandeItemServices{
     @Autowired
     CommandeItemRepository comItemRep;
	@Override
	public CommandeItem ajoutCommandeItem(CommandeItem commandeItem) {
		return comItemRep.save(commandeItem);
	}

	@Override
	public List<CommandeItem> displayCommandeItem(Commande commande) {
		// TODO Auto-generated method stub
		return comItemRep.dispalyCommandeItem(commande);
	}

	@Override
	public CommandeItem retriveCommandeItem(int id) {
		return comItemRep.findById(id).get();
	}

	@Override
	public CommandeItem retriveCommandeItemParProduit(Produit produit,Commande commande) {
		return comItemRep.dispalyCommandeItemParProduit(produit, commande);
	}

	@Override
	public List<CommandeItem> getListItems() {
		return (List<CommandeItem>) comItemRep.findAll();
	}

}
